package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class TableLayoutActivity extends Activity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;

    Button button20;
    Button button21;
    Button button22;
    Button button23;
    Button button24;
    Button button25;
    Button button26;

    Button button30;
    Button button31;
    Button button32;
    Button button33;
    Button button34;
    Button button35;
    Button button36;

    Button button40;
    Button button41;
    Button button42;
    Button button43;
    Button button44;
    Button button45;
    Button button46;

    Button button50;
    Button button51;
    Button button52;
    Button button53;
    Button button54;
    Button button55;
    Button button56;

    Button button60;
    Button button61;
    Button button62;
    Button button63;
    Button button64;
    Button button65;
    Button button66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);

        button10 = (Button)findViewById(R.id.button10);
        button11 = (Button)findViewById(R.id.button11);
        button12 = (Button)findViewById(R.id.button12);
        button13 = (Button)findViewById(R.id.button13);
        button14 = (Button)findViewById(R.id.button14);
        button15 = (Button)findViewById(R.id.button15);
        button16 = (Button)findViewById(R.id.button16);

        button20 = (Button)findViewById(R.id.button20);
        button21 = (Button)findViewById(R.id.button21);
        button22 = (Button)findViewById(R.id.button22);
        button23 = (Button)findViewById(R.id.button23);
        button24 = (Button)findViewById(R.id.button24);
        button25 = (Button)findViewById(R.id.button25);
        button26 = (Button)findViewById(R.id.button26);

        button30 = (Button)findViewById(R.id.button30);
        button31 = (Button)findViewById(R.id.button31);
        button32 = (Button)findViewById(R.id.button32);
        button33 = (Button)findViewById(R.id.button33);
        button34 = (Button)findViewById(R.id.button34);
        button35 = (Button)findViewById(R.id.button35);
        button36 = (Button)findViewById(R.id.button36);

        button40 = (Button)findViewById(R.id.button40);
        button41 = (Button)findViewById(R.id.button41);
        button42 = (Button)findViewById(R.id.button42);
        button43 = (Button)findViewById(R.id.button43);
        button44 = (Button)findViewById(R.id.button44);
        button45 = (Button)findViewById(R.id.button45);
        button46 = (Button)findViewById(R.id.button46);

        button50 = (Button)findViewById(R.id.button50);
        button51 = (Button)findViewById(R.id.button51);
        button52 = (Button)findViewById(R.id.button52);
        button53 = (Button)findViewById(R.id.button53);
        button54 = (Button)findViewById(R.id.button54);
        button55 = (Button)findViewById(R.id.button55);
        button56 = (Button)findViewById(R.id.button56);

        button60 = (Button)findViewById(R.id.button60);
        button61 = (Button)findViewById(R.id.button61);
        button62 = (Button)findViewById(R.id.button62);
        button63 = (Button)findViewById(R.id.button63);
        button64 = (Button)findViewById(R.id.button64);
        button65 = (Button)findViewById(R.id.button65);
        button66 = (Button)findViewById(R.id.button66);

    }

    @Override
    public void onWindowFocusChanged(boolean b) {

        TableLayout tableLayout = (TableLayout)findViewById(R.id.TableContainer);
        int height = tableLayout.getHeight();
        int width = tableLayout.getWidth();

        TableRow tableRow0 = (TableRow)findViewById(R.id.RowContainer0);

        int rowHeight = tableRow0.getHeight();
        int rowWidth = tableRow0.getWidth();

        int cellHeight = 0;
        int cellWidth = 0;

        /*
        Log.i("tableLayout.getWidth is:", Integer.toString(width));
        Log.i("tableLayout.getHeight is:", Integer.toString(height));
        Log.i("tableRow0.getWidth is:", Integer.toString(rowWidth));
        Log.i("tableRow0.getHeight is:", Integer.toString(rowHeight));
        */


        if(width < height) {
            Log.i("WIDTH <    ", "    HEIGHT");


            //TableLayout.LayoutParams layout_description = new TableLayout.LayoutParams(width, width);
            LinearLayout.LayoutParams layout_description = new LinearLayout.LayoutParams(width, width);
            tableLayout.setGravity(Gravity.CENTER);
            tableLayout.setLayoutParams(layout_description);


            TableRow.LayoutParams row_layout_description = new TableRow.LayoutParams(width, width);
            tableRow0.setLayoutParams(row_layout_description);

            cellWidth = tableRow0.getLayoutParams().width / 7;
            cellHeight = cellWidth;

            Log.i("Width measurement is:", Integer.toString(cellWidth));


        }
        else
        {
            Log.i("HEIGHT <    ", "    WIDTH");

            //TableLayout.LayoutParams layout_description = new TableLayout.LayoutParams(height, height);
            LinearLayout.LayoutParams layout_description = new LinearLayout.LayoutParams(height, height);
            tableLayout.setGravity(Gravity.CENTER);
            tableLayout.setLayoutParams(layout_description);


            TableRow.LayoutParams row_layout_description = new TableRow.LayoutParams(height, height);
            tableRow0.setLayoutParams(row_layout_description);

            cellWidth = tableLayout.getLayoutParams().height / 7;
            cellHeight = cellWidth;

            Log.i("Cell height measurement is:", Integer.toString(cellHeight));


        }

        height = tableLayout.getLayoutParams().height;
        width = tableRow0.getLayoutParams().width;

        Log.i("tableROW.getLayoutParams().width is:", Integer.toString(width));
        Log.i("tableLayout.getLayoutParams().height is:", Integer.toString(height));
        Log.i("Cell Width is:", Integer.toString(cellWidth));
        Log.i("Cell Height is:", Integer.toString(cellHeight));

        button0.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button1.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button2.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button3.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button4.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button5.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button6.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));

        button10.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button11.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button12.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button13.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button14.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button15.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button16.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));

        button20.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button21.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button22.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button23.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button24.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button25.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button26.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));

        button30.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button31.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button32.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button33.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button34.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button35.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button36.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));

        button40.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button41.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button42.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button43.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button44.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button45.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button46.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));

        button50.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button51.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button52.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button53.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button54.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button55.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button56.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));

        button60.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button61.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button62.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button63.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button64.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button65.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));
        button66.setLayoutParams(new TableRow.LayoutParams(cellWidth, cellHeight));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table_layout, menu);
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
