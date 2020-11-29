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
            Animal cat = new Animal(this.map,pos);
            if(!map.place(cat)){
                System.out.print("nie udalo sie dodac ktoregos zwierzaka" + cat.getPosition() + cat.getDirection());
                // nie wiem czy chodzi o to ,czy o wyrzucenie wyjątku
            }
        }
    }

    public void run() throws IllegalArgumentException{
        for(int i = 0; i < move.length ;i++){
            int animalNumber = i % initialAnimalPosition.length;
            if(map.objectAt(initialAnimalPosition[animalNumber]) instanceof Animal){
                Animal cat = (Animal) map.objectAt(initialAnimalPosition[animalNumber]);
                cat.move(this.move[i]);
                initialAnimalPosition[animalNumber] = cat.getPosition();
            }
        }
        for(Vector2d pet: initialAnimalPosition){
            System.out.print(pet);
        }
    }
}
