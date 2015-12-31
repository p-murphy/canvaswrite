package com.example.patrick.canvaswrite;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by Patrick on 3/24/2015.
 */

public class Character {

    public String identity;

    public Boolean isUpper;
    public Boolean isLower;

    public float right;
    public float wrong;

    public Stroke[] strokeArray;

    private Point[] u_a1 = {
            new Point(4, 14),
            new Point(9, 3),
            new Point(13, 15)
    };

    private Point[] u_a2 = {
            new Point(5, 11),
            new Point(12, 11)
    };

    private Point[][] u_aPointArray = {
            u_a1, u_a2
    };

    private Point[] u_b1 = {
            new Point(5, 3),
            new Point(5, 14)
    };

    private Point[] u_b2 = {
            new Point(5, 3),
            new Point(10, 3),
            new Point(12, 5),
            new Point(10, 8),
            new Point(5, 8),
            new Point(10, 9),
            new Point(12, 12),
            new Point(10, 14),
            new Point(5, 14)
    };


    private Point[][] u_bPointArray = {
            u_b1, u_b2
    };

    private Point[] u_c1 = {
            new Point(13, 3),
            new Point(9, 2),
            new Point(5, 5),
            new Point(4, 9),
            new Point(6, 13),
            new Point(10, 14),
            new Point(13, 13)
    };

    private Point[][] u_cPointArray = {
            u_c1
    };

    public Character() {
    }

    public Character(String id, Stroke[] sa, Boolean u, Boolean l, float r, float w) {
        identity = id;
        strokeArray = sa;
        isUpper = u;
        isLower = l;
        right = r;
        wrong = w;
    }

    public Character(String id, Boolean u, Boolean l, float r, float w, Stroke[] sa) {
        identity = id;
        isUpper = u;
        isLower = l;
        right = r;
        wrong = w;
        strokeArray = sa;
    }


    public Character BuildCharacter(String id, Stroke[] sa, Boolean u,
                                    Boolean l, float r, float w) {

        return new Character(id, sa, u, l, r, w);

    }

    public Character BuildGenericCharacter(String id, Boolean u,
                                           Boolean l, float r, float w) {

        Point[][] pa;

        if (id.equals("A")) {
            pa = u_aPointArray;
        } else if (id.equals("B")) {
            pa = u_bPointArray;
        } else if (id.equals("C")) {
            pa = u_cPointArray;
        } else {
            pa = u_bPointArray;
        }

        Stroke[] sa = BuildStrokeArray(id, pa);

        return new Character(id, sa, u, l, r, w);
    }


    private Stroke[] BuildStrokeArray(String id, Point[][] pa) {

        strokeArray = new Stroke[pa.length];
        Boolean isFirst;
        Boolean isLast;

        for (int i = 0; i < strokeArray.length; i++) {
            if (i == 0) {
                isFirst = true;
            } else {
                isFirst = false;
            }

            if (i + 1 == strokeArray.length) {
                isLast = true;
            } else {
                isLast = false;
            }

            Node[] nodeArray = new Node[pa[i].length];
            Boolean isStart;
            Boolean isEnd;

            for (int j = 0; j < nodeArray.length; j++) {
                if (j == 0) {
                    isStart = true;
                } else {
                    isStart = false;
                }

                if (j + 1 == nodeArray.length) {
                    isEnd = true;
                } else {
                    isEnd = false;
                }

                int pointX = pa[i][j].x;
                int pointY = pa[i][j].y;

                int[] location = {pointX, pointY};

                Node node = new Node(location, NodeMap.BuildNodeMap(pa[i][j]), isStart, isEnd);
                nodeArray[j] = node;

            }

            strokeArray[i] = Stroke.BuildStroke(nodeArray, isFirst, isLast);
        }

        return strokeArray;

    }

