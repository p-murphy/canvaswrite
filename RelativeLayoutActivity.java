package com.example.patrick.canvaswrite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



public class RelativeLayoutActivity extends Activity {

    RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
    }

    @Override
    public void onWindowFocusChanged(boolean b) {

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.RelativeContainer);

        int height = relativeLayout.getHeight();
        int width = relativeLayout.getWidth();

        Log.i("Width is:", Integer.toString(width));
        Log.i("Height is:", Integer.toString(height));


        if(width < height) {
            Log.i("WIDTH <    ", "    HEIGHT");


            LinearLayout.LayoutParams layout_description = new LinearLayout.LayoutParams(width, width);
            relativeLayout.setLayoutParams(layout_description);
        }
        else
        {
            Log.i("HEIGHT <    ", "    WIDTH");

            LinearLayout.LayoutParams layout_description = new LinearLayout.LayoutParams(height, height);
            relativeLayout.setGravity(Gravity.CENTER);
            relativeLayout.setLayoutParams(layout_description);

        }

        height = relativeLayout.getLayoutParams().height;
        width = relativeLayout.getLayoutParams().width;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_relative_layout, menu);
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
