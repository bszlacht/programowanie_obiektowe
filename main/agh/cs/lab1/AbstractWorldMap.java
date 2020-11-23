package agh.cs.lab1;
import java.util.LinkedList;
import java.util.HashMap;
public abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
    protected final LinkedList<Animal> animalsList = new LinkedList<>();    // czy ta lista jest potrzebna?
    private final MapVisualizer map = new MapVisualizer(this);
    protected final HashMap<Vector2d, Animal> animalHashMap = new HashMap<>(); //

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean place(Animal animal){
        if(this.canMoveTo(animal.getPosition())){
            this.animalsList.add(animal);
            this.animalHashMap.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    public boolean canMoveTo(Vector2d position){
        // jesli sie miesci na mapie i pozycja jest pusta
        return !(objectAt(position) instanceof Animal);
    }

    public Object objectAt(Vector2d position) {
        return this.animalHashMap.get(position);
    }

    public abstract Vector2d getLowerLeft();    // lepiej protected, "świat" nie potrzebuje tej metody
    public abstract Vector2d getUpperRight();

    public String toString(){
        return map.draw(getLowerLeft(),getUpperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal element = (Animal)this.objectAt(oldPosition);
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition,element);
    }
}
