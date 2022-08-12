package com.pesonal.adsdk;

import static com.pesonal.adsdk.AppManage.app_adShowStatus;
import static com.pesonal.adsdk.AppManage.impressionCountCheck;
import static com.pesonal.adsdk.AppManage.mysharedpreferences;
import static com.pesonal.adsdk.AppManage.showRedirectDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.pesonal.adsdk.myActivities.AdModel;
import com.pesonal.adsdk.myActivities.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class ADS_SplashActivity extends AppCompatActivity {

    public static ArrayList<AdModel> adModelArrayList = new ArrayList<AdModel>();
    public static int qureka_count = 0;
    public static boolean qureka_flag = true;

    public static boolean need_internet = false;
    String bytemode = "";
    boolean is_retry;
    private Runnable runnable;
    private Handler refreshHandler;

    private AppOpenManager appOpenManagerNew;
    private String app_open_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_splash);

        checkNetConfig();
        AppManage.Strcheckad = "StrOpen";
    }

    private boolean checkVPNBelow() {
        List<String> networkList = new ArrayList<>();
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp()) {
                    networkList.add(networkInterface.getName());
                }
            }
        } catch (Exception ex) {
        }
        return networkList.contains("tun0");
    }

    private boolean checkVPN() {

        ConnectivityManager cm = null;
        try {
            cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm != null && cm.getActiveNetworkInfo() != null && cm.getNetworkInfo(ConnectivityManager.TYPE_VPN).isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private void checkNetConfig() {

        Const.initpref(ADS_SplashActivity.this);
        if (Build.VERSION.SDK_INT >= 21) {
            if (checkVPN()) {
                Const.setprefvalue(ADS_SplashActivity.this, "true");

            } else {
                Const.setprefvalue(ADS_SplashActivity.this, "false");
            }
        } else {
            if (checkVPNBelow()) {
                Const.setprefvalue(ADS_SplashActivity.this, "true");

            } else {
                Const.setprefvalue(ADS_SplashActivity.this, "false");
            }
        }


    }

    public void ADSinit(final Activity activity, boolean IS_PURCHASED, final int cversion, final getDataListner myCallback1) {
        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(R.layout.retry_layout, null);
        dialog.setContentView(view);
        final TextView retry_buttton = view.findViewById(R.id.retry_buttton);

        final SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        final SharedPreferences.Editor editor_AD_PREF = preferences.edit();

        need_internet = preferences.getBoolean("need_internet", need_internet);

        if (!isNetworkAvailable() && need_internet) {
            is_retry = false;
            dialog.show();
        }

        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        mysharedpreferences.edit().putBoolean("InAppPurchased", IS_PURCHASED).apply();


//        app_open_id = mysharedpreferences.getString("AppOpenID", "");
//        if (mysharedpreferences.getString("Splash_Priority_Inter", "").equalsIgnoreCase("Beta") && !app_open_id.isEmpty() && isNetworkAvailable()) {
//            loadSplashAppOpen(activity, myCallback1);
//        } else {
//            loadAppOpen(activity, myCallback1);
//        }
//        else if (mysharedpreferences.getString("ApplicationLevel_Beta", "").equalsIgnoreCase("on") && !app_open_id.isEmpty() && isNetworkAvailable()) {
//            if (appOpenManagerNew == null)
//                appOpenManagerNew = new AppOpenManager_New(activity.getApplication(), app_open_id);
//        }


        dialog.dismiss();
        refreshHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable()) {
                    is_retry = true;
                    retry_buttton.setText(activity.getString(R.string.retry));
                } else if (need_internet) {
                    dialog.show();
                    is_retry = false;
                    retry_buttton.setText(activity.getString(R.string.connect_internet));
                }
                refreshHandler.postDelayed(this, 1000);
            }
        };

        refreshHandler.postDelayed(runnable, 1000);

        retry_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_retry) {
                    if (need_internet) {
                        myCallback1.onReload();
                    } else {
                        myCallback1.onSuccess();
                    }
                } else {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });

        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String currentDate = df.format(calender.getTime());


        final int addfdsf123;
        String status = mysharedpreferences.getString("firsttime", "true");
        final SharedPreferences.Editor editor = mysharedpreferences.edit();
        if (status.equals("true")) {
            editor.putString("date", currentDate).apply();
            editor.putString("firsttime", "false").apply();
            addfdsf123 = 13421;
        } else {
            String date = mysharedpreferences.getString("date", "");
            if (!currentDate.equals(date)) {
                editor.putString("date", currentDate).apply();
                addfdsf123 = 26894;
            } else {
                addfdsf123 = 87332;
            }
        }


