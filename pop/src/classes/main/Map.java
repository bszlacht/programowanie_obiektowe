package classes.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
public class Map implements IPositionChangedObserver{
    protected final HashMapList<Vector2d,Animal> animalHashMapList = new HashMapList<>();
    protected final  HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    protected  final LinkedList<Animal> dead = new LinkedList<>();
    // --
    public final int height;
    public final int width;
    public final int jungleWidth;
    public final int jungleHeight;
    public final Vector2d upperRight;
    public final Vector2d lowerLeft;
    public final Vector2d jungleUpperRight;
    public final Vector2d jungleLoweLeft;
    // -- NIEPOTRZBEN
    // --
    public final int mapSurface;
    public final int jungleSurface;
    public final int sawannaSurface;
    // --
    public final int initialEnergy;
    public final int grassEnergy;
    public final int moveEnergy;
    // --
    public final double jungleRatio;
    public int day;
    // --

    private LinkedList<Integer> averageAnimalCount = new LinkedList<>();
    private LinkedList<Integer> averageGrassCount = new LinkedList<>();
    private Multiset<DNA> averageDNAS = HashMultiset.create();
    private LinkedList<Long> averageEnergyCount = new LinkedList<>();
    private LinkedList<Integer> averageDeathLifeLengthCount = new LinkedList<>();
    private LinkedList<Integer> avarageKidsCount = new LinkedList<>();

    public Map(int width, int height, double jungleRatio,int initialEnergy, int grassEnergy, int moveEnergy, int startingAnimals){
        double axesMultiplayer =  Math.sqrt(jungleRatio);
        int jHeight = (int)((double)height* axesMultiplayer);
        int jWidth = (int)((double)width*axesMultiplayer);
        // --
        this.jungleWidth = jWidth;
        this.jungleHeight = jHeight;
        //--
        this.height = height;
        this.width = width;
        this.upperRight = new Vector2d(this.width-1,this.height-1);
        this.lowerLeft = new Vector2d(0,0);

        int lowerLeftJGX = 0;
        int lowerLeftJGY = 0;
        int upperRightJGX = width - 1;
        int upperRightJGY = height - 1;
        for (int i = 0; i < (width - jWidth); i++) {
            if (i % 2 == 0) {
                lowerLeftJGX++;
            } else {
                upperRightJGX--;
            }
        }
        for (int i = 0; i < (height - jHeight); i++) {
            if (i % 2 == 0) {
                lowerLeftJGY++;
            } else {
                upperRightJGY--;
            }
        }
        this.jungleLoweLeft = new Vector2d(lowerLeftJGX, lowerLeftJGY);
        this.jungleUpperRight = new Vector2d(upperRightJGX, upperRightJGY);


        // --
        this.mapSurface = height*width;
        this.jungleSurface = jHeight*jWidth;
        this.sawannaSurface = this.mapSurface - this.jungleSurface;
        //--
        this.initialEnergy = initialEnergy;
        this.grassEnergy = grassEnergy;
        this.moveEnergy = moveEnergy;
        //
        this.jungleRatio = jungleRatio;
        for(int i = 0; i < startingAnimals; i++){
            this.placeFirstAnimal();
        }

        // TODO: SPRAWDZENIE CZY DOBRE PAREMETRY JUNGLI TJ. CZY MNIEJSZE NIE WIDTH I HEIGHT

    }
    //TODO: ZMIENIC NIEKTORE NA PRIVATE!

    // PLACERS AND ANIMAL MOVER:
    public boolean birthAnimal(Animal father, Animal mother, Vector2d position){
        if(!this.isValid()){
            return false;
        }
        DNA kidDna = new DNA(father,mother);
        Vector2d lowerLeftChildBoundary = new Vector2d(position.getX()-1,position.getY()-1);
        Vector2d higherRightChildBoundary = new Vector2d(position.getX()+1,position.getY()+1);
        if(this.objectsAround(position) < 8){
            Vector2d kidPosition = Vector2d.randInRectangle(lowerLeftChildBoundary,higherRightChildBoundary);
            kidPosition = kidPosition.wrapAround(this);

            while(this.animalHashMapList.get(kidPosition) != null || kidPosition.equals(position)){
                kidPosition = Vector2d.randInRectangle(lowerLeftChildBoundary,higherRightChildBoundary);
                kidPosition = kidPosition.wrapAround(this);

            }
            // mamy dobrą pozycje
            Animal kid = new Animal(this,kidPosition,father.energy/4 + mother.energy/4,this.initialEnergy,kidDna,this.moveEnergy);
            father.energy -= father.energy/4;
            father.kids.add(kid);
            mother.energy -= mother.energy/4;
            mother.kids.add(kid);
            this.animalHashMapList.put(kidPosition,kid);
            kid.addObserver(this);
            father.kidCount++;
            mother.kidCount++;
            return true;
        }
        Vector2d kidPosition = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
        kidPosition = kidPosition.wrapAround(this);
        while(this.animalHashMapList.get(kidPosition) != null || kidPosition.equals(position)){
            kidPosition = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
            kidPosition = kidPosition.wrapAround(this);

        }
        // mamy dobrą pozycje
        Animal kid = new Animal(this,kidPosition,father.energy/4 + mother.energy/4,this.initialEnergy,kidDna,this.moveEnergy);
        father.energy -= father.energy/4;
        mother.energy -= mother.energy/4;
        father.kidCount++;
        mother.kidCount++;
        father.kids.add(kid);
        mother.kids.add(kid);
        this.animalHashMapList.put(kidPosition,kid);
        kid.addObserver(this);
        return true;


    }

