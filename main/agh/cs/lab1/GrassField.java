package agh.cs.lab1;

import java.lang.Math;
import java.util.LinkedList;



public class GrassField extends AbstractWorldMap {
    private final int grassCount;
    private final LinkedList<Grass> grassList = new LinkedList<>();



    public GrassField(int grassCount){
        this.grassCount = grassCount;


        // teraz dodajemy:
        for(int i = 0; i < grassCount; i++){
            boolean flag = false;
            while(!flag){ //
                flag = this.addRandGrassInSquare();
            }
        }
    }

    private boolean addRandGrassInSquare() {
        Grass newGrass = Grass.randGrassInSquare(0,(int)Math.sqrt(this.grassCount*10));
        for(Grass test:this.grassList){
            if(test.getPosition().equals(newGrass.getPosition())){
                // jest juz zajete pole, wiec powtorzymy
                return false;
            }
        }
        this.grassList.add(newGrass);
        return true;
    }


    public String toString(){
        int maxx=0;
        int maxy=0;
        int miny=Integer.MAX_VALUE;
        int minx=Integer.MAX_VALUE;
        for(Animal test:animalsList){
            int x = test.getPosition().x;
            int y = test.getPosition().y;
            if(x >= maxx){
                maxx = x;
            }
            if(x<=minx){
                minx=x;
            }
            if(y >= maxy){
                maxy = y;
            }
            if(y<=miny){
                miny=y;
            }
        }
        for(Grass test:grassList){
            int x = test.getPosition().x;
            int y = test.getPosition().y;
            if(x >= maxx){
                maxx = x;
            }
            if(x<=minx){
                minx=x;
            }
            if(y >= maxy){
                maxy = y;
            }
            if(y<=miny){
                miny=y;
            }
        }

        Vector2d lowerLeft = new Vector2d(minx,miny);
        Vector2d upperRight = new Vector2d(maxx,maxy);
        return super.toString(lowerLeft,upperRight);
        // to mi tez kupki trawy wyświetla, chce mieć całą mape po prostu widoczną
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        // jesli sie miesci na mapie i pozycja jest pusta
        return super.canMoveTo(position) ;
    }


    @Override
    public boolean place(Animal animal){
        return super.place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object res = super.objectAt(position);
        if(res == null){
            for (Grass test : this.grassList) {
                if (test.getPosition().equals(position)) {
                    res = test;
                }
            }
        }
        return res;
    }
}
