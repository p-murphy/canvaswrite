package com.example.patrick.canvaswrite;

/**
 * Created by Patrick on 3/24/2015.
 */
public class Node {

    int[] location;
    Boolean isStart;
    Boolean isEnd;
    NodeMap nodeMap;

    Atom[] atomArray;

    public Node(){
    }

    public Node(int[] l, NodeMap nm, Boolean s, Boolean e){

        location = l;
        nodeMap = nm;
        isStart = s;
        isEnd = e;

    }

    public Node(int[] l, Atom[] aa, Boolean s, Boolean e){

        location = l;
        atomArray = aa;
        isStart = s;
        isEnd = e;

    }

    public Node(Boolean is, Boolean ie, Atom[] aa){

        atomArray = aa;
        isStart = is;
        isEnd = ie;

    }

}
