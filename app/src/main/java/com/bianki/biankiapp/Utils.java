package com.bianki.biankiapp;

import android.app.AlertDialog;
import android.content.Context;

import com.bianki.biankiapp.Api.biankiApi;
import com.bianki.biankiapp.Api.biankiClient;

public class Utils {

    public static biankiApi getApi() {
        return biankiClient.getTravelClient().create(biankiApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }


}





