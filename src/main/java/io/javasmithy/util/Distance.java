package io.javasmithy.util;

public class Distance {
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
