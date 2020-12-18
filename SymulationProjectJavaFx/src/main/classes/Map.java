package main.classes;

import java.util.HashMap;
import java.util.LinkedList;

public class Map implements IPositionChangedObserver{
    protected final HashMapList<Vector2d,Animal> animalHashMapList = new HashMapList<>();
    protected final  HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    public final int height;
    public final int width;
    public final Vector2d upperRight;
    public final Vector2d lowerLeft;
    public int sawannaAnimalCount;
    public int jungleAnimalCount;
    public int sawannaGrassCount;
    public int jungleGrassCount;
    public final Vector2d jungleUpperRight;
    public final Vector2d jungleLoweLeft;
    public final int mapSurface;
    public final int initialEnergy;
    public final int jungleSurface;
    public Map(int height, int width, int jungleRatio,int initialEnergy){
        this.height = height;
        this.width = width;
        this.upperRight = new Vector2d(height,width);
        this.lowerLeft = new Vector2d(0,0);
        this.jungleGrassCount = 0;
        this.sawannaGrassCount = 0;
        this.sawannaAnimalCount = 0;
        this.jungleAnimalCount = 0;
        // TODO: DODAC SPRAWDZENIE ZAPELNIENIA
        // TODO: SPRAWDZENIE CZY DOBRE PAREMETRY JUNGLI TJ. CZY MNIEJSZE NIE WIDTH I HEIGHT
        // TODO : CZYM JEST JUNGLE RATIO
        int axesDevider = (int) Math.sqrt(jungleRatio);
        this.jungleLoweLeft = new Vector2d(0,0);
        this.jungleUpperRight = new Vector2d(this.upperRight.getX()/axesDevider,this.upperRight.getY()/axesDevider);
        this.mapSurface = height*width;
        int jHeight = this.height/axesDevider;
        int jWidth = this.width/axesDevider;
        this.jungleSurface = jHeight*jWidth;
        this.initialEnergy = initialEnergy;
    }
    //TODO: ZMIENIC NIEKTORE NA PRIVATE!

    // PLACERS AND ANIMAL MOVER:
    public boolean birthAnimal(Animal father, Animal mother, Vector2d position){
        DNA kidDna = new DNA(father,mother);

        Vector2d lowerLeftChildBoundary = new Vector2d(position.getX()-1,position.getY()-1);
        Vector2d higherRightChildBoundary = new Vector2d(position.getX()+1,position.getY()+1);
        if(this.objectsAround(position) != 8){ // jak jest 8 pozycji w koło zajetych -> wstawiamy w rand na mapie
            Vector2d kidPosition = Vector2d.randInRectangle(new Vector2d(position.getX()-1,position.getY()-1),new Vector2d(position.getX()+1,position.getY()+1));
            kidPosition = kidPosition.wrapAround(this);
            while(this.animalsAtCount(kidPosition) != 0 || kidPosition.equals(position)){
                kidPosition = Vector2d.randInRectangle(new Vector2d(position.getX()-1,position.getY()-1),new Vector2d(position.getX()+1,position.getY()+1));
                kidPosition = kidPosition.wrapAround(this);
            }
            // mamy dobrą pozycje
            Animal kid = new Animal(this,kidPosition,this.initialEnergy,kidDna);
            this.animalHashMapList.put(kidPosition,kid);
            kid.addObserver(this);
            if(kidPosition.inJungle(this,kidPosition)){
                this.jungleAnimalCount += 1;
            }else{
                this.sawannaAnimalCount += 1;
            }

            return true;
        }
        Vector2d newPosition = Vector2d.randInRectangle(lowerLeftChildBoundary,higherRightChildBoundary);
        if(this.jungleAnimalCount + this.sawannaAnimalCount < this.mapSurface){ // TODO CZY ZWIERZE MOZE BYC TAM GDZI TRAWA!!!
            Vector2d kidPosition = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
            kidPosition = kidPosition.wrapAround(this);
            while(this.animalsAtCount(kidPosition) != 0 || kidPosition.equals(position)){
                kidPosition = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
                kidPosition = kidPosition.wrapAround(this);
            }
            // mamy dobrą pozycje
            Animal kid = new Animal(this,kidPosition,this.initialEnergy,kidDna);
            this.animalHashMapList.put(kidPosition,kid);
            kid.addObserver(this);
            // TODO !!! DRY !!!
            if(kidPosition.inJungle(this,kidPosition)){
                this.jungleAnimalCount += 1;
            }else{
                this.sawannaAnimalCount += 1;
            }
            return true;
        }
        return false;
    }

