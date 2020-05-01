package io.javasmithy.util;

import java.util.Random;
import java.util.Arrays;

/**Static class used to consolidate Random instance into one as to not get duplicate numbers from multiple instances.
* @author Peter Hyde-Smith
*/
public class Generator{
    /** Instance of Random to be used across entire program.
     *
     */
    private static final Random random = new Random();

    /** Generates a number between 1 and the inclusive bound to simulate dice rolls.
     * @param bound represents the 'sides' of die to roll
     * @param qty represents the 'number' of dice to roll
     * @return the roll
     */
    public static int generate(int bound, int qty){
        int total = 0;
        for (int i = 0; i < qty; i++){
            total += random.nextInt(bound)+1; 
        }
        return total;
    }

    /** This method simulates rolling 4d6, and dropping the lowest score. Used in character creation.
     * @return a score between 3-18
     */
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