    public boolean placeFirstAnimal(){
        if(!isValid()){
            throw new IllegalArgumentException("Map is full");
            //return false;
        }
        Vector2d position = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
        if(this.animalsAtCount(position)<1){
            Animal animal = new Animal(this,position,this.initialEnergy,this.initialEnergy,new DNA(),this.moveEnergy);
            this.animalHashMapList.put(position,animal);
            animal.addObserver(this);
            return true;
        }else{
            return placeFirstAnimal();
        }
    }


    public void eatAndRemoveGrass(Animal animal, Vector2d position){ // animal -> grass eater
        Grass grass = grassHashMap.get(position);
        animal.energy += grass.getEnergyValue();
        this.grassHashMap.remove(position);
        grass.removeObserver(this);
    }

    // -----

    // HELPERS:
    public int objectsInTheJungle(){
        int res = 0;
        for(int i = 0; i < width;i++){
            for(int j = 0; j < height; j++){
                Vector2d position = new Vector2d(i,j);
                if((grassHashMap.get(position) != null || animalHashMapList.get(position) != null) && position.inJungle(this,position)){
                    res++;
                }
            }
        }
        return res;
    }

    public int objectsInTheSawanna(){
        int res = 0;
        for(int i = 0; i < width;i++){
            for(int j = 0; j < height; j++){
                Vector2d position = new Vector2d(i,j);
                if((grassHashMap.get(position) != null || animalHashMapList.get(position) != null) && !position.inJungle(this,position)){
                    res++;
                }
            }
        }
        return res;
    }

    public int objectsOnTheMap(){
        return this.objectsInTheSawanna() + objectsInTheJungle();
    }

