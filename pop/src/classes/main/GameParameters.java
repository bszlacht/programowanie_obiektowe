package classes.main;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.*;


public class GameParameters {
    public int width;   // jeśli public, to final
    public int height;
    public double jungleRatio;
    public int initialEnergy;
    public int moveEnergy;
    public int grassEnergy;
    public int startingAnimals;
    public int days;
    public int delay;

    public GameParameters(int width, int height, double jungleRatio, int initialEnergy, int moveEnergy, int grassEnergy, int startingAnimals, int days, int delay){
        this.width = width;
        this.height = height;
        this.jungleRatio = jungleRatio;
        this.initialEnergy = initialEnergy;
        this.moveEnergy = moveEnergy;
        this.grassEnergy = grassEnergy;
        this.startingAnimals = startingAnimals;
        this.days = days;
        this.delay = delay;
    }
    public GameParameters(GameParameters other){
        this.width = other.width;
        this.height = other.height;
        this.jungleRatio = other.jungleRatio;
        this.initialEnergy = other.initialEnergy;
        this.moveEnergy = other.moveEnergy;
        this.grassEnergy = other.grassEnergy;
        this.startingAnimals = other.startingAnimals;
        this.days = other.days;
        this.delay = other.delay;
    }

    public void readFromJson() throws FileNotFoundException{
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("JSON.json");
        try{
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            this.width = ((Long)jsonObject.get("width")).intValue();
            this.height = ((Long)jsonObject.get("height")).intValue();
            this.jungleRatio = (Double) jsonObject.get("jungleRatio");
            this.initialEnergy = ((Long)jsonObject.get("initialEnergy")).intValue();
            this.moveEnergy = ((Long)jsonObject.get("moveEnergy")).intValue();
            this.grassEnergy = ((Long)jsonObject.get("grassEnergy")).intValue();
            this.startingAnimals = ((Long)jsonObject.get("startingAnimals")).intValue();
            this.days = ((Long)jsonObject.get("days")).intValue();
            this.delay = ((Long)jsonObject.get("delay")).intValue();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
