package com.example.patrick.canvaswrite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Patrick on 4/21/2015.
 */
public class FoundationalDialogFragment extends DialogFragment {

    public static FoundationalDialogFragment newInstance(String fragmentNumber){
        FoundationalDialogFragment newInstance = new FoundationalDialogFragment();
        Bundle args = new Bundle();
        args.putString("fragnum", fragmentNumber);
        newInstance.setArguments(args);
        return newInstance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final String fragnum = getArguments().getString("fragnum");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("This alert brought to you by " + fragnum);
        alertDialog.setIcon(android.R.drawable.btn_star);
        alertDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Learn) getActivity()).doPositiveClick(fragnum);
                        return;
                    }
                });
        return alertDialog.create();
    }

}
