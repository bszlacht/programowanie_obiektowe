package agh.cs.lab1;

public class SimulationEngine implements IEngine{
    public MapDirection initialDirection;   // do czego to służy?
    public MoveDirection[] move;   // te pola nie powinny być publiczne
    public IWorldMap map;
    public Vector2d[] initialAnimalPosition;

    public SimulationEngine(MoveDirection[] move, IWorldMap map, Vector2d[] initialAnimalPosition){
        this.initialDirection = MapDirection.NORTH;
        this.map = map;
        this.move = move;
        this.initialAnimalPosition = initialAnimalPosition;
        for(Vector2d pos:initialAnimalPosition){
            Animal cat = new Animal(this.map,pos);
            map.place(cat);
        }
    }
    public void run(){
        for(int i = 0; i < move.length ;i++){
            int animalNumber = i % initialAnimalPosition.length;
            Animal cat = (Animal) map.objectAt(initialAnimalPosition[animalNumber]);
            cat.move(this.move[i]);
            initialAnimalPosition[animalNumber] = cat.getPosition();
        }

    }
}