    public boolean placeFirstAnimal(){
        Vector2d position = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
        if(this.jungleAnimalCount + this.sawannaAnimalCount >= this.mapSurface){
            return false;
        }
        if(this.animalsAtCount(position)<2){
            Animal animal = new Animal(this,position,this.initialEnergy,new DNA());
            this.animalHashMapList.put(position,animal);
            animal.addObserver(this);
            if(position.inJungle(this,position)){
                this.jungleAnimalCount += 1;
            }else{
                this.sawannaAnimalCount += 1;
            }
            return true;
        }else{
            return placeFirstAnimal();
        }
    }

    public void placeGrass(){
        // najpierw kladziemy w jungli:
        Vector2d position = Vector2d.randInRectangle(this.jungleLoweLeft,this.jungleUpperRight);
        Grass grassAtPosition = grassHashMap.get(position);
        if(this.objectsInArea(this.jungleLoweLeft,this.jungleUpperRight) < this.jungleSurface){
            // TODO: DODAC WYJATKI !!!
            while(animalsAtCount(position) != 0 || grassAtPosition != null){ // na miejscu jest zwierze i jest trawa -> nowe miejsce
                position = Vector2d.randInRectangle(this.jungleLoweLeft,this.jungleUpperRight);
            }
            Grass grass = new Grass(this,position,this.initialEnergy);
            this.grassHashMap.put(position,grass);
            grass.addObserver(this);
        }
        // kladziemy na sawannie:
        // TODO POPRAWIDZ METODE KLADZENIA ZA POMOCA 4 PROSTOKATOW!!
        if(this.objectsInArea(this.lowerLeft,this.upperRight) - this.objectsInArea(this.jungleLoweLeft,this.jungleUpperRight) < this.mapSurface-this.jungleSurface){
            // TODO: DODAC WYJATKI !!!
            // SAWANNA:
            Vector2d position2 = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
            // szukamy miejsca w sawannie:
            while(animalsAtCount(position) > 0 || this.grassHashMap.get(position2) != null || position2.inJungle(this,position2)){
                position2 = Vector2d.randInRectangle(this.lowerLeft,this.upperRight);
            }
            // mamy dobra pozycje:
            Grass grass2 = new Grass(this,position2,this.initialEnergy);
            this.grassHashMap.put(position2,grass2);
            grass2.addObserver(this);
        }
    }

    public void eatAndRemoveGrass(Animal animal, Vector2d position){ // animal -> grass eater
        Grass grass = grassHashMap.get(position);
        animal.energy += grass.getEnergyValue();
        this.grassHashMap.remove(position);
        grass.removeObserver(this);
    }

    public void move(){
        LinkedList<Animal> animalsList = this.animalHashMapList.allElementsList();
        for(Animal animal : animalsList){

        }
    }
    // -----

    // HELPERS:
    public int objectsAround(Vector2d position){
        int res = 0;
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                if(!(x==0 && y==0)){
                    Vector2d nerbyVector = new Vector2d(position.getX()+x,position.getY()+y);
                    nerbyVector = nerbyVector.wrapAround(this);
                    if(animalsAtCount(nerbyVector) == 0 && this.grassHashMap.get(nerbyVector) != null){
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

    public int objectsInArea(Vector2d lowerLeft,Vector2d upperRight){
        int lowerX = lowerLeft.getX();
        int lowerY = lowerLeft.getY();
        int upperX = upperRight.getX();
        int upperY = upperRight.getY();
        int res = 0;
        for(int x = lowerX; x <= upperX; x++){
            for(int y = lowerY; y <= upperY; y++){
                if(this.animalsAtCount(new Vector2d(x,y)) > 0 || this.grassHashMap.get(new Vector2d(x,y)) != null){
                    res += 1;
                }
            }
        }
        return res;
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
            ((Animal) element).positionChanged(oldPosition,newPosition);
        }// dla trawy mamy remove, tam pozycja sie nie zmienia
    }

    // ---
}
