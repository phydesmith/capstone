package io.javasmithy.util;
/** Computes distance between two points.
 * @author Peter Hyde-Smith
 */
public class Distance {

    /** Computes the distance based on the distance between two points formula. This is used to find distance between row/column points for attack range.
     * @param sourceX source column
     * @param sourceY source row
     * @param targetX target column
     * @param targetY target row
     * @return the distance between two points.
     */
    public static double compute(int sourceX, int sourceY, int targetX, int targetY){
        double xDiff = targetX - sourceX;
        double yDiff = targetY - sourceY;
        double xDiffSquared = xDiff * xDiff;
        double yDiffSquared = yDiff * yDiff;
        double radicand = xDiffSquared + yDiffSquared;
        double result = Math.sqrt(radicand);
        return result;
    }
}
