package com.mymovies2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class AlertDialogHelper {
    private  DialogListener listener;
    private Context context;

    public AlertDialogHelper(Context context, DialogListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void showDialog(){
        new AlertDialog.Builder(context)
                .setTitle("warning")
                .setMessage("do you want to quit the app ?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       listener.onYesClicked();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("no", new DialogInterface.OnClickListener() {// לחצן היעד הישן
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onNoClicked();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public interface DialogListener{
        void onYesClicked();
        void onNoClicked();
    }

}