    public int objectsAround(Vector2d position){
        int res = 0;
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                if(!(x==0 && y==0)){
                    Vector2d nerbyVector = new Vector2d(position.getX()+x,position.getY()+y);
                    nerbyVector = nerbyVector.wrapAround(this);
                    if(this.animalHashMapList.get(nerbyVector) != null || this.grassHashMap.get(nerbyVector) != null){
                        res += 1;
                    }
                }
            }
        }
        return res;

    }

    public int animalsAtCount(Vector2d position){
        LinkedList<Animal> newList = new LinkedList<>();
        newList = this.animalHashMapList.get(position);
        if(newList == null){
            return 0;
        }
        return newList.size();
    }




    public LinkedList<Animal> AnimalsAt(Vector2d position) {
        return this.animalHashMapList.get(position);
    }

    public Grass GrassAt(Vector2d position){
        return this.grassHashMap.get(position);
    }
    public int AnimalsCount(){
        return this.animalHashMapList.allElementsList().size();
    }
    public int GrassCount(){
        return this.grassHashMap.size();
    }

    public DNA dominatingDNA(){
        LinkedList<Animal> animalsList = this.animalHashMapList.allElementsList();
        Multiset<DNA> DNAS = HashMultiset.create();
        for(Animal animal : animalsList){
            DNAS.add(animal.getGenes());
        }
        DNA dominating = null;
        int dominatingC = 0;
        for(DNA genotype : DNAS.elementSet()){
            if(DNAS.count(genotype) > dominatingC){
                dominating = genotype;
                dominatingC = DNAS.count(genotype);
            }
        }
        return dominating;
    }

    public int averageEnergy(){
        LinkedList<Animal> animalsList = this.animalHashMapList.allElementsList();
        int n = animalsList.size();
        if(n == 0){
            return 0;
        }
        int sum = 0;
        for(Animal animal: animalsList){
            sum += animal.energy;
        }
        return sum/n;
    }

    public int averageLifeLength(){
        int n = this.dead.size();
        if(n == 0){
            return 0;
        }
        int sum = 0;
        for(Animal animal: this.dead){
            sum += animal.getLifeLength();
        }
        return sum/n;
    }

    public int averageKidsCount(){
        LinkedList<Animal> animalsList = this.animalHashMapList.allElementsList();
        int n = animalsList.size();
        if(n == 0){
            return 0;
        }
        int sum = 0;
        for(Animal animal: animalsList){
            sum += animal.kidCount;
        }
        return sum/n;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public Vector2d getJungleUpperRight(){
        return this.jungleUpperRight;
    }
    public Vector2d getJungleLoweLeft(){
        return this.jungleLoweLeft;
    }
    // -----

    // Observer
    @Override
    public void objectRemoved(Vector2d position, IMapElement element) {
        if(element instanceof Animal){
            animalHashMapList.remove(position,(Animal)element); // usuwamy z hashlisty
            ((Animal) element).removeObserver(this); // usuwamy observery
        }
        else{
            grassHashMap.remove(position);
            ((Grass) element).removeObserver(this);
        }
    }
    @Override
    public void objectAdded(Vector2d position, IMapElement element){
        if(element instanceof Animal){
            animalHashMapList.put(position,(Animal)element);
            ((Animal) element).addObserver(this);
        }else if(element instanceof Grass){
            grassHashMap.put(position,(Grass)element);
            ((Grass) element).addObserver(this);
        }
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element){
        if(element instanceof Animal){
            animalHashMapList.remove(oldPosition,(Animal)element);
            animalHashMapList.put(newPosition,(Animal)element);
        }// dla trawy mamy remove, tam pozycja sie nie zmienia
    }

    public LinkedList<Animal> getStrongestTwo(LinkedList<Animal> animalList){
        long maxEnergy = 0;
        Animal father = new Animal(this,new Vector2d(0,0),10,10,new DNA(),this.moveEnergy);
        Animal mother = new Animal(this,new Vector2d(0,0),10,10,new DNA(),this.moveEnergy);
        for(Animal animal: animalList){
            if(animal.energy >= maxEnergy){
                father = animal;
                maxEnergy = father.energy;
            }
        }
        maxEnergy = 0;
        for(Animal animal: animalList){
            if(animal.energy >= maxEnergy && !animal.equals(father)){
                mother = animal;
                maxEnergy = mother.energy;
            }
        }
        LinkedList<Animal> res = new LinkedList<Animal>();
        res.add(0,father);
        res.add(1,mother);
        return res;
    }

    public Animal getStrongestOne(LinkedList<Animal> animalList){
        if(animalList.size() == 1){
            return animalList.get(0);
        }
        long maxEnergy = 0;
        Animal strongest = new Animal(this,new Vector2d(0,0),10,10,new DNA(),this.moveEnergy);
        for(Animal animal: animalList){
            if(animal.energy >= maxEnergy){
                strongest = animal;
                maxEnergy = strongest.energy;
            }
        }
        return strongest;
    }

    // ---

    // SIMULATION:
    public void removeDead(){
        LinkedList<Animal> AnimalList = this.animalHashMapList.allElementsList();
        int lifeLength = 0;
        for(Animal animal : AnimalList){
            if(animal.energy <= 0){
                lifeLength += this.day;
                this.animalHashMapList.remove(animal.getPosition(),animal);
                animal.removeObserver(this);
                this.dead.add(animal);
                animal.dead();

            }
        }
        this.averageDeathLifeLengthCount.push(lifeLength);
    }

    public void rotateAndMove(){
        LinkedList<Animal> animalsList = this.animalHashMapList.allElementsList();
        for(Animal animal : animalsList){
            animal.move();
        }
    }

    public void eat(){
        LinkedList<Animal> AnimalList = this.animalHashMapList.allElementsList();
        for(Animal animal : AnimalList){
            if(this.GrassAt(animal.getPosition()) != null){ // jak jest trawa
                if(this.animalHashMapList.get(animal.getPosition()).size() == 1){ // jak jest jeden zwierzak to je
                    this.eatAndRemoveGrass(animal,animal.getPosition());
                }else if(this.animalHashMapList.get(animal.getPosition()).size() > 1){ // jak wiecej to szukamy alphy
                    Animal eater = this.getStrongestOne(this.animalHashMapList.get(animal.getPosition()));
                    this.eatAndRemoveGrass(eater,eater.getPosition());
                }
            }
        }

    }

    public void breeding(){
        LinkedList<Animal> AnimalList = this.animalHashMapList.allElementsList();
        for(Animal animal: AnimalList){
            int animalsAt = this.animalsAtCount(animal.getPosition());
            if(animalsAt >= 2){
                LinkedList<Animal> AnimalListPrecise = this.animalHashMapList.get(animal.getPosition());
                Animal father = this.getStrongestTwo(AnimalListPrecise).get(0);
                Animal mother = this.getStrongestTwo(AnimalListPrecise).get(1);
                if(father.energy >= this.initialEnergy/2 && mother.energy >= this.initialEnergy/2){
                    this.birthAnimal(father,mother,animal.getPosition());
                }
            }
        }
    }

    public void placeGrass(){
        // najpierw kladziemy w jungli:
        Vector2d position = Vector2d.randInRectangle(this.jungleLoweLeft,this.jungleUpperRight);
        if(this.objectsInTheJungle() < this.jungleSurface){
            while(this.animalHashMapList.get(position) != null || this.grassHashMap.get(position) != null || !position.inJungle(this,position)){ // na miejscu jest zwierze i jest trawa -> nowe miejsce
                position = Vector2d.randInRectangle(this.jungleLoweLeft,this.jungleUpperRight);
            }
            Grass grass = new Grass(this,position,this.initialEnergy);
            this.grassHashMap.put(position,grass);
            grass.addObserver(this);
        }
        // kladziemy na sawannie:
        if(this.objectsInTheSawanna() < this.sawannaSurface){
            Vector2d position2 = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
            // szukamy miejsca w sawannie:
            while(this.animalHashMapList.get(position2) != null || this.grassHashMap.get(position2) != null || position2.inJungle(this,position2)){
                position2 = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
            }
            // mamy dobra pozycje:
            Grass grass2 = new Grass(this,position2,this.initialEnergy);
            this.grassHashMap.put(position2,grass2);
            grass2.addObserver(this);
        }
    }
    public void newDay(){
        LinkedList<Animal> animalsList = this.animalHashMapList.allElementsList();
        for(Animal animal:animalsList){
            animal.increaseLifeLength();
        }
        this.removeDead();
        this.rotateAndMove();
        this.eat();
        this.breeding();
        this.placeGrass();
        this.actualize();
        this.day++;

    }

    public boolean isValid(){
        int objects = this.objectsOnTheMap();
        if(objects >= this.mapSurface){
            return false;
        }
        return true;
    }

    public boolean objectIn(Vector2d position){
        if(this.animalHashMapList.get(position) !=null || this.grassHashMap.get(position) != null){
            return true;
        }
        return false;
    }

    public LinkedList<Animal> getAllAnimals(){
        return this.animalHashMapList.allElementsList();
    }

    public void actualize(){
        LinkedList<Animal> animalLinkedList = this.animalHashMapList.allElementsList();
        this.averageAnimalCount.push(animalLinkedList.size());
        this.averageGrassCount.push(this.grassHashMap.size());
        long ener = 0;
        int kidCount = 0;
        for(Animal animal : animalLinkedList){
                kidCount += animal.getKids();
                ener += animal.getEnergy();
        }

        for(Animal animal:animalLinkedList){
            this.averageDNAS.add(animal.getGenes());
        }


        //averageEnergy for dead in removeDead()
        this.averageEnergyCount.push(ener);
        this.avarageKidsCount.push(kidCount);
    }

    public void savaAvarageInTxt() throws IOException {
        int n = this.averageAnimalCount.size();
        int animalCountSum = 0;
        for (Integer integer : this.averageAnimalCount) {
            animalCountSum += integer;
        }
        int ng = this.averageGrassCount.size();
        int ngSum = 0;
        for(Integer integer: this.averageGrassCount){
            ngSum += integer;
        }


        DNA dom = null;
        int domC = 0;
        for(DNA genotype : this.averageDNAS.elementSet()){
            if(this.averageDNAS.count(genotype) > domC){
                dom = genotype;
                domC = this.averageDNAS.count(genotype);
            }
        }
        int ened = this.averageEnergyCount.size();
        int eneC = 0;
        for(Long lng: this.averageEnergyCount){
            eneC += lng;
        }
        int dedd = this.averageDeathLifeLengthCount.size();
        int dedC = 0;
        for(Integer integer: this.averageDeathLifeLengthCount){
            dedC += integer;
        }
        int dk = this.avarageKidsCount.size();
        int kidC = 0;
        for(Integer integer : this.avarageKidsCount){
            kidC += integer;
        }
        int aveKids = kidC/dk; // ilosc dzieci
        int aveLifePan = dedC/dedd; // dl zycia dla zmarlych
        int aveEnergy = eneC/ened; // poz energii
        DNA aveDNA = dom; // dna
        int aveGrass = ngSum/ng; // trawa
        int aveAnimal = animalCountSum/n; // zwierzaki
        FileWriter file = new FileWriter("src/EndStatistics.txt");
        file.write("kid Count:  "+ Integer.toString(aveKids)+"//  " + "averager life's span of death animals:   " + Integer.toString(aveLifePan)+"//   "
        +"average energy:  " + Integer.toString(aveEnergy) + "//  " + "average DNA: " + aveDNA.toString() + "//   " +  "average grass count:" + Integer.toString(aveGrass)+"//   "+
                "average animals count:" + Integer.toString(aveAnimal)+"//  ");
        file.close();
    }

}
