package agh.cs.lab1;

public class SimulationEngine implements IEngine{
    public MoveDirection[] move;
    private final IWorldMap map;
    private final Vector2d[] initialAnimalPosition;

    public SimulationEngine(MoveDirection[] move, IWorldMap map, Vector2d[] initialAnimalPosition){
        this.map = map;
        this.move = move;
        this.initialAnimalPosition = initialAnimalPosition;
        for(Vector2d pos:initialAnimalPosition){
            Animal cat = new Animal(this.map,pos);  // czemu cat?
            map.place(cat); // a jeśli się nie uda dodać?
        }
    }
    public void run(){
        for(int i = 0; i < move.length ;i++){
            int animalNumber = i % initialAnimalPosition.length;
            if(map.objectAt(initialAnimalPosition[animalNumber]) instanceof Animal){
                Animal cat = (Animal) map.objectAt(initialAnimalPosition[animalNumber]);
                cat.move(this.move[i]);
                initialAnimalPosition[animalNumber] = cat.getPosition();
            }

        }

    }
}
