package com.example.patrick.canvaswrite;

/**
 * Created by Patrick on 4/7/2015.
 */

public class Atom {

    int[] location;
    int value = -2;

    public Atom(){
    }

    public Atom(int[] l){

        location = l;
    }

    public Atom(int[] l, int v){

        location = l;
        value = v;
    }

}
