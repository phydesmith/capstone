package io.javasmithy.util;

public class Distance {

    public static double compute(int sourceX, int sourceY, int targetX, int targetY){
        double xDiff = targetX - sourceX;
        double yDiff = targetY - sourceY;
        return Math.hypot(xDiff, yDiff);
    }
}
