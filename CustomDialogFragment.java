package com.example.patrick.canvaswrite;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.os.Handler;
import android.content.Context;

/**
 * Created by Patrick on 4/22/2015.
 */
public class CustomDialogFragment extends DialogFragment {

    private final int VOLUME = 20;

    private Animation anim;
    private Animation transition;
    private Boolean loaded = false;
    private Context context;
    public Dialog dialog;
    private ImageView replayView;
    private ImageView redxView;
    private ImageView handView;
    private int counter;
    private int animDuration;
    private int soundDuration;
    private MediaPlayer mp;


    public onSubmitListener mListener;

    interface onSubmitListener {
        void setOnSubmitListener(String arg);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        configureControls();
        dialog.show();
        return dialog;
    }

    public void startAnimation(final Dialog dialog) {

        if(mp.isPlaying())
        {
            mp.stop();
            mp.release();
        }

        mp = MediaPlayer.create(context, R.raw.talking12seconds);

        handView = (ImageView)dialog.findViewById(R.id.handView);
        anim = AnimationUtils.loadAnimation(context, R.anim.test);

        configureDurationData();

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                if (counter == 1) {
                    hideView(handView);
                    startTransition(dialog);
                }
                if (counter > 1) {
                    counter--;
                    anim = AnimationUtils.loadAnimation(context, R.anim.test);
                    anim.setAnimationListener(this);
                    handView.startAnimation(anim);
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }

        });

        handView.startAnimation(anim);
        mp.start();
    }

    public void startTransition(final Dialog dialog){

        handView = (ImageView)dialog.findViewById(R.id.handView);
        transition = AnimationUtils.loadAnimation(context, R.anim.one_second_transition);
        transition.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                startSecondAnimation(dialog);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

        handView.startAnimation(transition);

    }



    public void startSecondAnimation(Dialog dialog) {

        handView = (ImageView)dialog.findViewById(R.id.handView);
        anim = AnimationUtils.loadAnimation(context, R.anim.test2);
        mp.release();
        mp = MediaPlayer.create(context, R.raw.london);

        configureDurationData();

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                if (counter == 1) {
                    hideView(handView);
                    exposeControls();

                }
                if (counter > 1) {
                    counter--;
                    anim = AnimationUtils.loadAnimation(context, R.anim.test2);
                    anim.setAnimationListener(this);
                    handView.startAnimation(anim);
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

        handView.startAnimation(anim);
        mp.start();
    }

    @Override
    public void onStart(){
        super.onStart();
        setMPVolume(VOLUME);
        mp = MediaPlayer.create(context, R.raw.dialog_start);
        mp.start();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startAnimation(dialog);
            }
        }, 2000 );
    }

    @Override
    public void onStop(){
        super.onStop();
        mp.release();
    }

    public void setMPVolume(int volume){
        AudioManager audioManager = (AudioManager)context.getSystemService(context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }

    public void exposeControls()
    {
        replayView.setAlpha(1.0f);
        redxView.setAlpha(1.0f);
        replayView.setClickable(true);
        redxView.setClickable(true);
    }

    public void logDurationInfo(){
        Log.i("animDuration is: ", Integer.toString(animDuration));
        Log.i("soundDuration is: ", Integer.toString(soundDuration));
        Log.i("counter is: ", Integer.toString(counter));
    }

    public void configureDurationData(){
        soundDuration = mp.getDuration();
        animDuration = (int) anim.computeDurationHint();
        counter = soundDuration / animDuration;
    }

    public void configureControls(){
        replayView = (ImageButton)dialog.findViewById(R.id.replayView);
        redxView = (ImageButton)dialog.findViewById(R.id.redxView);

        replayView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                startAnimation(dialog);
            }
        });

        redxView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        replayView.setClickable(false);
        redxView.setClickable(false);
    }

    public void hideView(View view){
        view.setVisibility(View.INVISIBLE);
    }
}