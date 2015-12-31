package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    String[] record = new String[4];
    String[][] recordArray = new String[26][4];
    String[] alphabet = new String[]{"A","B","C","D","E","F","G","H","I","J",
                                 "K","L","M","N","O","P","Q","R","S","T",
                                 "U","V","W","X","Y","Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager db = new DatabaseManager(this);
        for(int i = 0; i < 26; i++)
        {
            record = db.getRowAsObjectAlternative(alphabet[i]);
            recordArray[i] = record;

            Log.w("MAIN ACTIVITY","recordArray[" + i + "] is " + recordArray[i][0]
                    + "\n" + recordArray[i][1]
                    + "\n" + recordArray[i][2]
                    + "\n" + recordArray[i][3]);

        }
        //record = db.getRowAsObjectAlternative(alphabet[0]);
        //Log.e("RECORD_ID", record[0]);
        //Log.e("RECORD_UPPER", record[1]);
        //Log.e("RECORD_RIGHT", record[2]);
        //Log.e("RECORD_WRONG", record[3]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void startLettersActivity(View view) {
        Intent intent = new Intent(this, Letters.class);
        startActivity(intent);
    }

    public void startNodeEditLetterSelectActivity(View view) {
        Intent intent = new Intent(this, NodeEditLetterSelect.class);
        startActivity(intent);
    }

    public void startStatsActivity(View view) {
        Intent intent = new Intent(this, Stats.class);
        Bundle statsBundle = new Bundle();

        statsBundle.putSerializable("Array", recordArray);

        intent.putExtras(statsBundle);

        startActivity(intent);
    }

    public void startDialogActivity(View view) {
        Intent intent = new Intent(this, CustomDialogActivity.class);
        startActivity(intent);
    }

    public void startScrollViewActivity(View view) {
        Intent intent = new Intent(this, ScrollViewActivity.class);
        startActivity(intent);
    }

    public void startRelativeLayoutActivity(View view) {
        Intent intent = new Intent(this, RelativeLayoutActivity.class);
        startActivity(intent);
    }

    public void startGridLayoutActivity(View view) {
        Intent intent = new Intent(this, GridLayoutActivity.class);
        startActivity(intent);
    }

    public void startTableLayoutActivity(View view) {
        Intent intent = new Intent(this, TableLayoutActivity.class);
        startActivity(intent);
    }

}
