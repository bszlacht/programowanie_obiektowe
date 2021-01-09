package classes.main;



public interface IPositionChangedObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element);  // czy potrzebujemy przekazywać nową pozycję, skoro przekazujemy cały element?
    void objectRemoved(Vector2d position, IMapElement element); // jak ta metoda się ma do nazwy interfejsu?
    void objectAdded(Vector2d position, IMapElement element);
}
