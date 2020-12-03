package agh.cs.lab1;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final HashMap<Vector2d, Animal> animalHashMap = new HashMap<>();
    protected final MapBoundary boundary = new MapBoundary();   // po co to jest tutaj, skoro RectangularMap tego nie używa?
    private final MapVisualizer map = new MapVisualizer(this);

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean place(Animal animal) throws IllegalArgumentException {
        if (this.canMoveTo(animal.getPosition())) {
            this.animalHashMap.put(animal.getPosition(), animal);
            animal.addObserver(this);
            this.boundary.place(animal.getPosition());
            animal.addObserver(this.boundary);  // czemu MapBoundary nie dba samo o siebie?
            return true;
        } else {
            throw new IllegalArgumentException("Animal cannot be placed at position " + animal.getPosition().toString());
        }

    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public Object objectAt(Vector2d position) {
        return this.animalHashMap.get(position);
    }

    protected abstract Vector2d getLowerLeft();

    protected abstract Vector2d getUpperRight();

    public String toString() {
        return map.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal element = (Animal) this.objectAt(oldPosition);   // zwierzę już powinno być na nowej pozycji
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition, element);
        boundary.positionChanged(oldPosition, newPosition); // zwierzę ma notyfikować obserwatorów, a nie oni siebie nawzajem
    }
}
