package agh.cs.lab1;

import java.util.LinkedList;

public class AbstractWorldMap implements IWorldMap {
    protected LinkedList<Animal> animalsList = new LinkedList<>();  // to może być finalne


    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.animalsList.add(animal);
            return true;
        }
        return false;
    }

    public boolean canMoveTo(Vector2d position) {
        // jesli sie miesci na mapie i pozycja jest pusta
        return (objectAt(position) == null || objectAt(position) instanceof Grass); // <=> !(objectAt instanceof Animal)
    }

    public Object objectAt(Vector2d position) {
        Object res = null;
        for (Animal test : this.animalsList) {
            if (test.getPosition().equals(position)) {
                res = test;
            }
        }
        return res;
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {   // błędny nagłówek toString
        MapVisualizer map = new MapVisualizer(this);    // myląca nazwa zmiennej + nie trzeba tego tworzyć co wywołanie
        return map.draw(lowerLeft, upperRight);
    }
}
