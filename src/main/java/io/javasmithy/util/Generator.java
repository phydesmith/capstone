package io.javasmithy.util;

import java.util.Random;
import java.util.Arrays;

public class Generator{
    private static final Random random = new Random();

    public static int generate(int bound, int qty){
        int total = 0;
        for (int i = 0; i < qty; i++){
            total += random.nextInt(bound)+1; 
        }
        return total;
        //  Post-Condition
        //  Generates a random number between 1 and Bound
    }

    public static int generateAbilityScore(){
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++){
            arr[i] = generate(6, 1);
        }
        Arrays.sort(arr);
        int total = 0;
        for (int i = 1; i <= 3; i++){
            total+= arr[i];
        }
        return total;
    }

}