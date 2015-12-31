package com.example.patrick.canvaswrite;

import android.graphics.Point;

/**
 * Created by Patrick on 3/24/2015.
 */
public class NodeMap {

    int[][] map = new int[5][5];

    Point centerPoint = new Point();

    public NodeMap() {
    }

    public NodeMap(int[][] data, Point cp) {

        centerPoint = cp;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = data[i][j];
            }
        }

    }

    public static NodeMap BuildNodeMap(Point cp){
        int[] row1 = new int[]{-1,-1,-1,-1,-1};
        int[] row2 = new int[]{-1,0,0,0,-1};
        int[] row3 = new int[]{-1,0,1,0,-1};
        int[] row4 = new int[]{-1,0,0,0,-1};
        int[] row5 = new int[]{-1,-1,-1,-1,-1};
        int[][] grid = new int[][]{row1,row2,row3,row4,row5};

        NodeMap nodeMap = new NodeMap(grid, cp);
        return nodeMap;
    }

}

