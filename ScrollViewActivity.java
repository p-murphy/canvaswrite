package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ScrollViewActivity extends Activity {

    ImageButton A, B, C, D, E, F, G;
    float AX;
    float AY;
    float BX;
    float BY;
    float CX;
    float CY;
    float DX;
    float DY;
    float EX;
    float EY;
    float FX;
    float FY;
    float GX;
    float GY;
    int counter = 0;
    Animation anim;
    Animation shine;
    Animation shine2;
    Animation waitAnim;

    Animation redShine;
    Animation redShine2;
    Animation redWaitAnim;


    ImageView hand;
    ImageView yellowTest;
    ImageView yellowTest2;

    ImageView redTest;
    ImageView redTest2;

    AnimationDrawable flyAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        A = (ImageButton)findViewById(R.id.buttonA);
        B = (ImageButton)findViewById(R.id.buttonB);
        C = (ImageButton)findViewById(R.id.buttonC);
        D = (ImageButton)findViewById(R.id.buttonD);
        E = (ImageButton)findViewById(R.id.buttonE);
        F = (ImageButton)findViewById(R.id.buttonF);
        G = (ImageButton)findViewById(R.id.buttonG);

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.test2);
        shine = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shine);
        shine2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shine2);
        waitAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.wait_anim);

        redShine = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shine);
        redShine2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shine2);
        redWaitAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.wait_anim);

        hand = (ImageView)findViewById(R.id.handView);

        yellowTest = (ImageView)findViewById(R.id.yellowTest);
        yellowTest2 = (ImageView)findViewById(R.id.yellowTest2);

        redTest = (ImageView)findViewById(R.id.redTest);
        redTest2 = (ImageView)findViewById(R.id.redTest2);


        yellowTest.setAlpha(0.0f);
        redTest.setAlpha(0.0f);
        yellowTest2.setAlpha(0.0f);
        redTest2.setAlpha(0.0f);

        setAnimationListeners(yellowTest, yellowTest2, shine, shine2, waitAnim);

        setAnimationListeners(redTest, redTest2, redShine, redShine2, redWaitAnim);


        hand.setBackgroundResource(R.drawable.fly);
        flyAnimationDrawable = (AnimationDrawable) hand.getBackground();


        yellowTest.startAnimation(waitAnim);
        yellowTest2.startAnimation(shine2);

        redTest.startAnimation(redWaitAnim);
        redTest2.startAnimation(redShine2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scroll_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startLearnActivity(View view) {
        Intent intent = new Intent(this, Learn.class);
        ImageButton b = (ImageButton) view;
        String letter = b.getContentDescription().toString();
        Bundle letterBundle = new Bundle();
        letterBundle.putSerializable("Letter", letter);
        intent.putExtras(letterBundle);

        startActivity(intent);
    }

    public void getCoordinates(View view){

        if(counter == 0) {

            AX = A.getX();
            AY = A.getY();
            BX = B.getX();
            BY = B.getY();
            CX = C.getX();
            CY = C.getY();
            DX = D.getX();
            DY = D.getY();
            EX = E.getX();
            EY = E.getY();
            FX = F.getX();
            FY = F.getY();
            GX = G.getX();
            GY = G.getY();
            hand.setX(AX);
            hand.setY(AY);
            Log.i("   A button location: ", Float.toString(AX) + "," + Float.toString(AY));
            Log.i("   B button location: ", Float.toString(BX) + "," + Float.toString(BY));
            Log.i("   C button location: ", Float.toString(CX) + "," + Float.toString(CY));
            Log.i("   D button location: ", Float.toString(DX) + "," + Float.toString(DY));
            Log.i("   E button location: ", Float.toString(EX) + "," + Float.toString(EY));
            Log.i("   F button location: ", Float.toString(FX) + "," + Float.toString(FY));
            Log.i("   G button location: ", Float.toString(GX) + "," + Float.toString(GY));

        }

        if(counter == 1) {

            TranslateAnimation translateAnimation =
                    new TranslateAnimation(AX, FX, AY, FY);
            translateAnimation.setDuration(2000);
            translateAnimation.setFillAfter(true);
            translateAnimation.setInterpolator(new LinearInterpolator());
            hand.startAnimation(translateAnimation);
        }
        counter++;
    }

    public void setAnimationListeners(final ImageView iv1, final ImageView iv2
            , final Animation anim1, final Animation anim2, final Animation animWait){


        anim1.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {

                iv1.setAlpha(1.0f);
                iv1.startAnimation(anim1);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }

        });

        anim2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {

                iv2.setAlpha(1.0f);
                iv2.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }

        });

        animWait.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                iv1.startAnimation(anim1);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }

        });
    }

}
