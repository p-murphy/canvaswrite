package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;


public class GridLayoutActivity extends Activity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);

    }

    @Override
    public void onWindowFocusChanged(boolean b) {

        GridLayout gridLayout = (GridLayout)findViewById(R.id.GridContainer);

        int height = gridLayout.getHeight();
        int width = gridLayout.getWidth();

        Log.i("gridLayout.getWidth is:", Integer.toString(width));
        Log.i("gridLayout.getHeight is:", Integer.toString(height));

        int cellMeasurement = 0;

        if(width < height) {
            Log.i("WIDTH <    ", "    HEIGHT");


            LinearLayout.LayoutParams layout_description = new LinearLayout.LayoutParams(width, width);
            gridLayout.setLayoutParams(layout_description);

            cellMeasurement = gridLayout.getLayoutParams().width/ 7;
            Log.i("Width measurement is:", Integer.toString(cellMeasurement));

        }
        else
        {
            Log.i("HEIGHT <    ", "    WIDTH");

            LinearLayout.LayoutParams layout_description = new LinearLayout.LayoutParams(height, height);

            //gridLayout.setGravity(Gravity.CENTER);
            gridLayout.setLayoutParams(layout_description);

            cellMeasurement = gridLayout.getLayoutParams().height / 7;
            Log.i("Height measurement is:", Integer.toString(cellMeasurement));

        }

        height = gridLayout.getLayoutParams().height;
        width = gridLayout.getLayoutParams().width;

        Log.i("gridLayout.getLayoutParams().width is:", Integer.toString(width));
        Log.i("gridLayout.getLayoutParams().height is:", Integer.toString(height));

        Log.i("Width is:", Integer.toString(width));
        Log.i("Height is:", Integer.toString(height));
        Log.i("Cell measurement is:", Integer.toString(cellMeasurement));


        //button0.setLayoutParams(new View.LayoutParams(cellMeasurement, cellMeasurement));

        button0.setWidth(cellMeasurement);
        button0.setHeight(cellMeasurement);
        button1.setWidth(cellMeasurement);
        button1.setHeight(cellMeasurement);
        button2.setWidth(cellMeasurement);
        button2.setHeight(cellMeasurement);
        button3.setWidth(cellMeasurement);
        button3.setHeight(cellMeasurement);
        button4.setWidth(cellMeasurement);
        button4.setHeight(cellMeasurement);
        button5.setWidth(cellMeasurement);
        button5.setHeight(cellMeasurement);
        button6.setWidth(cellMeasurement);
        button6.setHeight(cellMeasurement);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid_layout, menu);
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
}
