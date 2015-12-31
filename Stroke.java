package com.example.patrick.canvaswrite;

/**
 * Created by Patrick on 3/24/2015.
 */
public class Stroke {

    public Node[] nodeArray;

    public Boolean isFirst;
    public Boolean isLast;

    //public int nodeCount = nodeArray.length;

    public Stroke(){
    }

    public Stroke(Node[] n, Boolean f, Boolean l){

        nodeArray = n;
        isFirst = f;
        isLast = l;

    }

    public Stroke(Boolean f, Boolean l, Node[] n){

        isFirst = f;
        isLast = l;
        nodeArray = n;

    }

    public static Stroke BuildStroke(Node[] n, Boolean f, Boolean l){
        return new Stroke(n, f, l);
    }

}
