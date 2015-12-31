package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class NodeEditFragment extends Fragment implements Button.OnClickListener{


    private Button mButton; //Add at the top of the fragment

    private static final String ARG_PARAM1 = "param1";
    private String characterKey;
    private String mParam1;
    public NodeEditView nodeEditCanvas;
    private OnFragmentInteractionListener mListener;

    public int[] mode = {-3};

    public Atom[] atomArray;

    public Character character;

    public Canvas bitmapCanvas;

    public static NodeEditFragment newInstance(String param1) {
        //p1 = param1;

        NodeEditFragment fragment = new NodeEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        Log.i("WE ARE IN ", "CONSTRUCTOR WITH ARGUMENTS");
        Log.i("param1 is", param1);
        return fragment;
    }

    public NodeEditFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

            characterKey = getArguments().getString("Letter");
            mode = getArguments().getIntArray("Mode");
            //character = Character.BuildUpperA(characterKey, true, false, 0, 0);
        }
        catch(Exception e){
            String err = (e.getMessage()==null)?"Error in Fragment":e.getMessage();
            Log.e("Fragment OnCreate:", err);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        characterKey = getArguments().getString("Letter");

        if (characterKey == null) {
            Log.i("NODE EDIT FRAGMENT OnCreateView ", "characterKey IS NULL!!!!!!!!");
        }
        else {
            Log.i("NODE EDIT FRAGMENT OnCreateView characterKey is ", characterKey);

        }

        View view = null;
        view = inflater.inflate(R.layout.node_edit_canvas, container, false);

        nodeEditCanvas = new NodeEditView(this.getActivity().getApplicationContext(), characterKey, mode);

        final float scale = getActivity().getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (300 * scale + 0.5f);



        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(pixels, pixels);
        nodeEditCanvas.setLayoutParams(layoutParams);

        Log.i("FrameLayout width in CanvasFragment ", Integer.toString(pixels));
        Log.i("FrameLayout height in CanvasFragment ", Integer.toString(pixels));

        FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.ViewContainer);
        frameLayout.addView(nodeEditCanvas);

        Log.i("nodeEditCanvas width in CanvasFragment ", Integer.toString(nodeEditCanvas.getWidth()));
        Log.i("nodeEditCanvas height in CanvasFragment ", Integer.toString(nodeEditCanvas.getHeight()));

        //mButton = (Button) view.findViewById(R.id.button);
        //mCanvas = (CanvasView) view.findViewById(R.id.view);
        //mCanvas.characterKey = p1;
        //mCanvas.character = this.character;

        /*
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onFragmentInteraction(view);
                    }
                });*/

        nodeEditCanvas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onFragmentInteraction(view);
                    }
                });

        if(characterKey == null)
        {
            Log.i("NodeEditFragment onCreateView LATER, characterKey is: ", "null!");

        }
        else
        {
            Log.i("NodeEditFragment onCreateView LATER, characterKey is: ", characterKey);
        }

        if(nodeEditCanvas.characterKey == null)
        {
            Log.i("NodeEditFragment onCreateView LATER, nodeEditCanvas.characterKey is: ", "null!");
        }
        else
        {
            Log.i("NodeEditFragment onCreateView LATER, nodeEditCanvas.characterKey is: ", nodeEditCanvas.characterKey);
        }

        if(mode[0] == -3)
        {
            Log.i("NodeEditFragment onCreateView LATER, characterKey is: ", "null!");

        }
        else
        {
            Log.i("NodeEditFragment onCreateView LATER, mode is: ", Integer.toString(mode[0]));
        }

        if(nodeEditCanvas.mode[0] == -3)
        {
            Log.i("NodeEditFragment onCreateView LATER, nodeEditCanvas.mode is: ", "null!");
        }
        else
        {
            Log.i("NodeEditFragment onCreateView LATER, nodeEditCanvas.mode is: ", Integer.toString(nodeEditCanvas.mode[0]));
        }


        atomArray = nodeEditCanvas.atomArray;
        bitmapCanvas = nodeEditCanvas.bitmapCanvas;

        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_canvas, container, false);
    }

    public void onClick(View v){
        //Nothing here yet
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(View view);
    }

}

