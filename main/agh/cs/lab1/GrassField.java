package agh.cs.lab1;

import java.lang.Math;
import java.util.LinkedList;
import java.util.HashMap;


public class GrassField extends AbstractWorldMap {
    private final int grassCount;
    private final LinkedList<Grass> grassList = new LinkedList<>(); // lista trawy
    private final HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();

    public GrassField(int grassCount){
        this.grassCount = grassCount;
        // teraz dodajemy:
        for(int i = 0; i < grassCount; i++){
            boolean czySieUdalo = false;
            while(!czySieUdalo){ //
                czySieUdalo = this.addRandGrassInSquare();
            }
        }
    }

    private boolean addRandGrassInSquare() {
        Grass newGrass = Grass.randGrassInSquare(0,(int)Math.sqrt(this.grassCount*10));
        if(this.objectAt(newGrass.getPosition()) == null){
            this.grassHashMap.put(newGrass.getPosition(),newGrass);
            grassList.add(newGrass);
            return true;
        }
        return false;
    }

    @Override
    public Vector2d getUpperRight(){
        /*int maxx=0;
        int maxy=0;
        for(Animal test:animalsList){
            int x = test.getPosition().x;
            int y = test.getPosition().y;
            if(x >= maxx){
                maxx = x;
            }
            if(y >= maxy){
                maxy = y;
            }
        }
        for(Grass test:grassList){
            int x = test.getPosition().x;
            int y = test.getPosition().y;
            if(x >= maxx){
                maxx = x;
            }
            if(y >= maxy){
                maxy = y;
            }
        }
        return new Vector2d(maxx,maxy);*/
        // tu jest druga metoda, ta wyżej jest szybsza to jej nie usuwam


        Vector2d upperRight = new Vector2d(0,0);
        for(Animal animal: animalsList){
            Vector2d newPosition = animal.getPosition();
            upperRight = upperRight.upperRight(newPosition);
        }
        for(Grass grass: grassList){
            Vector2d newPosition = grass.getPosition();
            upperRight = upperRight.upperRight(newPosition);
        }
        return upperRight;
    }

    @Override
    public Vector2d getLowerLeft(){
        /*int miny=Integer.MAX_VALUE;
        int minx=Integer.MAX_VALUE;
        for(Animal test:animalsList){
            int x = test.getPosition().x;
            int y = test.getPosition().y;
            if(x<=minx){
                minx=x;
            }
            if(y<=miny){
                miny=y;
            }
        }
        for(Grass test:grassList){
            int x = test.getPosition().x;
            int y = test.getPosition().y;
            if(x<=minx){
                minx=x;
            }
            if(y<=miny){
                miny=y;
            }
        }
        return new Vector2d(minx,miny);*/
        //to samo co wyzej, wole tego nie usuwać
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for(Animal animal: animalsList){
            Vector2d newPosition = animal.getPosition();
            lowerLeft = lowerLeft.lowerLeft(newPosition);
        }
        for(Grass grass: grassList){
            Vector2d newPosition = grass.getPosition();
            lowerLeft = lowerLeft.lowerLeft(newPosition);
        }
        return lowerLeft;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object res = super.objectAt(position);
        if(res == null){
            res = this.grassHashMap.get(position);
        }
        return res;
    }
}