    public int[] newEvaluateTouch(int[] coordinates, Atom[] atoms, int highestValue){

        int[] returnedValues = new int[]{0,0};
        int value = 0;
        int highest_value = highestValue;

        for(int i = 0; i < atoms.length; i++)
        {
            if (coordinates[0] == atoms[i].location[0] && coordinates[1] == atoms[i].location[1])
            {
                Log.i("At MAP coordinates", Integer.toString(coordinates[0])
                                    + "," + Integer.toString(coordinates[1]));
                Log.i("At NODE coordinates", Integer.toString(atoms[i].location[0])
                        + "," + Integer.toString(atoms[i].location[1]));
                Log.i("Value", Integer.toString(atoms[i].value));
                value = atoms[i].value;

                if(value > highest_value)
                {
                    highest_value = value;
                    Log.i("We are making highest_value ", Integer.toString(value));
                }

                if(value == -1)
                {
                    Log.i("We have reached"," the boundary of the node");
                    returnedValues[0] = -1;
                    returnedValues[1] = highest_value;
                    return returnedValues;
                }

                if(value == -9)
                {
                    Log.i("We have reached"," the node gate");
                    Log.i("highest_value is ", Integer.toString(highest_value));
                    returnedValues[0] = -9;
                    returnedValues[1] = highest_value;
                    return returnedValues;
                }

                Log.i("AT THE END OF THE ALGO highest_value is ", Integer.toString(highest_value));

            }
        }

        returnedValues[0] = -5;
        returnedValues[1] = highest_value;
        return returnedValues;
    }

    public void evaluateTouch(Character c, int[] co, int strokePointer, int nodePointer) {

        if (strokePointer == c.strokeArray.length) {
            Log.i("########################", " END #########");
            return;
        }

        if (co[0] == 1 || co[1] == 1 || co[0] == 16 || co[1] == 16) {
            Log.i("FAIL", "!!!!!!!!!!!!!!!!!!");
        }

        NodeMap currentNodeMap = c.strokeArray[strokePointer].nodeArray[nodePointer].nodeMap;
        Point centerOfNodeMap = currentNodeMap.centerPoint;


/*
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {

                if(co[0] == currentNodeMap[i])
                {

                }

            }
        }
*/

        if (c.strokeArray[strokePointer].nodeArray[nodePointer].location[0] == co[0]
                && c.strokeArray[strokePointer].nodeArray[nodePointer].location[1] == co[1]) {

            nodePointer++;
            Log.i("SUCCESS", "!!!!!!!!!!!!!!!!!!");
            Log.i("CHAR IS ", c.identity);
        }

        if (nodePointer == c.strokeArray[strokePointer].nodeArray.length) {
            strokePointer++;
            nodePointer = 0;
        }
    }

