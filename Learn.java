package com.example.patrick.canvaswrite;

import android.app.DialogFragment;
import android.media.SoundPool;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.media.AudioManager;

public class Learn extends FragmentActivity implements CanvasFragment.OnFragmentInteractionListener {

    private DatabaseManager db;
    private String letter;
    private int win, lose;
    private SoundPool soundPool;
    public CanvasFragment canvasFragment;

    private int flag = 0;
    private final int OUT_OF_BOUNDS = -100;
    private final int EARLY_RELEASE = -101;

    private String strokeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        letter = getIntent().getExtras().getString("Letter");
        Bundle letterBundle = new Bundle();
        letterBundle.putSerializable("Letter", letter);
        canvasFragment = new CanvasFragment();
        canvasFragment.setArguments(letterBundle);

        setContentView(R.layout.activity_learn);

        fragmentTransaction.add(R.id.canvasFrame, canvasFragment).commit();

        TextView canvas = (TextView)findViewById(R.id.LetterCanvas);
        canvas.setText(letter);

        db = new DatabaseManager(this);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        win = soundPool.load(this, R.raw.win, 1);
        lose = soundPool.load(this, R.raw.lose, 1);


        /*Log.i("In LEARN", " before first canvasFragment call");
        CanvasFragment canvasFragment = CanvasFragment.newInstance("A");
        Log.i("In LEARN", " after first canvasFragment call");
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn, menu);
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

    public void correct(View view) {

        showDialogFragment("Fragment Instance One");

        db.updateRowRight(letter);
        soundPool.play(win, 1, 1, 1, 0, 1f);
        finish();
    }

    public void incorrect(View view) {

        db.updateRowWrong(letter);
        soundPool.play(lose, 1, 1, 1, 0, 1f);
        finish();
    }

    @Override
    public void onFragmentInteraction(View view, int i, int f){

        strokeScore = Integer.toString(i);
        Log.i("OnFragmentInteraction: ", "called + + + + + + + + + + + + + + + + + + + ");
        Log.i("OnFragmentInteraction: Stroke Score is: ", strokeScore);

        if(f == OUT_OF_BOUNDS)
        {
            showDialogFragment("Out of bounds");
        }
    }

    public void showDialogFragment(String strFragmentNumber){
        DialogFragment newFragment = FoundationalDialogFragment.newInstance(strFragmentNumber);
        newFragment.show(getFragmentManager(), strFragmentNumber);
    }

    public void doPositiveClick(String strFragmentNumber){
        /*Toast.makeText(getApplicationContext(),
                "Clicked OK! (" + strFragmentNumber + ")",
                Toast.LENGTH_SHORT).show();*/
        finish();
    }
}
