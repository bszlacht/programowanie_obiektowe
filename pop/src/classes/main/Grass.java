package classes.main;

import java.util.LinkedList;

public class Grass implements IMapElement{
    private final Vector2d position;
    private final long energyValue;
    private final Map map;
    private final LinkedList<IPositionChangedObserver> observersList = new LinkedList<>();

    public Grass(Map map, Vector2d position, long energyValue){
        this.energyValue = energyValue;
        this.map = map;
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public long getEnergyValue(){
        return this.energyValue;
    }
    @Override
    public String toString(){
        return "*";
    }

    // metody observer
    public void addObserver(IPositionChangedObserver observer){
        this.observersList.add(observer);
    }
    public void removeObserver(IPositionChangedObserver observer) {
        this.observersList.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangedObserver observer : this.observersList){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }
}