    public Character newUpperA() {

        return new Character("A", true, false, 0, 0, new Stroke[]{
                new Stroke(true, false, new Node[]{
                        new Node(true, false, new Atom[]{
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2)
                        }),
                        new Node(false, false, new Atom[]{
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2)
                        }),
                        new Node(false, true, new Atom[]{
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2)
                        })
                }),
                new Stroke(false, true, new Node[]{
                        new Node(true, false, new Atom[]{
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2)
                        }),
                        new Node(false, true, new Atom[]{
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2),
                                new Atom(new int[]{0, 0}, -2)
                        })
                })
        });
    }

    public Character doABarrelRollA() {

        return new Character("A", true, false, 0, 0, new Stroke[]{
                new Stroke(true, false, new Node[]{
                        new Node(true, false, new Atom[]{
                                new Atom(new int[]{2,12}, -1),
                                new Atom(new int[]{3,12}, -1),
                                new Atom(new int[]{4,12}, -9),
                                new Atom(new int[]{5,12}, -9),
                                new Atom(new int[]{6,12}, -1),
                                new Atom(new int[]{2,13}, -1),
                                new Atom(new int[]{3,13}, 1),
                                new Atom(new int[]{4,13}, 0),
                                new Atom(new int[]{5,13}, 0),
                                new Atom(new int[]{6,13}, -1),
                                new Atom(new int[]{2,14}, -1),
                                new Atom(new int[]{3,14}, 1),
                                new Atom(new int[]{4,14}, 2),
                                new Atom(new int[]{5,14}, 0),
                                new Atom(new int[]{6,14}, -1),
                                new Atom(new int[]{2,15}, -1),
                                new Atom(new int[]{3,15}, 1),
                                new Atom(new int[]{4,15}, 1),
                                new Atom(new int[]{5,15}, 1),
                                new Atom(new int[]{6,15}, -1),
                                new Atom(new int[]{2,16}, -1),
                                new Atom(new int[]{3,16}, -1),
                                new Atom(new int[]{4,16}, -1),
                                new Atom(new int[]{5,16}, -1),
                                new Atom(new int[]{6,16}, -1)
                        }),
                        new Node(false, false, new Atom[]{
                                new Atom(new int[]{3,9}, -1),
                                new Atom(new int[]{4,9}, -1),
                                new Atom(new int[]{5,9}, -9),
                                new Atom(new int[]{6,9}, -9),
                                new Atom(new int[]{7,9}, -1),
                                new Atom(new int[]{3,10}, -1),
                                new Atom(new int[]{4,10}, 0),
                                new Atom(new int[]{5,10}, 0),
                                new Atom(new int[]{6,10}, 0),
                                new Atom(new int[]{7,10}, -1),
                                new Atom(new int[]{3,11}, -1),
                                new Atom(new int[]{4,11}, 1),
                                new Atom(new int[]{5,11}, 2),
                                new Atom(new int[]{6,11}, 1),
                                new Atom(new int[]{7,11}, -1),
                                new Atom(new int[]{3,12}, -1),
                                new Atom(new int[]{4,12}, 0),
                                new Atom(new int[]{5,12}, 0),
                                new Atom(new int[]{6,12}, 0),
                                new Atom(new int[]{7,12}, -1),
                                new Atom(new int[]{3,13}, -1),
                                new Atom(new int[]{4,13}, -1),
                                new Atom(new int[]{5,13}, -1),
                                new Atom(new int[]{6,13}, -1),
                                new Atom(new int[]{7,13}, -1)
                        }),
                        new Node(false, false, new Atom[]{
                                new Atom(new int[]{4,6}, -1),
                                new Atom(new int[]{5,6}, -1),
                                new Atom(new int[]{6,6}, -9),
                                new Atom(new int[]{7,6}, -9),
                                new Atom(new int[]{8,6}, -1),
                                new Atom(new int[]{4,7}, -1),
                                new Atom(new int[]{5,7}, 0),
                                new Atom(new int[]{6,7}, 0),
                                new Atom(new int[]{7,7}, 0),
                                new Atom(new int[]{8,7}, -1),
                                new Atom(new int[]{4,8}, -1),
                                new Atom(new int[]{5,8}, 1),
                                new Atom(new int[]{6,8}, 2),
                                new Atom(new int[]{7,8}, 1),
                                new Atom(new int[]{8,8}, -1),
                                new Atom(new int[]{4,9}, -1),
                                new Atom(new int[]{5,9}, 0),
                                new Atom(new int[]{6,9}, 0),
                                new Atom(new int[]{7,9}, 0),
                                new Atom(new int[]{8,9}, -1),
                                new Atom(new int[]{4,10}, -1),
                                new Atom(new int[]{5,10}, -1),
                                new Atom(new int[]{6,10}, -1),
                                new Atom(new int[]{7,10}, -1),
                                new Atom(new int[]{8,10}, -1)
                        }),
                        new Node(false, false, new Atom[]{
                                new Atom(new int[]{5,3}, -1),
                                new Atom(new int[]{6,3}, -1),
                                new Atom(new int[]{7,3}, -1),
                                new Atom(new int[]{8,3}, -1),
                                new Atom(new int[]{9,3}, -1),
                                new Atom(new int[]{5,4}, -1),
                                new Atom(new int[]{6,4}, 0),
                                new Atom(new int[]{7,4}, 1),
                                new Atom(new int[]{8,4}, -9),
                                new Atom(new int[]{9,4}, -1),
                                new Atom(new int[]{5,5}, -1),
                                new Atom(new int[]{6,5}, 0),
                                new Atom(new int[]{7,5}, 2),
                                new Atom(new int[]{8,5}, 1),
                                new Atom(new int[]{9,5}, -1),
                                new Atom(new int[]{5,6}, -1),
                                new Atom(new int[]{6,6}, 0),
                                new Atom(new int[]{7,6}, 0),
                                new Atom(new int[]{8,6}, 0),
                                new Atom(new int[]{9,6}, -1),
                                new Atom(new int[]{5,7}, -1),
                                new Atom(new int[]{6,7}, -1),
                                new Atom(new int[]{7,7}, -1),
                                new Atom(new int[]{8,7}, -1),
                                new Atom(new int[]{9,7}, -1)
                        }),
                        new Node(false, false, new Atom[]{
                                new Atom(new int[]{7,1}, -1),
                                new Atom(new int[]{8,1}, -1),
                                new Atom(new int[]{9,1}, -1),
                                new Atom(new int[]{10,1}, -1),
                                new Atom(new int[]{11,1}, -1),
                                new Atom(new int[]{7,2}, -1),
                                new Atom(new int[]{8,2}, 0),
                                new Atom(new int[]{9,2}, 0),
                                new Atom(new int[]{10,2}, 0),
                                new Atom(new int[]{11,2}, -1),
                                new Atom(new int[]{7,3}, -1),
                                new Atom(new int[]{8,3}, 0),
                                new Atom(new int[]{9,3}, 2),
                                new Atom(new int[]{10,3}, 0),
                                new Atom(new int[]{11,3}, -1),
                                new Atom(new int[]{7,4}, -1),
                                new Atom(new int[]{8,4}, 0),
                                new Atom(new int[]{9,4}, 2),
                                new Atom(new int[]{10,4}, 0),
                                new Atom(new int[]{11,4}, -1),
                                new Atom(new int[]{7,5}, -1),
                new Atom(new int[]{8,5}, -1),
                new Atom(new int[]{9,5}, -9),
                new Atom(new int[]{10,5}, -9),
                new Atom(new int[]{11,5}, -1)
        }),
        new Node(false, false, new Atom[]{
                new Atom(new int[]{8,4}, -1),
                new Atom(new int[]{9,4}, -1),
                new Atom(new int[]{10,4}, -1),
                new Atom(new int[]{11,4}, -1),
                new Atom(new int[]{12,4}, -1),
                new Atom(new int[]{8,5}, -1),
                new Atom(new int[]{9,5}, 0),
                new Atom(new int[]{10,5}, 0),
                new Atom(new int[]{11,5}, 0),
                new Atom(new int[]{12,5}, -1),
                new Atom(new int[]{8,6}, -1),
                new Atom(new int[]{9,6}, 1),
                new Atom(new int[]{10,6}, 2),
                new Atom(new int[]{11,6}, 1),
                new Atom(new int[]{12,6}, -1),
                new Atom(new int[]{8,7}, -1),
                new Atom(new int[]{9,7}, 0),
                new Atom(new int[]{10,7}, 0),
                new Atom(new int[]{11,7}, 0),
                new Atom(new int[]{12,7}, -1),
                new Atom(new int[]{8,8}, -1),
                new Atom(new int[]{9,8}, -1),
                new Atom(new int[]{10,8}, -9),
                new Atom(new int[]{11,8}, -9),
                new Atom(new int[]{12,8}, -1)
        }),
                new Node(false, false, new Atom[]{
                        new Atom(new int[]{9,7}, -1),
                        new Atom(new int[]{10,7}, -1),
                        new Atom(new int[]{11,7}, -1),
                        new Atom(new int[]{12,7}, -1),
                        new Atom(new int[]{13,7}, -1),
                        new Atom(new int[]{9,8}, -1),
                        new Atom(new int[]{10,8}, 0),
                        new Atom(new int[]{11,8}, 0),
                        new Atom(new int[]{12,8}, 0),
                        new Atom(new int[]{13,8}, -1),
                        new Atom(new int[]{9,9}, -1),
                        new Atom(new int[]{10,9}, 1),
                        new Atom(new int[]{11,9}, 2),
                        new Atom(new int[]{12,9}, 1),
                        new Atom(new int[]{13,9}, -1),
                        new Atom(new int[]{9,10}, -1),
                        new Atom(new int[]{10,10}, 0),
                        new Atom(new int[]{11,10}, 0),
                        new Atom(new int[]{12,10}, 0),
                        new Atom(new int[]{13,10}, -1),
                        new Atom(new int[]{9,11}, -1),
                        new Atom(new int[]{10,11}, -1),
                        new Atom(new int[]{11,11}, -9),
                        new Atom(new int[]{12,11}, -9),
                        new Atom(new int[]{13,11}, -1)
                }),
                new Node(false, false, new Atom[]{
                        new Atom(new int[]{10,10}, -1),
                        new Atom(new int[]{11,10}, -1),
                        new Atom(new int[]{12,10}, -1),
                        new Atom(new int[]{13,10}, -1),
                        new Atom(new int[]{14,10}, -1),
                        new Atom(new int[]{10,11}, -1),
                        new Atom(new int[]{11,11}, 0),
                        new Atom(new int[]{12,11}, 0),
                        new Atom(new int[]{13,11}, 0),
                        new Atom(new int[]{14,11}, -1),
                        new Atom(new int[]{10,12}, -1),
                        new Atom(new int[]{11,12}, 0),
                        new Atom(new int[]{12,12}, 2),
                        new Atom(new int[]{13,12}, 1),
                        new Atom(new int[]{14,12}, -1),
                        new Atom(new int[]{10,13}, -1),
                        new Atom(new int[]{11,13}, 0),
                        new Atom(new int[]{12,13}, 1),
                        new Atom(new int[]{13,13}, -9),
                        new Atom(new int[]{14,13}, -1),
                        new Atom(new int[]{10,14}, -1),
                        new Atom(new int[]{11,14}, -1),
                        new Atom(new int[]{12,14}, -1),
                        new Atom(new int[]{13,14}, -1),
                        new Atom(new int[]{14,14}, -1)
                }),
                new Node(false, true, new Atom[]{
                        new Atom(new int[]{11,12}, -1),
                        new Atom(new int[]{12,12}, -1),
                        new Atom(new int[]{13,12}, -1),
                        new Atom(new int[]{14,12}, -1),
                        new Atom(new int[]{15,12}, -1),
                        new Atom(new int[]{11,13}, -1),
                        new Atom(new int[]{12,13}, 0),
                        new Atom(new int[]{13,13}, 0),
                        new Atom(new int[]{14,13}, 1),
                        new Atom(new int[]{15,13}, -1),
                        new Atom(new int[]{11,14}, -1),
                        new Atom(new int[]{12,14}, 0),
                        new Atom(new int[]{13,14}, 2),
                        new Atom(new int[]{14,14}, -9),
                        new Atom(new int[]{15,14}, -1),
                        new Atom(new int[]{11,15}, -1),
                        new Atom(new int[]{12,15}, 1),
                        new Atom(new int[]{13,15}, -9),
                        new Atom(new int[]{14,15}, 0),
                        new Atom(new int[]{15,15}, -1),
                        new Atom(new int[]{11,16}, -1),
                        new Atom(new int[]{12,16}, -1),
                        new Atom(new int[]{13,16}, -1),
                        new Atom(new int[]{14,16}, -1),
                        new Atom(new int[]{15,16}, -1)
                })
        }),
        new Stroke(false, true, new Node[]{
                new Node(true, false, new Atom[]{
                        new Atom(new int[]{3,9}, -1),
                        new Atom(new int[]{4,9}, -1),
                        new Atom(new int[]{5,9}, -1),
                        new Atom(new int[]{6,9}, -1),
                        new Atom(new int[]{7,9}, -1),
                        new Atom(new int[]{3,10}, -1),
                        new Atom(new int[]{4,10}, 1),
                        new Atom(new int[]{5,10}, 1),
                        new Atom(new int[]{6,10}, 0),
                        new Atom(new int[]{7,10}, -9),
                        new Atom(new int[]{3,11}, -1),
                        new Atom(new int[]{4,11}, 1),
                        new Atom(new int[]{5,11}, 2),
                        new Atom(new int[]{6,11}, 2),
                new Atom(new int[]{7,11}, -9),
                new Atom(new int[]{3,12}, -1),
                new Atom(new int[]{4,12}, 1),
                new Atom(new int[]{5,12}, 1),
                new Atom(new int[]{6,12}, 0),
                new Atom(new int[]{7,12}, -9),
                new Atom(new int[]{3,13}, -1),
                new Atom(new int[]{4,13}, -1),
                new Atom(new int[]{5,13}, -1),
                new Atom(new int[]{6,13}, -1),
                new Atom(new int[]{7,13}, -1)
        }),
        new Node(false, false, new Atom[]{
                new Atom(new int[]{6,9}, -1),
                new Atom(new int[]{7,9}, -1),
                new Atom(new int[]{8,9}, -1),
                new Atom(new int[]{9,9}, -1),
                new Atom(new int[]{10,9}, -1),
                new Atom(new int[]{6,10}, -1),
                new Atom(new int[]{7,10}, 0),
                new Atom(new int[]{8,10}, 1),
                new Atom(new int[]{9,10}, 0),
                new Atom(new int[]{10,10}, -9),
                new Atom(new int[]{6,11}, -1),
                new Atom(new int[]{7,11}, 0),
                new Atom(new int[]{8,11}, 2),
                new Atom(new int[]{9,11}, 0),
                new Atom(new int[]{10,11}, -9),
                new Atom(new int[]{6,12}, -1),
                new Atom(new int[]{7,12}, 0),
                new Atom(new int[]{8,12}, 1),
                new Atom(new int[]{9,12}, 0),
                new Atom(new int[]{10,12}, -9),
                new Atom(new int[]{6,13}, -1),
                new Atom(new int[]{7,13}, -1),
                new Atom(new int[]{8,13}, -1),
                new Atom(new int[]{9,13}, -1),
                new Atom(new int[]{10,13}, -1)
        }),
                new Node(false, false, new Atom[]{
                        new Atom(new int[]{9,9}, -1),
                        new Atom(new int[]{10,9}, -1),
                        new Atom(new int[]{11,9}, -1),
                        new Atom(new int[]{12,9}, -1),
                        new Atom(new int[]{13,9}, -1),
                        new Atom(new int[]{9,10}, -1),
                        new Atom(new int[]{10,10}, 0),
                        new Atom(new int[]{11,10}, 1),
                        new Atom(new int[]{12,10}, -9),
                        new Atom(new int[]{13,10}, -1),
                        new Atom(new int[]{9,11}, -1),
                        new Atom(new int[]{10,11}, 0),
                        new Atom(new int[]{11,11}, 2),
                        new Atom(new int[]{12,11}, -9),
                        new Atom(new int[]{13,11}, -1),
                        new Atom(new int[]{9,12}, -1),
                        new Atom(new int[]{10,12}, 0),
                        new Atom(new int[]{11,12}, 1),
                        new Atom(new int[]{12,12}, -9),
                        new Atom(new int[]{13,12}, -1),
                        new Atom(new int[]{9,13}, -1),
                        new Atom(new int[]{10,13}, -1),
                        new Atom(new int[]{11,13}, -1),
                        new Atom(new int[]{12,13}, -1),
                        new Atom(new int[]{13,13}, -1)
                }),
                new Node(false, true, new Atom[]{
                        new Atom(new int[]{10,9}, -1),
                        new Atom(new int[]{11,9}, -1),
                        new Atom(new int[]{12,9}, -1),
                        new Atom(new int[]{13,9}, -1),
                        new Atom(new int[]{14,9}, -1),
                        new Atom(new int[]{10,10}, -1),
                        new Atom(new int[]{11,10}, 0),
                        new Atom(new int[]{12,10}, 0),
                        new Atom(new int[]{13,10}, -9),
                        new Atom(new int[]{14,10}, -1),
                        new Atom(new int[]{10,11}, -1),
                        new Atom(new int[]{11,11}, 0),
                        new Atom(new int[]{12,11}, 2),
                        new Atom(new int[]{13,11}, -9),
                        new Atom(new int[]{14,11}, -1),
                        new Atom(new int[]{10,12}, -1),
                        new Atom(new int[]{11,12}, 0),
                        new Atom(new int[]{12,12}, 0),
                        new Atom(new int[]{13,12}, -9),
                        new Atom(new int[]{14,12}, -1),
                        new Atom(new int[]{10,13}, -1),
                        new Atom(new int[]{11,13}, -1),
                        new Atom(new int[]{12,13}, -1),
                        new Atom(new int[]{13,13}, -1),
                        new Atom(new int[]{14,13}, -1)
                })
        })
        });

    }

}