//        String akbsvl679056 = "7C4882638462AA4C587E2C6391E36274632E526C861BAAF1F18C8F917BB48BA17CFAD9D37789829267DFB9B88FEF0DED\n";


        try {

//            bytemode = AESSUtils.decryptA(activity, akbsvl679056);
//            bytemode = bytemode + "v1/get_app.php";

            bytemode = "https://suratoffers.in/AppsManager/api/" + "v1/get_app.php";

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String sdfsdf;
        if (BuildConfig.DEBUG) {
            sdfsdf = "TRSOFTAG12789I";
        } else {
            sdfsdf = "TRSOFTAG82382I";
        }

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, bytemode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
                        try {
                            JSONObject response = new JSONObject(response1);
                            try {
                                boolean status = response.getBoolean("STATUS");
                                if (status) {
                                    String need_in = response.getJSONObject("APP_SETTINGS").getString("app_needInternet");
                                    need_internet = need_in.endsWith("1");
                                    editor_AD_PREF.putBoolean("need_internet", need_internet).apply();
                                    editor_AD_PREF.putString("Advertise_List", response.getJSONArray("Advertise_List").toString()).apply();
                                    editor_AD_PREF.putString("MORE_APP_SPLASH", response.getJSONArray("MORE_APP_SPLASH").toString()).apply();
                                    editor_AD_PREF.putString("MORE_APP_EXIT", response.getJSONArray("MORE_APP_EXIT").toString()).apply();

                                    SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                                    editor1.putString("response", response.toString());
                                    editor1.apply();
                                }
                            } catch (Exception e) {
                                if (need_internet) {
                                    dialog.dismiss();
                                    refreshHandler = new Handler();
                                    runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (isNetworkAvailable()) {
                                                is_retry = true;
                                                retry_buttton.setText(activity.getString(R.string.retry));
                                            } else {
                                                dialog.show();
                                                is_retry = false;
                                                retry_buttton.setText(activity.getString(R.string.connect_internet));
                                            }
                                            refreshHandler.postDelayed(this, 1000);
                                        }
                                    };
                                } else {
                                    loadAppOpen(activity, myCallback1);
                                }
                            }


                            loadOtherData(myCallback1, cversion);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (need_internet) {
                            dialog.dismiss();
                            refreshHandler = new Handler();
                            runnable = new Runnable() {
                                @Override
                                public void run() {
                                    if (isNetworkAvailable()) {
                                        is_retry = true;
                                        retry_buttton.setText(activity.getString(R.string.retry));
                                    } else {
                                        dialog.show();
                                        is_retry = false;
                                        retry_buttton.setText(activity.getString(R.string.connect_internet));
                                    }
                                    refreshHandler.postDelayed(this, 1000);
                                }
                            };
                        } else {
                            loadAppOpen(activity, myCallback1);
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Log.e("aaaaa", activity.getPackageName() + "  " + getKeyHash(activity) + "  " + addfdsf123 + "  " + sdfsdf);
                Map<String, String> params = new HashMap<String, String>();
                params.put("PHSUGSG6783019KG", activity.getPackageName());
                params.put("AFHJNTGDGD563200K", getKeyHash(activity));
                params.put("DTNHGNH7843DFGHBSA", String.valueOf(addfdsf123));
                params.put("DBMNBXRY4500991G", sdfsdf);
                return params;
            }
        };
        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);
    }

    private void loadSplashAppOpen(Activity ac, getDataListner myCallback) {
        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        Log.e("LLL_Splash_AppOpen : ", "onAdFailedToShowFullScreenContent");
                        loadAppOpen(ac, myCallback);
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.e("LLL_Splash_AppOpen : ", "onAdDismissedFullScreenContent");
                        loadAppOpen(ac, myCallback);
                    }
                });
                ad.show(ac);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Log.e("LLL_Splash_AppOpen : ", "onAdFailedToLoad");
                loadAppOpen(ac, myCallback);
            }
        };

        Log.e("LLL_Splash_AppOpen : ", "Send Request");

        AppOpenAd.load(
                ac,
                app_open_id,
                new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    private void loadAppOpen(Activity ac, getDataListner myCallback) {
        if (mysharedpreferences.getString("ApplicationLevel_Beta", "").equalsIgnoreCase("on")
                && !mysharedpreferences.getBoolean("InAppPurchased", false)
                && !app_open_id.isEmpty()
                && isNetworkAvailable()) {
            if (appOpenManagerNew == null)
                appOpenManagerNew = new AppOpenManager(ac.getApplication(), app_open_id);
        }
        myCallback.onSuccess();
    }

    public void loadOtherData(getDataListner myCallback1, int cversion) {
        AppManage.getInstance(ADS_SplashActivity.this).getResponseFromPref(new getDataListner() {
            @Override
            public void onSuccess() {
                if (!mysharedpreferences.getBoolean("InAppPurchased", false)) {
                    qureka_flag = true;
                    AppManage.getInstance(ADS_SplashActivity.this).loadInterstitialAd(ADS_SplashActivity.this);

                    String app_open_id2 = mysharedpreferences.getString("AppOpenID", "");
                    if (app_adShowStatus != 0) {
                        qureka_flag = true;

                        //TODO impressionCount check
                        if (impressionCountCheck()) {
                            String redirectNewPackage = mysharedpreferences.getString("app_newPackageName", "");
                            showRedirectDialog(redirectNewPackage);
                            return;
                        }

                        if (mysharedpreferences.getString("Splash_Priority_Inter", "").equalsIgnoreCase("AMInter") && isNetworkAvailable()) {
                            AppManage.getInstance(ADS_SplashActivity.this).loadSplashInterstitialAd(ADS_SplashActivity.this, myCallback1);
                        } else if (mysharedpreferences.getString("Splash_Priority_Inter", "").equalsIgnoreCase("Facebook") && isNetworkAvailable()) {
                            AppManage.getInstance(ADS_SplashActivity.this).loadFacebooknSplashInterstitialAd(ADS_SplashActivity.this, myCallback1);
                        } else if (mysharedpreferences.getString("Splash_Priority_Inter", "").equalsIgnoreCase("TopOn") && isNetworkAvailable()) {
                            AppManage.getInstance(ADS_SplashActivity.this).loadTopOnSplashInterstitialAd(ADS_SplashActivity.this, myCallback1);
                        } else {
                            app_open_id = mysharedpreferences.getString("AppOpenID", "");

                            if (mysharedpreferences.getString("Splash_Priority_Inter", "").equalsIgnoreCase("Beta") && !app_open_id.isEmpty() && isNetworkAvailable()) {
                                loadSplashAppOpen(ADS_SplashActivity.this, myCallback1);
                            } else {
                                loadAppOpen(ADS_SplashActivity.this, myCallback1);
                            }
                        }
//                        else {
//                            loadAppOpen(ADS_SplashActivity.this, myCallback1);
//                            myCallback1.onSuccess();
//                        }
                    } else {
                        qureka_flag = false;
                        myCallback1.onSuccess();
                        //  loadAppOpen(ADS_SplashActivity.this, myCallback1);
                    }
                } else {
                    qureka_flag = false;
                    myCallback1.onSuccess();
                    //  loadAppOpen(ADS_SplashActivity.this, myCallback1);
                }
            }

            @Override
            public void onUpdate(String url) {
                myCallback1.onUpdate(url);
            }

            @Override
            public void onRedirect(String url) {
                myCallback1.onRedirect(url);
            }

            @Override
            public void onReload() {
            }

            @Override
            public void onGetExtradata(JSONObject extraData) {
                myCallback1.onGetExtradata(extraData);

            }
        }, cversion);
    }

    //TODO HIral OneSignal
    public void loadOtherData_OI(getDataListner myCallback1, int cversion) {
        AppManage.getInstance(ADS_SplashActivity.this).getResponseFromPref(new getDataListner() {
            @Override
            public void onSuccess() {
                myCallback1.onSuccess();
            }

            @Override
            public void onUpdate(String url) {
                myCallback1.onUpdate(url);
            }

            @Override
            public void onRedirect(String url) {
                myCallback1.onRedirect(url);
            }

            @Override
            public void onReload() {
            }

            @Override
            public void onGetExtradata(JSONObject extraData) {
                myCallback1.onGetExtradata(extraData);

            }
        }, cversion);
    }

    public void ADSinit_OI(final Activity activity, final int cversion, final getDataListner myCallback1) {
        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);

        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String currentDate = df.format(calender.getTime());

        final int addfdsf123;
        String status = mysharedpreferences.getString("firsttime", "true");
        final SharedPreferences.Editor editor = mysharedpreferences.edit();
        if (status.equals("true")) {
            editor.putString("date", currentDate).apply();
            editor.putString("firsttime", "false").apply();
            addfdsf123 = 13421;
        } else {
            String date = mysharedpreferences.getString("date", "");
            if (!currentDate.equals(date)) {
                editor.putString("date", currentDate).apply();
                addfdsf123 = 26894;
            } else {
                addfdsf123 = 87332;
            }
        }
//        String akbsvl679056 = "7C4882638462AA4C587E2C6391E36274632E526C861BAAF1F18C8F917BB48BA17CFAD9D37789829267DFB9B88FEF0DED\n";


        try {

//            bytemode = AESSUtils.decryptA(activity, akbsvl679056);
//            bytemode = bytemode + "v1/get_app.php";

            bytemode = "https://suratoffers.in/AppsManager/api/" + "v1/get_app.php";

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String sdfsdf;
        if (BuildConfig.DEBUG) {
            sdfsdf = "TRSOFTAG12789I";
        } else {
            sdfsdf = "TRSOFTAG82382I";
        }

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, bytemode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
                        loadOtherData_OI(myCallback1, cversion);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("PHSUGSG6783019KG", activity.getPackageName());
                params.put("AFHJNTGDGD563200K", getKeyHash(activity));
                params.put("DTNHGNH7843DFGHBSA", String.valueOf(addfdsf123));
                params.put("DBMNBXRY4500991G", sdfsdf);
                return params;
            }
        };
        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);
    }

    private String getKeyHash(Activity activity) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = (Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                return something.replace("+", "*");
            }
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
        return null;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = networkInfo != null && networkInfo.isConnected();
        // Network is present and connected
        return isAvailable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (refreshHandler != null)
            refreshHandler.removeCallbacks(runnable);
    }

}