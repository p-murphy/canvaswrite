package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class CanvasFragment extends Fragment implements Button.OnClickListener{


    private Button mButton; //Add at the top of the fragment

    private static final String ARG_PARAM1 = "param1";
    private String characterKey;
    private String mParam1;
    private CanvasView mCanvas;
    private OnFragmentInteractionListener mListener;

    public Character character;


    public static CanvasFragment newInstance(String param1) {
        //p1 = param1;

        CanvasFragment fragment = new CanvasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        Log.i("WE ARE IN ", "CONSTRUCTOR WITH ARGUMENTS");
        Log.i("param1 is", param1);
        return fragment;
    }

    public CanvasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

            characterKey = getArguments().getString("Letter");
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
            Log.i("CANVAS FRAGMENT OnCreateView ", "characterKey IS NULL!!!!!!!!");
        }
        else {
            Log.i("CANVAS FRAGMENT OnCreateView characterKey is ", characterKey);

        }

        View view = null;
        view = inflater.inflate(R.layout.fragment_canvas, container, false);

        mCanvas = new CanvasView(this.getActivity().getApplicationContext(), characterKey);

        final float scale = getActivity().getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (300 * scale + 0.5f);



        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(pixels, pixels);
        mCanvas.setLayoutParams(layoutParams);

        Log.i("FrameLayout width in CanvasFragment ", Integer.toString(pixels));
        Log.i("FrameLayout height in CanvasFragment ", Integer.toString(pixels));

        FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.ViewContainer);
        frameLayout.addView(mCanvas);

        Log.i("mCanvas width in CanvasFragment ", Integer.toString(mCanvas.getWidth()));
        Log.i("mCanvas height in CanvasFragment ", Integer.toString(mCanvas.getHeight()));

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

        mCanvas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onFragmentInteraction(view, 0, 0);
                    }
                });

        mCanvas.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View arg0, MotionEvent event) {
                        Log.i("ON TOUCH", "- - - - - - - - - - - - - - ");
                        mListener.onFragmentInteraction(arg0, mCanvas.returnScore(), mCanvas.returnFlag());
                        return false;
                    }
                });


            if(characterKey==null)

            {
                //Log.i("CanvasFragment onCreateView LATER, characterKey is: ", "null!");

            }

            else

            {
                //Log.i("CanvasFragment onCreateView LATER, characterKey is: ", characterKey);
            }

            if(mCanvas.characterKey==null)

            {
                //Log.i("CanvasFragment onCreateView LATER, mCanvas.characterKey is: ", "null!");
            }

            else

            {
                //Log.i("CanvasFragment onCreateView LATER, mCanvas.characterKey is: ", mCanvas.characterKey);
            }

            return view;

            // Inflate the layout for this fragment
            //return inflater.inflate(R.layout.fragment_canvas, container, false);
        }

    public void onClick(View v){
        //Nothing here yet
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onTouch(View v) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction();
        }*/
    }

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
        public void onFragmentInteraction(View view, int i, int f);

    }

}
