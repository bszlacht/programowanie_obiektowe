package agh.cs.lab1;

import java.lang.Math;
import java.util.HashMap;



public class GrassField extends AbstractWorldMap implements IPositionChangeObserver {
    private final int grassCount;
    private final HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();

    public GrassField(int grassCount){
        this.grassCount = grassCount;
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
            this.boundary.place(newGrass.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public Vector2d getUpperRight(){
        return this.boundary.upperRight();
    }

    @Override
    public Vector2d getLowerLeft(){
        return this.boundary.lowerLeft();
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
