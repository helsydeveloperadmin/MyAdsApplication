package com.pesonal.adsdk.myActivities;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

public class Const {


    public static void initpref(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("netstatusPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("netstatus", "false");
        editor.putInt("netImp", 0);

        editor.commit();
    }

    public static void setprefvalue(Activity activity, String value) {
        SharedPreferences preferences = activity.getSharedPreferences("netstatusPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("netstatus", value);
        editor.commit();
    }


    public static String getprefvalue(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("netstatusPref", MODE_PRIVATE);
        String netStatus = preferences.getString("netstatus", "false");
        return netStatus;

    }


}
