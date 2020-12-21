package classes.main;

import java.util.Arrays;
import java.util.Random;


public class DNA {
    private final int[] genes = new int [32];


    // creating an Animal:
    public DNA(){
        boolean validness = false;
        while(!validness){
            Random rand = new Random();
            // creating basic Animal Gen, not valid yet
            for(int i = 0; i < 32; i++){
                this.genes[i] = rand.nextInt(8);
            }
            //checking if valid:
            validness = this.isValid();
        }
        Arrays.sort(this.genes);
    }


    // giving birth to an Animal:
    public DNA(Animal father, Animal mother){
        Random rand = new Random();
        int[] groups = new int[32];  // 0,2,lub 3
        // 0 -> mother
        // 1 or 2 -> father
        for(int i = 0; i<32; i++){
            groups[i] = rand.nextInt(3);
        }
        for(int i = 0; i < 32; ++i){
            if(groups[i] == 0)
                this.genes[i] = mother.getGenes().getGene(i);
            else
                this.genes[i] = father.getGenes().getGene(i);
        }
        while(!this.isValid()){
            // we have to fix it
            // step 1, find missing genes:
            int[] missingGenes = new int[8];
            for(int i = 0; i < 32; i++){
                missingGenes[this.genes[i]] += 1;
            }
            // step 2, we change random missing gene to the missing genes
            for(int i = 0; i<8; i++){
                if(missingGenes[i] == 0){
                    int idx = rand.nextInt(32);
                    if(missingGenes[this.genes[idx]] > 1){ // if the swap doesnt make genes completly fall apart
                        this.fixParticularGene(idx,i);
                    }
                }
            }


        }
        Arrays.sort(this.genes);
    }

    private boolean isValid(){
        //checking if valid:
        int[] valid = new int[8]; // valid checker
        for(int i = 0; i < 8; i++){
            valid[i] = 0;
        }
        for(int i = 0; i < 32; i++){
            valid[this.genes[i]] += 1;
        }
        for(int i = 0; i < 8; i++) {
            if (valid[i] == 0) {
                return false;
            }
        }
        return true;
    }

    private void fixParticularGene(int idx, int targetGen){
        this.genes[idx] = targetGen;
    }
    public int[] getGenes(){return this.genes;}
    public int getGene(int i){
        return this.genes[i];
    }
    public int getRandomGene(){ // using for example to change direction
        return this.genes[(new Random()).nextInt(32)];
    }
    public String toString(){
        return Arrays.toString(this.genes);
    }
}
