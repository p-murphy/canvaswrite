package com.example.patrick.canvaswrite;

import android.util.Log;

/**
 * Created by Patrick on 3/21/2015.
 */


// Converts user interaction on view to logic
// Used with CanvasView

public class CanvasMap {

    int[] point = new int[2]; // Holds touch coordinates

    public CanvasMap(){
    }

    // Prints coordinates to console for verification while debugging
    private void logXY(int x, int y){
        Log.i("X, Y: ", Integer.toString(x) + ", " + Integer.toString(y));
    }

    // Outwardly facing method that converts arbitrary screen coordinates into
    // NxN map of sectors which correspond to internal app logic
    protected int[] mapXY(int x, int y, int diameter, int resolution){

        point[0] = recursiveMap(x, diameter, resolution);
        point[1] = recursiveMap(y, diameter, resolution);

        //logXY(point[0], point[1]);

        return point;
    }

    // -The engine for mapXY method.-
    // Converts any screen location to internal logical location.
    // Finds answer for a map "N" sectors wide
    // after log2(N) recursions. (N must be a power of 2).
    // Ex: A map with a resolution of 16x16 sectors will locate one coordinate
    // after 4 steps, as log2(16) = 4.
    //
    // *coordinate is the real X or Y pixel location of a user's touch
    // *d is the diameter of our CanvasView, in pixels, after inflation.
    // *r is the resolution of our logical grid.
    //
    // This is called twice in mapXY, once to find X, and once to find Y.
    private int recursiveMap(int coordinate, int d, int r){

        if (r == 2){
            if(coordinate < d/2){
                return 1;
            }
            else{
                return 2;
            }
        }
        else{
            if(coordinate < d/2){
                return recursiveMap(coordinate, d/2, r/2);
            }
            else{
                return recursiveMap(coordinate - d/2, d/2, r/2) + r/2;
            }
        }
    }
}
