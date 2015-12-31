package com.example.patrick.canvaswrite;

import com.example.patrick.canvaswrite.CustomDialogFragment.onSubmitListener;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

/**
 * Created by Patrick on 4/22/2015.
 */
public class CustomDialogActivity extends Activity implements onSubmitListener {
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_activity);
        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                CustomDialogFragment fragment1 = new CustomDialogFragment();
                fragment1.mListener = CustomDialogActivity.this;
                fragment1.show(getFragmentManager(), "");
            }
        });

    }

    @Override
    public void setOnSubmitListener(String arg) {
    }

}