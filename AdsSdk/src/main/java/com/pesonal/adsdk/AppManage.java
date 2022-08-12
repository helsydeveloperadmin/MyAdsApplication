package com.pesonal.adsdk;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.anythink.banner.api.ATBannerExListener;
import com.anythink.banner.api.ATBannerView;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATNetworkConfirmInfo;
import com.anythink.core.api.ATSDK;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialListener;
import com.anythink.nativead.api.ATNative;
import com.anythink.nativead.api.ATNativeAdRenderer;
import com.anythink.nativead.api.ATNativeAdView;
import com.anythink.nativead.api.ATNativeImageView;
import com.anythink.nativead.api.ATNativeNetworkListener;
import com.anythink.nativead.unitgroup.api.CustomNativeAd;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.onesignal.OneSignal;
import com.pesonal.adsdk.myActivities.AllInterstitialAdsPriority1GroupTwo;
import com.pesonal.adsdk.myActivities.AllInterstitialAdsPriorityGroupTwo;
import com.pesonal.adsdk.myActivities.Common;
import com.pesonal.adsdk.myActivities.Const;
import com.pesonal.adsdk.myActivities.NativeTemplateStyle;
import com.pesonal.adsdk.myActivities.TemplateView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class AppManage {
    //TODO Change Hiral
   /* public static final String appid = "a5aa1f9deda26d";
    public static final String appKey = "4f7b9ac17decb9babec83aac078742c7";*/

    public static String ADMOB = "Admob";
    public static String FACEBOOK = "Facebookaudiencenetwork";
    public static String MyCustomAds = "MyCustomAds";
    public static String TopOn = "TopOn";

    public static int count_banner = -1;
    public static int count_native = -1;
    public static int count_click = -1;
    public static int count_click_for_alt = -1;

    public static String app_privacyPolicyLink = "";
    public static String app_accountLink = "";
    public static int app_updateAppDialogStatus = 0;
    public static int app_dialogBeforeAdShow = 0;
    public static int app_redirectOtherAppStatus = 0;
    public static int app_adShowStatus = 1;
    public static int app_mainClickCntSwAd = 0;
    public static int app_innerClickCntSwAd = 0;


    public static String ADMOB_APPID = "";
    public static String[] ADMOB_B = {"", "", "", "", ""};
    public static String[] ADMOB_I = {"", "", "", "", ""};
    public static String[] ADMOB_N = {"", "", "", "", ""};


    public static String[] FACEBOOK_I = {"", "", "", "", ""};
    public static String[] FACEBOOK_B = {"", "", "", "", ""};
    public static String[] FACEBOOK_N = {"", "", "", "", ""};

    public static String[] TopOn_I = {"", "", "", "", ""};
    public static String[] TopOn_B = {"", "", "", "", ""};
    public static String[] TopOn_N = {"", "", "", "", ""};

    public static String TopOn_appID = "";
    public static String TopOn_appKey = "";

    public static int admob_AdStatus = 0;
    public static int facebook_AdStatus = 0;
    public static int myCustom_AdStatus = 0;
    public static int topOn_AdStatus = 0;

    public static int admob_loadAdIdsType = 0;
    public static int facebook_loadAdIdsType = 0;
    public static int topOn_loadAdIdsType = 0;
    public static SharedPreferences mysharedpreferences;
    public static List<CustomAdModel> myAppMarketingList = new ArrayList<>();
    public static int count_custBannerAd = 0;
    public static int count_custNBAd = 0;
    public static int count_custNativeAd = 0;
    public static int count_custIntAd = 0;
    public static int count_custAppOpenAd = 0;
    //Todo Hiral Ad code Change
    public static List<String> TestDeviceID = Arrays.asList("4F61F4EE6963DED455BB87F1A458613A");
    public static String Strcheckad = "";
    public static int Backprssornot = 0;
    public static int FrontshowadsCounter = 0;
    public static int intertitialCounter;
    public static int alternetCounter;
    public static int betaCounter;
    public static int sdkCounter;
    public static String checkStartMode = "";
    public static int totalImpressionCount = 0;
    //  public static int ad_dialog_time_in_second = 2;
    static Context activity;
    static MyCallback myCallback;
    private static AppManage mInstance;
    String admob_b, facebook_nb, facebook_b, topOn_b;
    String admob_n, facebook_n, topOn_n;
    ArrayList<String> banner_sequence = new ArrayList<>();
    ArrayList<String> native_sequence = new ArrayList<>();
    ArrayList<String> interstitial_sequence = new ArrayList<>();
    ATNativeAdView atNativeAdView;
    private InterstitialAd mInterstitialAd;
    private String google_i_pre = "", facebook_i_pre = "", topOn_i_pre = "";
    private com.facebook.ads.InterstitialAd fbinterstitialAd1;
    private ATInterstitial topOnmInterstitialAd;
    private Dialog dialog_fbinter;
    private Dialog dialog;
    private long mShownAds = System.currentTimeMillis();
    private ATNative atNatives;

    public AppManage(Context activity) {
        AppManage.activity = activity;
        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        getResponseFromPref();

    }

    public static AppManage getInstance(Context activity) {
        AppManage.activity = activity;
        if (mInstance == null) {
            mInstance = new AppManage(activity);
        }
        return mInstance;
    }

    public static boolean hasActiveInternetConnection(Context context) {
        @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void getResponseFromPref() {
        String response1 = mysharedpreferences.getString("response", "");

        SharedPreferences.Editor editor_count = mysharedpreferences.edit();
        editor_count.putInt("count_admob_B", 0);
        editor_count.putInt("count_admob_I", 0);
        editor_count.putInt("count_admob_N", 0);
        editor_count.putInt("count_facebook_B", 0);
        editor_count.putInt("count_facebook_NB", 0);
        editor_count.putInt("count_facebook_I", 0);
        editor_count.putInt("count_facebook_N", 0);

        editor_count.putInt("count_topOn_B", 0);
        editor_count.putInt("count_topOn_I", 0);
        editor_count.putInt("count_topOn_N", 0);
        editor_count.commit();

        if (!response1.isEmpty()) {
            try {


                JSONObject response = new JSONObject(response1);
                JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");

                app_accountLink = settingsJsonObject.getString("app_accountLink");
                app_privacyPolicyLink = settingsJsonObject.getString("app_privacyPolicyLink");
                app_updateAppDialogStatus = settingsJsonObject.getInt("app_updateAppDialogStatus");
                app_redirectOtherAppStatus = settingsJsonObject.getInt("app_redirectOtherAppStatus");
                app_dialogBeforeAdShow = settingsJsonObject.getInt("app_dialogBeforeAdShow");
                app_adShowStatus = settingsJsonObject.getInt("app_adShowStatus");

                app_mainClickCntSwAd = settingsJsonObject.getInt("app_mainClickCntSwAd");
                app_innerClickCntSwAd = settingsJsonObject.getInt("app_innerClickCntSwAd");


                boolean app_AppOpenAdStatus;
                app_AppOpenAdStatus = settingsJsonObject.getInt("app_AppOpenAdStatus") == 1;


                SharedPreferences.Editor editor = mysharedpreferences.edit();
                editor.putString("app_name", settingsJsonObject.getString("app_name"));
                editor.putString("app_logo", settingsJsonObject.getString("app_logo"));

                //TODO Skip AD after Second
                editor.putString("SkipAfter", settingsJsonObject.getString("SkipAfter"));
                editor.putString("BackPress_Priority", settingsJsonObject.getString("BackPress_Priority"));
                editor.putString("Splash_Priority_Inter", settingsJsonObject.getString("Splash_Priority_Inter"));
                editor.putString("CTA_Buttton_Color", settingsJsonObject.getString("CTA_Buttton_Color"));
                editor.putString("CTA_Buttton_TextColor", settingsJsonObject.getString("CTA_Buttton_TextColor"));
                editor.putString("Dialog_Img_Link", settingsJsonObject.getString("Dialog_Img_Link"));
                editor.putString("Dialog_Redirect_URL", settingsJsonObject.getString("Dialog_Redirect_URL"));
                editor.putString("AdLoadingTitle", settingsJsonObject.getString("AdLoadingTitle"));
                editor.putString("AdDialogLoadingTime", settingsJsonObject.getString("AdDialogLoadingTime"));
                editor.putString("NativePlaceHolder", settingsJsonObject.getString("NativePlaceHolder"));
                editor.putString("NativePlaceHolderText", settingsJsonObject.getString("NativePlaceHolderText"));
                editor.putString("NativeAdMainType", settingsJsonObject.getString("NativeAdMainType"));
                editor.putString("NativeAdSubType", settingsJsonObject.getString("NativeAdSubType"));
                editor.putString("StartMode", settingsJsonObject.getString("StartMode"));
                editor.putString("NativeAdLoadPlace", settingsJsonObject.getString("NativeAdLoadPlace"));
                editor.putString("BannerAdLoadPlace", settingsJsonObject.getString("BannerAdLoadPlace"));
                editor.putString("SDKExpressMode", settingsJsonObject.getString("SDKExpressMode"));
                editor.putString("PlayStoreRedirectDialog", settingsJsonObject.getString("PlayStoreRedirectDialog"));
                editor.putString("ApplicationLevel_Beta", settingsJsonObject.getString("ApplicationLevel_Beta"));
                editor.putString("ShowInterAdLoadingDialog", settingsJsonObject.getString("ShowInterAdLoadingDialog"));
                editor.putString("DoubleBackToExit", settingsJsonObject.getString("DoubleBackToExit"));
                editor.putString("ExitType", settingsJsonObject.getString("ExitType"));
                editor.putString("ONESIGNAL_APP_ID", settingsJsonObject.getString("ONESIGNAL_APP_ID"));
                editor.putString("net_mainClickCntSwAd", settingsJsonObject.getString("net_mainClickCntSwAd"));
                editor.putString("net_innerClickCntSwAd", settingsJsonObject.getString("net_innerClickCntSwAd"));
                editor.putString("net_StartMode", settingsJsonObject.getString("net_StartMode"));
                editor.putString("AdDelaytimeInterval", settingsJsonObject.getString("AdDelaytimeInterval"));

                //TODO impressionCount
                editor.putString("impressionCount", settingsJsonObject.getString("impressionCount"));

                //TODO Qureka
                editor.putString("Qureka_count", settingsJsonObject.getString("Qureka_count"));
                editor.putString("Qureka_Urls", settingsJsonObject.getString("Qureka_Urls"));
                editor.putString("Qureka_Names", settingsJsonObject.getString("Qureka_Names"));
                editor.putString("Qureka_GIFs", settingsJsonObject.getString("Qureka_GIFs"));
                editor.putString("Qureka_btn_bg_color", settingsJsonObject.getString("Qureka_btn_bg_color"));
                editor.putString("Qureka_btn_txt_color", settingsJsonObject.getString("Qureka_btn_txt_color"));


                editor.putString("app_privacyPolicyLink", app_privacyPolicyLink);
                editor.putInt("app_updateAppDialogStatus", app_updateAppDialogStatus);
                editor.putString("app_versionCode", settingsJsonObject.getString("app_versionCode"));
                editor.putInt("app_redirectOtherAppStatus", app_redirectOtherAppStatus);
                editor.putString("app_newPackageName", settingsJsonObject.getString("app_newPackageName"));
                editor.putInt("app_adShowStatus", app_adShowStatus);

                editor.putInt("app_howShowAdInterstitial", settingsJsonObject.getInt("app_howShowAdInterstitial"));
                editor.putString("app_adPlatformSequenceInterstitial", settingsJsonObject.getString("app_adPlatformSequenceInterstitial"));
                editor.putString("app_alernateAdShowInterstitial", settingsJsonObject.getString("app_alernateAdShowInterstitial"));

                editor.putInt("app_howShowAdNative", settingsJsonObject.getInt("app_howShowAdNative"));
                editor.putString("app_adPlatformSequenceNative", settingsJsonObject.getString("app_adPlatformSequenceNative"));
                editor.putString("app_alernateAdShowNative", settingsJsonObject.getString("app_alernateAdShowNative"));

                editor.putInt("app_howShowAdBanner", settingsJsonObject.getInt("app_howShowAdBanner"));
                editor.putString("app_adPlatformSequenceBanner", settingsJsonObject.getString("app_adPlatformSequenceBanner"));
                editor.putString("app_alernateAdShowBanner", settingsJsonObject.getString("app_alernateAdShowBanner"));

                editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);
                editor.putBoolean("app_AppOpenAdStatus", app_AppOpenAdStatus);
                editor.commit();

                if (Const.getprefvalue((Activity) activity).equalsIgnoreCase("true")) {
                    app_mainClickCntSwAd = Integer.parseInt(mysharedpreferences.getString("net_mainClickCntSwAd", ""));
                    app_innerClickCntSwAd = Integer.parseInt(mysharedpreferences.getString("net_innerClickCntSwAd", ""));

                    editor.putInt("app_mainClickCntSwAd", Integer.parseInt(mysharedpreferences.getString("net_mainClickCntSwAd", "")));
                    editor.putInt("app_innerClickCntSwAd", Integer.parseInt(mysharedpreferences.getString("net_innerClickCntSwAd", "")));
                } else {
                    editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                    editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);
                }
                editor.commit();


                JSONObject AdmobJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Admob");
                admob_AdStatus = AdmobJsonObject.getInt("ad_showAdStatus");
                admob_loadAdIdsType = AdmobJsonObject.getInt("ad_loadAdIdsType");
                ADMOB_APPID = AdmobJsonObject.getString("AppID");
                try {
                    ADMOB_B[0] = AdmobJsonObject.getString("Banner1");
                    ADMOB_I[0] = AdmobJsonObject.getString("Interstitial1");
                    ADMOB_N[0] = AdmobJsonObject.getString("Native1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                editor1.putString("AppOpenID", AdmobJsonObject.getString("AppOpen1"));
                editor1.commit();

                JSONObject FBJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Facebookaudiencenetwork");
                facebook_AdStatus = FBJsonObject.getInt("ad_showAdStatus");
                facebook_loadAdIdsType = FBJsonObject.getInt("ad_loadAdIdsType");


                try {
                    FACEBOOK_B[0] = FBJsonObject.getString("Banner1");
                    FACEBOOK_I[0] = FBJsonObject.getString("Interstitial1");
                    FACEBOOK_I[1] = FBJsonObject.getString("Interstitial2");
                    FACEBOOK_N[0] = FBJsonObject.getString("Native1");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject TopOnJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("TopOn");
                topOn_AdStatus = TopOnJsonObject.getInt("ad_showAdStatus");
                topOn_loadAdIdsType = TopOnJsonObject.getInt("ad_loadAdIdsType");
                TopOn_appID = TopOnJsonObject.getString("AppID");
                TopOn_appKey = TopOnJsonObject.getString("AppOpen1");
                try {
                    TopOn_B[0] = TopOnJsonObject.getString("Banner1");
                    TopOn_I[0] = TopOnJsonObject.getString("Interstitial1");
                    TopOn_I[1] = TopOnJsonObject.getString("Interstitial2");
                    TopOn_N[0] = TopOnJsonObject.getString("Native1");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /*TopOn_appID = "a5aa1f9deda26d";
                TopOn_appKey = "4f7b9ac17decb9babec83aac078742c7";
                try {
                  //  TopOn_B[0] = TopOnJsonObject.getString("Banner1");
                    TopOn_B[0] = "b5baca41a2536f";
                    //TopOn_I[0] = TopOnJsonObject.getString("Interstitial1");
                    TopOn_I[0] = "b5baca54674522";
                    //TopOn_N[0] = TopOnJsonObject.getString("Native1");
                    TopOn_N[0] = "b5aa1fa501d9f6";
                } catch (Exception e) {
                    e.printStackTrace();
                }*/


                JSONObject MyAdJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("MyCustomAds");
                myCustom_AdStatus = MyAdJsonObject.getInt("ad_showAdStatus");


            } catch (Exception e) {
            }

        }

    }

    private static void initAd() {
        if (admob_AdStatus == 1) {
            MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
        }

        if (facebook_AdStatus == 1) {
            AudienceNetworkAds.initialize(activity);

            AdSettings.addTestDevice("HASHED ID");
        }
        if (topOn_AdStatus == 1) {
            ATSDK.init(activity, TopOn_appID, TopOn_appKey);

            //  ATSDK.integrationChecking(activity);
        }
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(activity);
        OneSignal.setAppId(mysharedpreferences.getString("ONESIGNAL_APP_ID", ""));

    }

    public static boolean checkUpdate(int cversion) {


        if (mysharedpreferences.getInt("app_updateAppDialogStatus", 0) == 1) {
            String versions = mysharedpreferences.getString("app_versionCode", "");
            String[] str = versions.split(",");

            try {


                if (Arrays.asList(str).contains(cversion + "")) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    //TODO Hiral Custome Ad Click
    public static void getApp(Context context, String str) {
        try {
            CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
            customIntent.setToolbarColor(ContextCompat.getColor(context, R.color.colorAccent));
            openCustomTab(context, customIntent.build(), Uri.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void openCustomTab(Context context, CustomTabsIntent customTabsIntent, Uri uri) {
        String packageName = "com.android.chrome";
        if (packageName != null) {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(context, uri);
        } else {
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

    //TODO Redirect Dialog
    public static void showRedirectDialog(final String url) {

        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        View view = ((Activity) activity).getLayoutInflater().inflate(R.layout.installnewappdialog, null);
        dialog.setContentView(view);
        TextView update = view.findViewById(R.id.update);
        TextView txt_title = view.findViewById(R.id.txt_title);
        TextView txt_decription = view.findViewById(R.id.txt_decription);

        update.setText("Install Now");
        txt_title.setText("Install our new app now and enjoy");
        txt_decription.setText("We have transferred our server, so install our new app by clicking the button below to enjoy the new features of app.");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri marketUri = Uri.parse(url);
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    activity.startActivity(marketIntent);
                } catch (ActivityNotFoundException ignored1) {
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    //TODO impressionCount check
    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static boolean impressionCountCheck() {
        if (Integer.parseInt(mysharedpreferences.getString("impressionCount", "0")) == 0) {
            return false;
        }
        SharedPreferences.Editor editor = mysharedpreferences.edit();

        long saveDate = mysharedpreferences.getLong("saveDate", 0L);

        if (saveDate == 0L)
            editor.putLong("saveDate", System.currentTimeMillis()).apply();


        Log.e("HH_saveDate", getDate(saveDate, "dd/MM/yyyy"));
        Log.e("HH_Date", getDate(System.currentTimeMillis(), "dd/MM/yyyy"));

        if (getDate(System.currentTimeMillis(), "dd/MM/yyyy").compareTo(getDate(saveDate, "dd/MM/yyyy")) > 0) {
//            if ("31/07/2022".compareTo(getDate(saveDate,"dd/MM/yyyy")) > 0) {
            totalImpressionCount = 0;
            editor.putLong("saveDate", System.currentTimeMillis()).apply();
//            try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                editor.putLong("saveDate", formatter.parse("31/07/2022").getTime()).commit();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        } else {
            totalImpressionCount = Integer.parseInt(mysharedpreferences.getString("totalImpressionCount", "0"));
        }

        if (totalImpressionCount >= Integer.parseInt(mysharedpreferences.getString("impressionCount", "0"))) {
            return true;
        } else {
            totalImpressionCount++;
            editor.putString("totalImpressionCount", String.valueOf(totalImpressionCount));
            editor.apply();
            return false;
        }

    }

    public List<CustomAdModel> get_CustomAdData() {
        List<CustomAdModel> data = new ArrayList<>();
        SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        try {

            JSONArray array = new JSONArray(preferences.getString("Advertise_List", ""));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                CustomAdModel customAdModel = new CustomAdModel();
                customAdModel.setAd_id(object.getInt("ad_id"));
                customAdModel.setApp_name(object.getString("app_name"));
                customAdModel.setApp_packageName(object.getString("app_packageName"));
                customAdModel.setApp_logo(object.getString("app_logo"));
                customAdModel.setApp_banner(object.getString("app_banner"));
                customAdModel.setApp_shortDecription(object.getString("app_shortDecription"));
                customAdModel.setApp_rating(object.getString("app_rating"));
                customAdModel.setApp_download(object.getString("app_download"));
                customAdModel.setApp_AdFormat(object.getString("app_AdFormat"));
                customAdModel.setApp_buttonName(object.getString("app_buttonName"));
                data.add(customAdModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public CustomAdModel getMyCustomAd(String adFormat) {

        if (myAppMarketingList.size() == 0) {
            myAppMarketingList = get_CustomAdData();

        }

        List<CustomAdModel> adFormatWiseAd = new ArrayList<>();
        if (myAppMarketingList.size() != 0) {
            for (int i = 0; i < myAppMarketingList.size(); i++) {
                if (!myAppMarketingList.get(i).getApp_AdFormat().isEmpty()) {
                    String[] adFormat_list = myAppMarketingList.get(i).getApp_AdFormat().split(",");
                    if (Arrays.asList(adFormat_list).contains(adFormat)) {
                        adFormatWiseAd.add(myAppMarketingList.get(i));
                    }
                }

            }
        }

        int count_myAd = 0;
        if (adFormat.equals("Banner")) {
            count_myAd = count_custBannerAd;
        } else if (adFormat.equals("NativeBanner")) {
            count_myAd = count_custNBAd;
        } else if (adFormat.equals("Native")) {
            count_myAd = count_custNativeAd;
        } else if (adFormat.equals("Interstitial")) {
            count_myAd = count_custIntAd;
        } else if (adFormat.equals("AppOpen")) {
            count_myAd = count_custAppOpenAd;
        }
        CustomAdModel customAdModel = null;
        if (adFormatWiseAd.size() != 0) {
            for (int j = 0; j <= adFormatWiseAd.size(); j++) {
                if (count_myAd % adFormatWiseAd.size() == j) {
                    customAdModel = adFormatWiseAd.get(j);
                }
            }
        }


        return customAdModel;

    }

    public List<MoreApp_Data> get_SPLASHMoreAppData() {
        List<MoreApp_Data> data = new ArrayList<>();
        SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        try {

            JSONArray array = new JSONArray(preferences.getString("MORE_APP_SPLASH", ""));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                data.add(new MoreApp_Data(object.getString("app_id"), object.getString("app_name"), object.getString("app_packageName"), object.getString("app_logo"), object.getString("app_status")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<MoreApp_Data> get_EXITMoreAppData() {
        List<MoreApp_Data> data = new ArrayList<>();
        SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        try {

            JSONArray array = new JSONArray(preferences.getString("MORE_APP_EXIT", ""));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                data.add(new MoreApp_Data(object.getString("app_id"), object.getString("app_name"), object.getString("app_packageName"), object.getString("app_logo"), object.getString("app_status")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void getResponseFromPref(getDataListner listner, int cversion) {
        String response1 = mysharedpreferences.getString("response", "");

        SharedPreferences.Editor editor_count = mysharedpreferences.edit();
        editor_count.putInt("count_admob_B", 0);
        editor_count.putInt("count_admob_I", 0);
        editor_count.putInt("count_admob_N", 0);
        editor_count.putInt("count_facebook_B", 0);
        editor_count.putInt("count_facebook_NB", 0);
        editor_count.putInt("count_facebook_I", 0);
        editor_count.putInt("count_facebook_N", 0);

        editor_count.putInt("count_topOn_B", 0);
        editor_count.putInt("count_topOn_I", 0);
        editor_count.putInt("count_topOn_N", 0);

        editor_count.commit();

        if (!response1.isEmpty()) {
            try {


                JSONObject response = new JSONObject(response1);
                JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");

                app_accountLink = settingsJsonObject.getString("app_accountLink");
                app_privacyPolicyLink = settingsJsonObject.getString("app_privacyPolicyLink");
                app_updateAppDialogStatus = settingsJsonObject.getInt("app_updateAppDialogStatus");
                app_redirectOtherAppStatus = settingsJsonObject.getInt("app_redirectOtherAppStatus");
                app_dialogBeforeAdShow = settingsJsonObject.getInt("app_dialogBeforeAdShow");
                app_adShowStatus = settingsJsonObject.getInt("app_adShowStatus");
                app_mainClickCntSwAd = settingsJsonObject.getInt("app_mainClickCntSwAd");
                app_innerClickCntSwAd = settingsJsonObject.getInt("app_innerClickCntSwAd");
                boolean app_AppOpenAdStatus;
                app_AppOpenAdStatus = settingsJsonObject.getInt("app_AppOpenAdStatus") == 1;

                SharedPreferences.Editor editor = mysharedpreferences.edit();
                editor.putString("app_name", settingsJsonObject.getString("app_name"));

                //TODO Hiral
                editor.putString("SkipAfter", settingsJsonObject.getString("SkipAfter"));
                editor.putString("BackPress_Priority", settingsJsonObject.getString("BackPress_Priority"));
                editor.putString("Splash_Priority_Inter", settingsJsonObject.getString("Splash_Priority_Inter"));
                editor.putString("CTA_Buttton_Color", settingsJsonObject.getString("CTA_Buttton_Color"));
                editor.putString("CTA_Buttton_TextColor", settingsJsonObject.getString("CTA_Buttton_TextColor"));
                editor.putString("Dialog_Img_Link", settingsJsonObject.getString("Dialog_Img_Link"));
                editor.putString("Dialog_Redirect_URL", settingsJsonObject.getString("Dialog_Redirect_URL"));
                editor.putString("AdLoadingTitle", settingsJsonObject.getString("AdLoadingTitle"));
                editor.putString("AdDialogLoadingTime", settingsJsonObject.getString("AdDialogLoadingTime"));
                editor.putString("NativePlaceHolder", settingsJsonObject.getString("NativePlaceHolder"));
                editor.putString("NativePlaceHolderText", settingsJsonObject.getString("NativePlaceHolderText"));
                editor.putString("NativeAdMainType", settingsJsonObject.getString("NativeAdMainType"));
                editor.putString("NativeAdSubType", settingsJsonObject.getString("NativeAdSubType"));
                editor.putString("StartMode", settingsJsonObject.getString("StartMode"));
                editor.putString("NativeAdLoadPlace", settingsJsonObject.getString("NativeAdLoadPlace"));
                editor.putString("BannerAdLoadPlace", settingsJsonObject.getString("BannerAdLoadPlace"));
                editor.putString("SDKExpressMode", settingsJsonObject.getString("SDKExpressMode"));
                editor.putString("PlayStoreRedirectDialog", settingsJsonObject.getString("PlayStoreRedirectDialog"));
                editor.putString("ApplicationLevel_Beta", settingsJsonObject.getString("ApplicationLevel_Beta"));
                editor.putString("ShowInterAdLoadingDialog", settingsJsonObject.getString("ShowInterAdLoadingDialog"));
                editor.putString("DoubleBackToExit", settingsJsonObject.getString("DoubleBackToExit"));
                editor.putString("ExitType", settingsJsonObject.getString("ExitType"));
                editor.putString("ONESIGNAL_APP_ID", settingsJsonObject.getString("ONESIGNAL_APP_ID"));
                editor.putString("net_mainClickCntSwAd", settingsJsonObject.getString("net_mainClickCntSwAd"));
                editor.putString("net_innerClickCntSwAd", settingsJsonObject.getString("net_innerClickCntSwAd"));
                editor.putString("net_StartMode", settingsJsonObject.getString("net_StartMode"));
                editor.putString("AdDelaytimeInterval", settingsJsonObject.getString("AdDelaytimeInterval"));

                //TODO impressionCount
                editor.putString("impressionCount", settingsJsonObject.getString("impressionCount"));

                //TODO Qureka
                editor.putString("Qureka_count", settingsJsonObject.getString("Qureka_count"));
                editor.putString("Qureka_Urls", settingsJsonObject.getString("Qureka_Urls"));
                editor.putString("Qureka_Names", settingsJsonObject.getString("Qureka_Names"));
                editor.putString("Qureka_GIFs", settingsJsonObject.getString("Qureka_GIFs"));
                editor.putString("Qureka_btn_bg_color", settingsJsonObject.getString("Qureka_btn_bg_color"));
                editor.putString("Qureka_btn_txt_color", settingsJsonObject.getString("Qureka_btn_txt_color"));


                editor.putString("app_logo", settingsJsonObject.getString("app_logo"));
                editor.putString("app_privacyPolicyLink", app_privacyPolicyLink);
                editor.putInt("app_updateAppDialogStatus", app_updateAppDialogStatus);
                editor.putString("app_versionCode", settingsJsonObject.getString("app_versionCode"));
                editor.putInt("app_redirectOtherAppStatus", app_redirectOtherAppStatus);
                editor.putString("app_newPackageName", settingsJsonObject.getString("app_newPackageName"));
                editor.putInt("app_adShowStatus", app_adShowStatus);

                editor.putInt("app_howShowAdInterstitial", settingsJsonObject.getInt("app_howShowAdInterstitial"));
                editor.putString("app_adPlatformSequenceInterstitial", settingsJsonObject.getString("app_adPlatformSequenceInterstitial"));
                editor.putString("app_alernateAdShowInterstitial", settingsJsonObject.getString("app_alernateAdShowInterstitial"));

                editor.putInt("app_howShowAdNative", settingsJsonObject.getInt("app_howShowAdNative"));
                editor.putString("app_adPlatformSequenceNative", settingsJsonObject.getString("app_adPlatformSequenceNative"));
                editor.putString("app_alernateAdShowNative", settingsJsonObject.getString("app_alernateAdShowNative"));

                editor.putInt("app_howShowAdBanner", settingsJsonObject.getInt("app_howShowAdBanner"));
                editor.putString("app_adPlatformSequenceBanner", settingsJsonObject.getString("app_adPlatformSequenceBanner"));
                editor.putString("app_alernateAdShowBanner", settingsJsonObject.getString("app_alernateAdShowBanner"));
                editor.commit();
                if (Const.getprefvalue((Activity) activity).equalsIgnoreCase("true")) {
                    app_mainClickCntSwAd = Integer.parseInt(mysharedpreferences.getString("net_mainClickCntSwAd", ""));
                    app_innerClickCntSwAd = Integer.parseInt(mysharedpreferences.getString("net_innerClickCntSwAd", ""));

                    editor.putInt("app_mainClickCntSwAd", Integer.parseInt(mysharedpreferences.getString("net_mainClickCntSwAd", "")));
                    editor.putInt("app_innerClickCntSwAd", Integer.parseInt(mysharedpreferences.getString("net_innerClickCntSwAd", "")));

                } else {
                    editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                    editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);
                }

                editor.putBoolean("app_AppOpenAdStatus", app_AppOpenAdStatus);
                editor.commit();

                JSONObject AdmobJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Admob");
                admob_AdStatus = AdmobJsonObject.getInt("ad_showAdStatus");
                admob_loadAdIdsType = AdmobJsonObject.getInt("ad_loadAdIdsType");
                ADMOB_APPID = AdmobJsonObject.getString("AppID");
                try {
                    ADMOB_B[0] = AdmobJsonObject.getString("Banner1");
                    ADMOB_I[0] = AdmobJsonObject.getString("Interstitial1");
                    ADMOB_N[0] = AdmobJsonObject.getString("Native1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                editor1.putString("AppOpenID", AdmobJsonObject.getString("AppOpen1"));
                editor1.commit();

                JSONObject FBJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Facebookaudiencenetwork");
                facebook_AdStatus = FBJsonObject.getInt("ad_showAdStatus");
                facebook_loadAdIdsType = FBJsonObject.getInt("ad_loadAdIdsType");


                try {
                    FACEBOOK_B[0] = FBJsonObject.getString("Banner1");
                    FACEBOOK_I[0] = FBJsonObject.getString("Interstitial1");
                    FACEBOOK_I[1] = FBJsonObject.getString("Interstitial2");
                    FACEBOOK_N[0] = FBJsonObject.getString("Native1");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject TopOnJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("TopOn");
                topOn_AdStatus = TopOnJsonObject.getInt("ad_showAdStatus");
                topOn_loadAdIdsType = TopOnJsonObject.getInt("ad_loadAdIdsType");
                TopOn_appID = TopOnJsonObject.getString("AppID");
                TopOn_appKey = TopOnJsonObject.getString("AppOpen1");
                try {
                    TopOn_B[0] = TopOnJsonObject.getString("Banner1");
                    TopOn_I[0] = TopOnJsonObject.getString("Interstitial1");
                    TopOn_I[1] = TopOnJsonObject.getString("Interstitial2");
                    TopOn_N[0] = TopOnJsonObject.getString("Native1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              /*  TopOn_appKey = "4f7b9ac17decb9babec83aac078742c7";
                try {
                    TopOn_B[0] = "b5baca41a2536f";
                    TopOn_I[0] = "b5baca54674522";
                    TopOn_N[0] = "b5aa1fa501d9f6";
                } catch (Exception e) {
                    e.printStackTrace();
                }*/


                JSONObject MyAdJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("MyCustomAds");
                myCustom_AdStatus = MyAdJsonObject.getInt("ad_showAdStatus");

                try {
                    listner.onGetExtradata(response.getJSONObject("EXTRA_DATA"));
                    Log.e("EXTRA_DATA", response.getJSONObject("EXTRA_DATA").toString());
                } catch (Exception e) {
                    Log.e("extradata_error", e.getMessage());
                }


            } catch (Exception e) {
            }

//            if (app_redirectOtherAppStatus == 1) {
//                String redirectNewPackage = mysharedpreferences.getString("app_newPackageName", "");
//                listner.onRedirect(redirectNewPackage);
//            } else if (app_updateAppDialogStatus == 1 && checkUpdate(cversion)) {
//                listner.onUpdate("https://play.google.com/store/apps/details?id=" + activity.getPackageName());
//            } else {
            //  listner.onSuccess();
            // initAd(listner);
            initAd();
            listner.onSuccess();
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }

            // }
        }
    }

    private String implode(String[] placementList) {

        String str_ads = "";
        for (int i = 1; i < placementList.length; i++) {
            if (!placementList[i].isEmpty()) {
                if (str_ads.isEmpty()) {
                    str_ads = placementList[i];
                } else {
                    str_ads = str_ads + "," + placementList[i];
                }

            }
        }
        return str_ads;
    }

    private String getHigheCPMAdId(String platform, String adFormat, String whichOne) {
        String adId = "";


        if (whichOne.equals("First")) {
            SharedPreferences.Editor editor = mysharedpreferences.edit();
            if (platform.equals(ADMOB)) {
                if (adFormat.equals("I")) {
                    adId = ADMOB_I[0];
                    String ADMOB_I_list = implode(ADMOB_I);
                    editor.putString("ADMOB_I", ADMOB_I_list);
                }

            } else if (platform.equals(FACEBOOK)) {
                if (adFormat.equals("I")) {
                    adId = FACEBOOK_I[0];
                    String FACEBOOK_I_list = implode(FACEBOOK_I);
                    editor.putString("FACEBOOK_I", FACEBOOK_I_list);
                }
            } else if (platform.equals(TopOn)) {
                if (adFormat.equals("I")) {
                    adId = TopOn_I[0];
                    String TopOn_I_list = implode(TopOn_I);
                    editor.putString("TopOn_I", TopOn_I_list);
                }
            }

            editor.commit();
        } else if (whichOne.equals("Next")) {
            SharedPreferences.Editor editor = mysharedpreferences.edit();
            if (platform.equals(ADMOB)) {
                if (adFormat.equals("I")) {

                    String ADMOB_I_list = mysharedpreferences.getString("ADMOB_I", "");
                    if (!ADMOB_I_list.isEmpty()) {
                        String[] intA_list = ADMOB_I_list.split(",");
                        adId = intA_list[0];
                        ADMOB_I_list = implode(intA_list);
                        editor.putString("ADMOB_I", ADMOB_I_list);
                    }

                }

            } else if (platform.equals(FACEBOOK)) {
                if (adFormat.equals("I")) {
                    String FACEBOOK_I_list = mysharedpreferences.getString("FACEBOOK_I", "");
                    if (!FACEBOOK_I_list.isEmpty()) {
                        String[] intF_list = FACEBOOK_I_list.split(",");
                        adId = intF_list[0];
                        FACEBOOK_I_list = implode(intF_list);
                        editor.putString("FACEBOOK_I", FACEBOOK_I_list);
                    }
                }
            } else if (platform.equals(TopOn)) {
                if (adFormat.equals("I")) {
                    String TopOn_I_list = mysharedpreferences.getString("TopOn_I", "");
                    if (!TopOn_I_list.isEmpty()) {
                        String[] intF_list = TopOn_I_list.split(",");
                        adId = intF_list[0];
                        TopOn_I_list = implode(intF_list);
                        editor.putString("TopOn_I", TopOn_I_list);
                    }
                }
            }

            editor.commit();
        }


        return adId;
    }

    public void loadInterstitialAd(Activity activity, String google_i, String facebook_i, String topOn_i) {
        if (admob_loadAdIdsType == 2) {
            google_i = getHigheCPMAdId(ADMOB, "I", "First");
        }
        if (facebook_loadAdIdsType == 2) {
            facebook_i = getHigheCPMAdId(FACEBOOK, "I", "First");
        }
        if (topOn_loadAdIdsType == 2) {
            topOn_i = getHigheCPMAdId(FACEBOOK, "I", "First");
        }
        turnLoadInterstitialAd(activity, google_i, facebook_i, topOn_i);
    }

    public void loadInterstitialAd(Activity activity) {
        if (!mysharedpreferences.getBoolean("InAppPurchased", false)) {
            String google_i = "";
            String facebook_i = "";
            String topOn_i = "";

            if (admob_loadAdIdsType == 2) {
                google_i = getHigheCPMAdId(ADMOB, "I", "First");
            } else {
                google_i = getRandomPlacementId(ADMOB, "I");
            }
           /* if (facebook_loadAdIdsType == 2) {
                facebook_i = getHigheCPMAdId(FACEBOOK, "I", "First");
            } else {
                facebook_i = getRandomPlacementId(FACEBOOK, "I");
            }*/
            facebook_i = FACEBOOK_I[1];
            Log.e("FACEBOOK_I", facebook_i);

        /*    if (topOn_loadAdIdsType == 2) {
                topOn_i = getHigheCPMAdId(TopOn, "I", "First");
            } else {
                topOn_i = getRandomPlacementId(TopOn, "I");
            }*/

            topOn_i = TopOn_I[1];
            Log.e("topOn_i", topOn_i);

            turnLoadInterstitialAd(activity, google_i, facebook_i, topOn_i);
        }
    }

    public void turnLoadInterstitialAd(Activity activity, String google_i, String facebook_i, String topOn_i) {

        if (app_adShowStatus == 0) {
            return;
        }

        if (admob_AdStatus == 1 && !google_i.isEmpty() && !google_i_pre.equals(google_i)) {
            // loadAdmobInterstitial(activity, google_i);

            //Todo Hiral Ad code Change
            google_i_pre = google_i;
            AllInterstitialAdsPriorityGroupTwo.LoadInterstitialAd(activity, google_i, "Fail");
            AllInterstitialAdsPriority1GroupTwo.LoadInterstitialAd(activity, google_i, "Fail");

        }


        if (facebook_AdStatus == 1 && !facebook_i.isEmpty() && !facebook_i_pre.equals(facebook_i)) {
            loadFacebookInterstitial(activity, facebook_i);
        }
        if (topOn_AdStatus == 1 && !topOn_i.isEmpty() && !topOn_i_pre.equals(topOn_i)) {
            loadTopOnInterstitial(activity, topOn_i);
        }


    }

    public void loadFacebooknSplashInterstitialAd(final Activity activity, final getDataListner myCallback1) {
        if (app_adShowStatus == 0) {
            return;
        }


        final Dialog dialog = new Dialog(activity, R.style.fulldialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        dialog.setCancelable(false);
        TextView txt_adloading = dialog.findViewById(R.id.txt_adloading);
        txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        String facebook_i = "";

        /*if (facebook_loadAdIdsType == 2) {
            facebook_i = getHigheCPMAdId(FACEBOOK, "I", "First");
        } else {
            facebook_i = getRandomPlacementId(FACEBOOK, "I");
        }*/
        facebook_i = FACEBOOK_I[0];

        Log.e("facebook_i_splash", facebook_i);

        if (facebook_AdStatus == 1 && !facebook_i.isEmpty()) {
            com.facebook.ads.InterstitialAd interstitialAd = new com.facebook.ads.InterstitialAd(activity, facebook_i);
            // Create listeners for the Interstitial Ad
            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    // Interstitial ad displayed callback
                    Log.e(TAG, "Interstitial ad displayed.");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    // Interstitial dismissed callback
                    Log.e(TAG, "Interstitial ad dismissed.");
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    myCallback1.onSuccess();
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    Log.e(TAG, "Splash FB Interstitial ad failed to load: " + adError.getErrorMessage());
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    myCallback1.onSuccess();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Interstitial ad is loaded and ready to be displayed
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                    // Show the ad
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {
                        if (!dialog.isShowing()) {
                            dialog.show();
                        }

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    interstitialAd.show();
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                            interstitialAd.show();
                        }

                    } else {
                        interstitialAd.show();
                    }

                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                    Log.d(TAG, "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                    Log.d(TAG, "Interstitial ad impression logged!");
                }
            };

            // For auto play video ads, it's recommended to load the ad
            // at least 30 seconds before it is shown
            interstitialAd.loadAd(
                    interstitialAd.buildLoadAdConfig()
                            .withAdListener(interstitialAdListener)
                            .build());


        }

    }

    public void loadTopOnSplashInterstitialAd(final Activity activity, final getDataListner myCallback1) {
        ATInterstitial mInterstitialAd = new ATInterstitial(activity, TopOn_I[0]);
        Log.e("topOn_i_splash", TopOn_I[0]);
        mInterstitialAd.load();
        mInterstitialAd.setAdListener(new ATInterstitialListener() {
            @Override
            public void onInterstitialAdLoaded() {

                if (app_adShowStatus == 0) {
                    return;
                }


                dialog = new Dialog(activity, R.style.fulldialog);
                View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(view);

                dialog.setCancelable(false);
                TextView txt_adloading = dialog.findViewById(R.id.txt_adloading);
                txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {
                    if (!dialog.isShowing()) {
                        dialog.show();
                    }

                    try {
                        new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                mInterstitialAd.show(activity);
                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mInterstitialAd.show(activity);
                    }

                } else {
                    mInterstitialAd.show(activity);
                }
            }

            @Override
            public void onInterstitialAdLoadFail(com.anythink.core.api.AdError adError) {
                // Toast.makeText(activity, "TopOn interAD failed to load1", Toast.LENGTH_LONG).show();
                myCallback1.onSuccess();
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo entity) {

            }

            @Override
            public void onInterstitialAdShow(ATAdInfo entity) {

            }

            @Override
            public void onInterstitialAdClose(ATAdInfo entity) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                myCallback1.onSuccess();
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {

            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                myCallback1.onSuccess();
            }


            @Override
            public void onInterstitialAdVideoError(com.anythink.core.api.AdError adError) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                // myCallback1.onSuccess();
            }
        });
        mInterstitialAd.load();
    }

    //TODO Hiral Splash Inter
    public void loadSplashInterstitialAd(final Activity activity, final getDataListner myCallback1) {
        if (app_adShowStatus == 0) {
            return;
        }


        final Dialog dialog = new Dialog(activity, R.style.fulldialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        dialog.setCancelable(false);
        TextView txt_adloading = dialog.findViewById(R.id.txt_adloading);
        txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        String google_i = "";

        if (admob_loadAdIdsType == 2) {
            google_i = getHigheCPMAdId(ADMOB, "I", "First");
        } else {
            google_i = getRandomPlacementId(ADMOB, "I");
        }

        if (admob_AdStatus == 1 && !google_i.isEmpty()) {

            this.google_i_pre = google_i;

            AdRequest adRequest = new AdRequest.Builder().build();

            InterstitialAd.load(activity, google_i, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.


                    mInterstitialAd = interstitialAd;
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {
                        if (!dialog.isShowing()) {
                            dialog.show();
                        }

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {

                                    mInterstitialAd.show(activity);
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                            mInterstitialAd.show(activity);
                        }

                    } else {
                        mInterstitialAd.show(activity);
                    }

                    // mInterstitialAd.show(activity);
                    Log.i(TAG, "onAdLoaded");

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            Log.d("TAG", "The ad was dismissed.");
                            //    interstitialCallBack();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            myCallback1.onSuccess();

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            // Called when fullscreen content failed to show.
                            Log.d("TAG", "The ad failed to show.");
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            myCallback1.onSuccess();
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when fullscreen content is shown.
                            // Make sure to set your reference to null so you don't
                            // show it a second time.
                            mInterstitialAd = null;
                            Log.d("TAG", "The ad was shown.");
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i(TAG, loadAdError.getMessage());
                    mInterstitialAd = null;

                    myCallback1.onSuccess();
                }
            });
        }

/*
        if (facebook_AdStatus == 1 && !facebook_i.isEmpty() && !facebook_i_pre.equals(facebook_i)) {
            loadFacebookInterstitial(activity, facebook_i);
        }*/
    }

    //Todo Hiral Ad code Change
    public void ShowInterstitialAdsOnCreate(Context context) {

        if (app_adShowStatus == 0 || mysharedpreferences.getBoolean("InAppPurchased", false)) {
            return;
        }
        if (app_adShowStatus == 0) {
            return;
        }
        int TIME_INTERVAL = Integer.parseInt(mysharedpreferences.getString("AdDelaytimeInterval", "0"));
        if (mShownAds + (TIME_INTERVAL * 1000) > System.currentTimeMillis()) {
            return;
        }
        mShownAds = System.currentTimeMillis();


        if (impressionCountCheck()) {
            String redirectNewPackage = mysharedpreferences.getString("app_newPackageName", "");
            showRedirectDialog(redirectNewPackage);
            return;
        }

        count_click_for_alt++;

        String specific_platform = "";
        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");

        if (!specific_platform.isEmpty()) {
            app_howShowAd = 0;
            adPlatformSequence = specific_platform;
        }


        interstitial_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                interstitial_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_click_for_alt % alernateAd.length == j) {
                    index = j;
                    interstitial_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (interstitial_sequence.size() != 0) {
                    if (!interstitial_sequence.get(0).equals(adSequence[j])) {
                        interstitial_sequence.add(adSequence[j]);
                    }
                }

            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }

        if (interstitial_sequence.size() != 0) {


            if (interstitial_sequence.get(0).equals(ADMOB) && admob_AdStatus == 1) {
                if (app_adShowStatus != 0) {
                    Backprssornot = 0;
                    if (app_adShowStatus == 0) {
                        return;
                    }
                    if (app_mainClickCntSwAd == 0) {
                        return;
                    }
                    Log.e("FrontshowadsCounter", FrontshowadsCounter + "");

                    if (FrontshowadsCounter % app_mainClickCntSwAd == 0) {
                        int i = intertitialCounter;
                        if (i == 0) {
                            AllInterstitialAdsPriorityGroupTwo.ShowAdsOnCreate(context, google_i_pre);
                            intertitialCounter++;
                        } else if (i == 1) {
                            AllInterstitialAdsPriority1GroupTwo.ShowAdsOnCreate(context, google_i_pre);
                            intertitialCounter = 0;
                        }
                    } else {
                        FrontshowadsCounter++;
                    }
                }
            } else if (interstitial_sequence.get(0).equals(FACEBOOK) && facebook_AdStatus == 1 && fbinterstitialAd1 != null) {
                if (app_adShowStatus != 0) {
                    Backprssornot = 0;
                    if (app_adShowStatus == 0) {
                        return;
                    }
                    if (app_mainClickCntSwAd == 0) {
                        return;
                    }

                    if (FrontshowadsCounter % app_mainClickCntSwAd == 0) {
                        dialog_fbinter = new Dialog(context, R.style.fulldialog);
                        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
                        dialog_fbinter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog_fbinter.setContentView(view);


                        dialog_fbinter.setCancelable(false);
                        TextView txt_adloading = dialog_fbinter.findViewById(R.id.txt_adloading);
                        txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                        dialog_fbinter.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                        if (fbinterstitialAd1.isAdLoaded()) {

                            if (AppManage.Backprssornot == 0) {
                                AppManage.FrontshowadsCounter++;
                            }
                            if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                                dialog_fbinter.show();

                                try {
                                    new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        @Override
                                        public void onFinish() {

                                            fbinterstitialAd1.show();
                                        }
                                    }.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    dialog_fbinter.dismiss();
                                    fbinterstitialAd1.show();
                                }

                            } else {
                                fbinterstitialAd1.show();
                            }
                        } else {
//                fbinterstitialAd1.loadAd();
                            if (facebook_loadAdIdsType == 2) {
                                facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                            }
                            loadFacebookInterstitial((Activity) context, facebook_i_pre);
                            nextInterstitialPlatform(context);
                        }
                    } else {
                        FrontshowadsCounter++;
                    }
                }


            } else if (interstitial_sequence.get(0).equals(TopOn) && topOn_AdStatus == 1 && topOnmInterstitialAd != null) {
                if (app_adShowStatus != 0) {
                    Backprssornot = 0;
                    if (app_adShowStatus == 0) {
                        return;
                    }
                    if (app_mainClickCntSwAd == 0) {
                        return;
                    }

                    if (FrontshowadsCounter % app_mainClickCntSwAd == 0) {
                        dialog_fbinter = new Dialog(context, R.style.fulldialog);
                        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
                        dialog_fbinter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog_fbinter.setContentView(view);


                        dialog_fbinter.setCancelable(false);
                        TextView txt_adloading = dialog_fbinter.findViewById(R.id.txt_adloading);
                        txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                        dialog_fbinter.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                        if (topOnmInterstitialAd.isAdReady()) {
                            if (AppManage.Backprssornot == 0) {
                                AppManage.FrontshowadsCounter++;
                            }
                            if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                                dialog_fbinter.show();

                                try {
                                    new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        @Override
                                        public void onFinish() {

                                            topOnmInterstitialAd.show((Activity) context);
                                        }
                                    }.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    dialog_fbinter.dismiss();
                                    topOnmInterstitialAd.show((Activity) context);
                                }

                            } else {
                                topOnmInterstitialAd.show((Activity) context);
                            }
                        } else {
                            //  Toast.makeText(activity,"TopOn interAD failed to load",Toast.LENGTH_LONG).show();
//                fbinterstitialAd1.loadAd();
                            if (topOn_loadAdIdsType == 2) {
                                topOn_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                            }
                            loadTopOnInterstitial((Activity) context, topOn_i_pre);
                            nextInterstitialPlatform(context);
                        }
                    }
                }
            } else if (interstitial_sequence.get(0).equals(MyCustomAds) && myCustom_AdStatus == 1) {
                CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("Interstitial");
                if (customAdModel != null) {
                    try {
                        final CustomIntAds customIntAds = new CustomIntAds(activity, customAdModel);
                        customIntAds.setCanceledOnTouchOutside(false);
                        customIntAds.setCancelable(false);
                        customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                            public void onAdsCloseClick() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }

                            public void setOnKeyListener() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }
                        });
                        customIntAds.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        nextInterstitialPlatform(context);
                    }
                } else {
                    nextInterstitialPlatform(context);
                }

            } else {
                nextInterstitialPlatform(context);
            }
        }


    }

    private void loadTopOnInterstitial(final Activity activity, String topOn_i) {
/*        if (topOn_loadAdIdsType == 0) {
            topOn_i = getRandomPlacementId(TopOn, "I");
        }*/
        topOn_i_pre = topOn_i;
        Log.e("topOn_i", topOn_i);
        topOnmInterstitialAd = new ATInterstitial(activity, topOn_i);
        topOnmInterstitialAd.load();
        topOnmInterstitialAd.setAdListener(new ATInterstitialListener() {
            @Override
            public void onInterstitialAdLoaded() {
            }

            @Override
            public void onInterstitialAdLoadFail(com.anythink.core.api.AdError adError) {
                //    Toast.makeText(activity, "TopOn interAD failed to load", Toast.LENGTH_LONG).show();
                if (!topOn_i_pre.isEmpty()) {
                    // loadTopOnInterstitial(activity, topOn_i_pre);
                }
                /*if (topOn_loadAdIdsType == 2) {
                    topOn_i_pre = getHigheCPMAdId(TopOn, "I", "Next");
                    if (!topOn_i_pre.isEmpty()) {
                        loadTopOnInterstitial(activity, topOn_i_pre);
                    }
                }*/
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo entity) {
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo entity) {
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo entity) {
                dialog_fbinter.dismiss();

                if (topOn_loadAdIdsType == 2) {
                    topOn_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                }
                loadTopOnInterstitial(activity, topOn_i_pre);
                interstitialCallBack();
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {

            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {

            }


            @Override
            public void onInterstitialAdVideoError(com.anythink.core.api.AdError adError) {
            }
        });
    }

    private void loadFacebookInterstitial(final Activity activity, String facebook_i) {


     /*   if (facebook_loadAdIdsType == 0) {
            facebook_i = getRandomPlacementId(FACEBOOK, "I");
        }*/
        Log.e("facebook_i", facebook_i);
        facebook_i_pre = facebook_i;

        fbinterstitialAd1 = new com.facebook.ads.InterstitialAd(activity, facebook_i);
        fbinterstitialAd1.loadAd(fbinterstitialAd1.buildLoadAdConfig().withAdListener(new AbstractAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                super.onError(ad, error);
                Log.e("errorcodeFB", error.getErrorCode() + "  " + error.getErrorMessage());
                if (!facebook_i_pre.isEmpty()) {
                    //  loadFacebookInterstitial(activity, facebook_i_pre);
                }
              /*  if (facebook_loadAdIdsType == 2) {
                    facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "Next");
                    if (!facebook_i_pre.isEmpty()) {
                        loadFacebookInterstitial(activity, facebook_i_pre);
                    }
                }*/
            }

            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
//                fbinterstitialAd1.loadAd();
                dialog_fbinter.dismiss();

                if (facebook_loadAdIdsType == 2) {
                    facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                }
                loadFacebookInterstitial(activity, facebook_i_pre);
                interstitialCallBack();


            }
        }).build());
    }

    private void loadAdmobInterstitial(final Activity activity, String google_i) {

        if (admob_loadAdIdsType == 0) {
            google_i = getRandomPlacementId(ADMOB, "I");
        }
        this.google_i_pre = google_i;

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(activity, google_i, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                        if (admob_loadAdIdsType == 2) {
                            google_i_pre = getHigheCPMAdId(ADMOB, "I", "First");
                        }
                        loadAdmobInterstitial(activity, google_i_pre);
                        interstitialCallBack();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;

                if (admob_loadAdIdsType == 2) {
                    google_i_pre = getHigheCPMAdId(ADMOB, "I", "Next");
                    if (!google_i_pre.isEmpty()) {
                        loadAdmobInterstitial(activity, google_i_pre);
                    }
                }
            }
        });


    }

    private String getRandomPlacementId(String platform, String adFormat) {
        String return_adId = "";


        SharedPreferences.Editor editor_count = mysharedpreferences.edit();
        int count = 0;
        String[] platform_Format_Ids = {"", "", "", "", ""};
        if (platform.equals(ADMOB)) {
            if (adFormat.equals("B")) {
                platform_Format_Ids = ADMOB_B;

                count = mysharedpreferences.getInt("count_admob_B", 0) + 1;
                editor_count.putInt("count_admob_B", count);
            } else if (adFormat.equals("I")) {
                platform_Format_Ids = ADMOB_I;

                count = mysharedpreferences.getInt("count_admob_I", 0) + 1;
                editor_count.putInt("count_admob_I", count);
            } else if (adFormat.equals("N")) {
                platform_Format_Ids = ADMOB_N;

                count = mysharedpreferences.getInt("count_admob_N", 0) + 1;
                editor_count.putInt("count_admob_N", count);
            }

        } else if (platform.equals(FACEBOOK)) {
            if (adFormat.equals("B")) {
                platform_Format_Ids = FACEBOOK_B;

                count = mysharedpreferences.getInt("count_facebook_B", 0) + 1;
                editor_count.putInt("count_facebook_B", count);
            } else if (adFormat.equals("I")) {
                platform_Format_Ids = FACEBOOK_I;

                count = mysharedpreferences.getInt("count_facebook_I", 0) + 1;
                editor_count.putInt("count_facebook_I", count);
            } else if (adFormat.equals("N")) {
                platform_Format_Ids = FACEBOOK_N;

                count = mysharedpreferences.getInt("count_facebook_N", 0) + 1;
                editor_count.putInt("count_facebook_N", count);
            }
        } else if (platform.equals(TopOn)) {
            if (adFormat.equals("B")) {
                platform_Format_Ids = TopOn_B;

                count = mysharedpreferences.getInt("count_topOn_B", 0) + 1;
                editor_count.putInt("count_topOn_B", count);
            } else if (adFormat.equals("I")) {
                platform_Format_Ids = TopOn_I;

                count = mysharedpreferences.getInt("count_topOn_I", 0) + 1;
                editor_count.putInt("count_topOn_I", count);
            } else if (adFormat.equals("N")) {
                platform_Format_Ids = TopOn_N;

                count = mysharedpreferences.getInt("count_topOn_N", 0) + 1;
                editor_count.putInt("count_topOn_N", count);
            }
        }
        editor_count.commit();

        ArrayList<String> placemnt_Ids = new ArrayList<String>();
        for (int i = 0; i < platform_Format_Ids.length; i++) {
            if (!platform_Format_Ids[i].isEmpty()) {
                placemnt_Ids.add(platform_Format_Ids[i]);
            }
        }

        if (placemnt_Ids.size() != 0) {
            if (count % placemnt_Ids.size() == 0) {
                return_adId = placemnt_Ids.get(0);
            } else if (count % placemnt_Ids.size() == 1) {
                return_adId = placemnt_Ids.get(1);
            } else if (count % placemnt_Ids.size() == 2) {
                return_adId = placemnt_Ids.get(2);
            } else if (count % placemnt_Ids.size() == 3) {
                return_adId = placemnt_Ids.get(3);
            } else if (count % placemnt_Ids.size() == 4) {
                return_adId = placemnt_Ids.get(4);
            }
        }
        return return_adId;
    }

    public void showCustomAppOpenAd(Context context, MyCallback myCallback) {
        turnCustomAppOpenAd(context, myCallback, 0);
    }

    public void showCustomAppOpenAd(Context context, MyCallback myCallback, int how_many_clicks) {
        turnCustomAppOpenAd(context, myCallback, how_many_clicks);
    }

    public void turnCustomAppOpenAd(Context context, MyCallback myCallback2, int how_many_clicks) {
        myCallback = myCallback2;

        count_click++;

        if (app_adShowStatus == 0) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }
        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }

        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
       /* boolean app_AppOpenAdStatus = mysharedpreferences.getBoolean("app_AppOpenAdStatus", false);
        if (!app_AppOpenAdStatus) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }*/

        CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("AppOpen");
        if (customAdModel != null) {
            try {
                final CustomAppOpenAds customAppOpenAds = new CustomAppOpenAds(activity, R.style.Theme_AppOpen_Dialog, customAdModel);
                customAppOpenAds.setCanceledOnTouchOutside(false);
                customAppOpenAds.setCancelable(false);
                customAppOpenAds.setOnCloseListener(new CustomAppOpenAds.OnCloseListener() {
                    public void onAdsCloseClick() {
                        customAppOpenAds.dismiss();
                        interstitialCallBack();
                    }

                    public void setOnKeyListener() {
                        customAppOpenAds.dismiss();
                        interstitialCallBack();
                    }
                });
                customAppOpenAds.show();
            } catch (Exception e) {
                e.printStackTrace();

                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }


    }

    public void showInterstitialAd(Context context, MyCallback myCallback) {
        turnInterstitialAd(context, myCallback, 0, "");
    }

    public void showInterstitialAd(Context context, MyCallback myCallback, String specific_platform) {
        turnInterstitialAd(context, myCallback, 0, specific_platform);
    }

    public void showInterstitialAd(Context context, MyCallback myCallback, int how_many_clicks) {
        turnInterstitialAd(context, myCallback, how_many_clicks, "");
    }

    public void showInterstitialAd(Context context, MyCallback myCallback, int how_many_clicks, String specific_platform) {
        turnInterstitialAd(context, myCallback, how_many_clicks, specific_platform);
    }

    public void showInterstitialAd(Context context, MyCallback myCallback, String specific_platform, int how_many_clicks) {
        turnInterstitialAd(context, myCallback, how_many_clicks, specific_platform);
    }

    public void turnInterstitialAd(Context context, MyCallback myCallback2, int how_many_clicks, String specific_platform) {
        myCallback = myCallback2;

        count_click++;

        if (app_adShowStatus == 0) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }
        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }

        count_click_for_alt++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");

        if (!specific_platform.isEmpty()) {
            app_howShowAd = 0;
            adPlatformSequence = specific_platform;
        }


        interstitial_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                interstitial_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_click_for_alt % alernateAd.length == j) {
                    index = j;
                    interstitial_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (interstitial_sequence.size() != 0) {
                    if (!interstitial_sequence.get(0).equals(adSequence[j])) {
                        interstitial_sequence.add(adSequence[j]);
                    }
                }

            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }

        if (interstitial_sequence.size() != 0) {
            displayInterstitialAd(interstitial_sequence.get(0), context);
        }

    }

    /*private void displayInterstitialAd(String platform, final Context context) {
        final Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        if (platform.equals(ADMOB) && admob_AdStatus == 1) {
            if (mInterstitialAd != null) {
                if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {
                    dialog.show();

                    try {
                        new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                dialog.dismiss();
                                mInterstitialAd.show((Activity) context);
                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mInterstitialAd.show((Activity) context);
                    }

                } else {
                    mInterstitialAd.show((Activity) context);
                }
            } else {
                if (admob_loadAdIdsType == 2) {
                    google_i_pre = getHigheCPMAdId(ADMOB, "I", "First");
                }
                if (!google_i_pre.isEmpty()) {
                    loadAdmobInterstitial((Activity) context, google_i_pre);
                }

                nextInterstitialPlatform(context);
            }
        } else if (platform.equals(FACEBOOK) && facebook_AdStatus == 1 && fbinterstitialAd1 != null) {

            if (fbinterstitialAd1.isAdLoaded()) {
                if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                    dialog.show();

                    try {
                        new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                dialog.dismiss();
                                fbinterstitialAd1.show();
                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        fbinterstitialAd1.show();
                    }

                } else {
                    fbinterstitialAd1.show();
                }
            } else {
//                fbinterstitialAd1.loadAd();
                if (facebook_loadAdIdsType == 2) {
                    facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                }
                loadFacebookInterstitial((Activity) context, facebook_i_pre);
                nextInterstitialPlatform(context);
            }

        } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {
            CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("Interstitial");
            if (customAdModel != null) {
                try {
                    final CustomIntAds customIntAds = new CustomIntAds(activity, customAdModel);
                    customIntAds.setCanceledOnTouchOutside(false);
                    customIntAds.setCancelable(false);
                    customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                        public void onAdsCloseClick() {
                            customIntAds.dismiss();
                            interstitialCallBack();
                        }

                        public void setOnKeyListener() {
                            customIntAds.dismiss();
                            interstitialCallBack();
                        }
                    });
                    customIntAds.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    nextInterstitialPlatform(context);
                }
            } else {
                nextInterstitialPlatform(context);
            }

        } else {

            nextInterstitialPlatform(context);

        }
    }
*/
    private void displayInterstitialAd(String platform, final Context context) {

        if (interstitial_sequence.size() != 0) {


            if (interstitial_sequence.get(0).equals(ADMOB) && admob_AdStatus == 1) {
                if (app_adShowStatus != 0) {
                    Backprssornot = 0;
                    if (app_adShowStatus == 0) {
                        return;
                    }
                    if (app_mainClickCntSwAd == 0) {

                        return;
                    }

                    if (FrontshowadsCounter % app_mainClickCntSwAd == 0) {
                        int i = intertitialCounter;
                        if (i == 0) {
                            AllInterstitialAdsPriorityGroupTwo.ShowAdsOnCreate(context, google_i_pre);
                            intertitialCounter++;
                        } else if (i == 1) {
                            AllInterstitialAdsPriority1GroupTwo.ShowAdsOnCreate(context, google_i_pre);
                            intertitialCounter = 0;
                        }
                    } else {
                        FrontshowadsCounter++;
                    }
                }
            } else if (interstitial_sequence.get(0).equals(FACEBOOK) && facebook_AdStatus == 1 && fbinterstitialAd1 != null) {

                dialog_fbinter = new Dialog(context, R.style.fulldialog);
                View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
                dialog_fbinter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_fbinter.setContentView(view);


                dialog_fbinter.setCancelable(false);
                TextView txt_adloading = dialog_fbinter.findViewById(R.id.txt_adloading);
                txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                dialog_fbinter.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                if (fbinterstitialAd1.isAdLoaded()) {
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                        dialog_fbinter.show();

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {

                                    fbinterstitialAd1.show();
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog_fbinter.dismiss();
                            fbinterstitialAd1.show();
                        }

                    } else {
                        fbinterstitialAd1.show();
                    }
                } else {
//                fbinterstitialAd1.loadAd();
                    if (facebook_loadAdIdsType == 2) {
                        facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                    }
                    loadFacebookInterstitial((Activity) context, facebook_i_pre);
                    nextInterstitialPlatform(context);
                }

            } else if (interstitial_sequence.get(0).equals(TopOn) && topOn_AdStatus == 1 && topOnmInterstitialAd != null) {

                dialog_fbinter = new Dialog(context, R.style.fulldialog);
                View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
                dialog_fbinter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_fbinter.setContentView(view);


                dialog_fbinter.setCancelable(false);
                TextView txt_adloading = dialog_fbinter.findViewById(R.id.txt_adloading);
                txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                dialog_fbinter.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                if (topOnmInterstitialAd.isAdReady()) {
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                        dialog_fbinter.show();

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {

                                    topOnmInterstitialAd.show((Activity) context);
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog_fbinter.dismiss();
                            topOnmInterstitialAd.show((Activity) context);
                        }

                    } else {
                        topOnmInterstitialAd.show((Activity) context);
                    }
                } else {
//                fbinterstitialAd1.loadAd();
                    if (topOn_loadAdIdsType == 2) {
                        topOn_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                    }
                    loadTopOnInterstitial((Activity) context, topOn_i_pre);
                    nextInterstitialPlatform(context);
                }

            } else if (interstitial_sequence.get(0).equals(MyCustomAds) && myCustom_AdStatus == 1) {
                CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("Interstitial");
                if (customAdModel != null) {
                    try {
                        final CustomIntAds customIntAds = new CustomIntAds(activity, customAdModel);
                        customIntAds.setCanceledOnTouchOutside(false);
                        customIntAds.setCancelable(false);
                        customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                            public void onAdsCloseClick() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }

                            public void setOnKeyListener() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }
                        });
                        customIntAds.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        nextInterstitialPlatform(context);
                    }
                } else {
                    nextInterstitialPlatform(context);
                }

            } else {
                nextInterstitialPlatform(context);
            }
        }
    }

    private void nextInterstitialPlatform(Context context) {

        if (interstitial_sequence.size() != 0) {
            interstitial_sequence.remove(0);

            if (interstitial_sequence.size() != 0) {
                displayInterstitialAd(interstitial_sequence.get(0), context);
            } else {
                interstitialCallBack();
            }

        } else {
            interstitialCallBack();

        }
    }

    public void interstitialCallBack() {

        if (myCallback != null) {
            myCallback.callbackCall();
            myCallback = null;
        }
    }

    public void showBanner(ViewGroup banner_container, String admob_b, String facebook_b) {
        if (!mysharedpreferences.getBoolean("InAppPurchased", false)) {
            turnShowBanner(banner_container, ADMOB_B[0], FACEBOOK_B[0], TopOn_B[0]);
        }
    }

    public void showBanner(ViewGroup banner_container) {

        turnShowBanner(banner_container, "random", "random", "random");
    }

    public void turnShowBanner(ViewGroup banner_container, String admob_b, String facebook_b, String topOn_b) {
        this.admob_b = admob_b;
        this.facebook_b = facebook_b;
        this.topOn_b = topOn_b;


        if (!hasActiveInternetConnection(activity)) {
            return;
        }

        if (app_adShowStatus == 0) {
            return;
        }

        count_banner++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdBanner", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceBanner", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowBanner", "");


        banner_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                banner_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_banner % alernateAd.length == j) {
                    index = j;
                    banner_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (banner_sequence.size() != 0) {
                    if (!banner_sequence.get(0).equals(adSequence[j])) {
                        banner_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (banner_sequence.size() != 0) {
            displayBanner(banner_sequence.get(0), banner_container);
        }


    }

    public void displayBanner(String platform, ViewGroup banner_container) {
        if (platform.equals(ADMOB) && admob_AdStatus == 1) {
            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_b.equals("random")) && !admob_b.isEmpty()) {
                admob_b = getRandomPlacementId(ADMOB, "B");
            }

            showAdmobBanner(banner_container);
        } else if (platform.equals(FACEBOOK) && facebook_AdStatus == 1) {

            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_b.equals("random")) && !facebook_b.isEmpty()) {
                facebook_b = getRandomPlacementId(FACEBOOK, "B");
            }
            showFacebookBanner(banner_container);
        } else if (platform.equals(TopOn) && topOn_AdStatus == 1) {

            if ((topOn_loadAdIdsType == 0 || topOn_loadAdIdsType == 2 || topOn_b.equals("random")) && !topOn_b.isEmpty()) {
                topOn_b = getRandomPlacementId(TopOn, "B");
            }
            showTopOnBanner(banner_container);
        } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {
            showMyCustomBanner(banner_container);
        } else {
            nextBannerPlatform(banner_container);
        }
    }

    public int dip2px(float dipValue) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void showTopOnBanner(ViewGroup banner_container) {
        if (topOn_b.isEmpty() || topOn_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }
        ATBannerView mBannerView = new ATBannerView(activity);
        mBannerView.setPlacementId(topOn_b);

        banner_container.removeAllViews();
        banner_container.addView(mBannerView);
        mBannerView.loadAd();
        mBannerView.setBannerAdListener(new ATBannerExListener() {

            @Override
            public void onDeeplinkCallback(boolean isRefresh, ATAdInfo adInfo, boolean isSuccess) {
                Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
            }

            @Override
            public void onDownloadConfirm(Context context, ATAdInfo atAdInfo, ATNetworkConfirmInfo atNetworkConfirmInfo) {

            }

            @Override
            public void onBannerLoaded() {
                Log.i(TAG, "onBannerLoaded");

            }

            @Override
            public void onBannerFailed(com.anythink.core.api.AdError adError) {
                Log.i(TAG, "onBannerFailed: " + adError.getFullErrorInfo());
                banner_container.removeAllViews();
                nextBannerPlatform(banner_container);
            }

            @Override
            public void onBannerClicked(ATAdInfo entity) {
                Log.i(TAG, "onBannerClicked:" + entity.toString());

            }

            @Override
            public void onBannerShow(ATAdInfo entity) {
                Log.i(TAG, "onBannerShow:" + entity.toString());

            }

            @Override
            public void onBannerClose(ATAdInfo entity) {
                Log.i(TAG, "onBannerClose:" + entity.toString());

            }

            @Override
            public void onBannerAutoRefreshed(ATAdInfo entity) {
                Log.i(TAG, "onBannerAutoRefreshed:" + entity.toString());
            }

            @Override
            public void onBannerAutoRefreshFail(com.anythink.core.api.AdError adError) {
                Log.i(TAG, "onBannerAutoRefreshFail: " + adError.getFullErrorInfo());

            }
        });

    }

    private void nextBannerPlatform(ViewGroup banner_container) {
        if (banner_sequence.size() != 0) {
            banner_sequence.remove(0);
            if (banner_sequence.size() != 0) {
                displayBanner(banner_sequence.get(0), banner_container);
            }
        }
    }

    @SuppressLint("NewApi")
    private void showMyCustomBanner(final ViewGroup banner_container) {
        final CustomAdModel appModal = getMyCustomAd("Banner");
        if (appModal != null) {

            View inflate2 = LayoutInflater.from(activity).inflate(R.layout.cust_banner, banner_container, false);
            ImageView imageView2 = inflate2.findViewById(R.id.icon);
            TextView textView = inflate2.findViewById(R.id.primary);
            TextView textView2 = inflate2.findViewById(R.id.secondary);
            Button cta = inflate2.findViewById(R.id.cta);

            Glide
                    .with(activity)
                    .load(appModal.getApp_logo())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            banner_container.removeAllViews();
                            nextBannerPlatform(banner_container);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView2);

            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            cta.setText(appModal.getApp_buttonName());
            try {
                cta.setTextColor(ColorStateList.valueOf(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_TextColor", ""))));
                cta.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_Color", ""))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            inflate2.findViewById(R.id.cta).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        //TODO Hiral Native Banner Custom Click
                        getApp(activity, action_str);

                    } else {
                        Common.PlayStoreRedirectDialog(activity, action_str);
                    }

                }
            });
            banner_container.removeAllViews();
            banner_container.addView(inflate2);
            count_custBannerAd++;


        } else {
            nextBannerPlatform(banner_container);
        }

    }

    private void showFacebookBanner(final ViewGroup banner_container) {
        if (facebook_b.isEmpty() || facebook_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }

        final com.facebook.ads.AdView adView = new com.facebook.ads.AdView(activity, facebook_b, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                banner_container.removeAllViews();
                nextBannerPlatform(banner_container);

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                banner_container.removeAllViews();
                banner_container.addView(adView);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };

        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());


    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = ((Activity) activity).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    private void showAdmobBanner(final ViewGroup banner_container) {
        if (admob_b.isEmpty() || admob_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }

        final AdView mAdView = new AdView(activity);
        AdSize adSize = getAdSize();
        mAdView.setAdSize(adSize);
        mAdView.setAdUnitId(admob_b);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                banner_container.removeAllViews();
                nextBannerPlatform(banner_container);

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                banner_container.removeAllViews();
                banner_container.addView(mAdView);
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });

    }

    public void showNativeBanner(ViewGroup banner_container, String admobB, String facebookNB) {

        turnShowNativeBanner(banner_container, admobB, facebookNB);
    }

    public void showNativeBanner(ViewGroup banner_container) {

        turnShowNativeBanner(banner_container, "random", "random");
    }

    public void turnShowNativeBanner(ViewGroup banner_container, String admobB, String facebookNB) {
        this.admob_b = admobB;
        this.facebook_nb = facebookNB;
        if (app_adShowStatus == 0) {
            return;
        }

        count_banner++;
        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdBanner", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceBanner", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowBanner", "");


        banner_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                banner_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_banner % alernateAd.length == j) {
                    index = j;
                    banner_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (banner_sequence.size() != 0) {
                    if (!banner_sequence.get(0).equals(adSequence[j])) {
                        banner_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (banner_sequence.size() != 0) {
            displayNativeBanner(banner_sequence.get(0), banner_container);
        }
    }

    public void displayNativeBanner(String platform, ViewGroup banner_container) {
        if (platform.equals(ADMOB) && admob_AdStatus == 1) {
            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_b.equals("random")) && !admob_b.isEmpty()) {
                admob_b = getRandomPlacementId(ADMOB, "N");
            }

            showNativeAdmobBanner(banner_container);
        } else if (platform.equals(FACEBOOK) && facebook_AdStatus == 1) {
            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_nb.equals("random")) && !facebook_nb.isEmpty()) {
                facebook_nb = getRandomPlacementId(FACEBOOK, "NB");
            }

            showNativeFacebookBanner(banner_container);
        } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {
            showMyCustomNativeBanner(banner_container);
        } else {
            nextNativeBannerPlatform(banner_container);
        }
    }

    private void nextNativeBannerPlatform(ViewGroup banner_container) {
        if (banner_sequence.size() != 0) {
            banner_sequence.remove(0);
            if (banner_sequence.size() != 0) {
                displayNativeBanner(banner_sequence.get(0), banner_container);
            }
        }
    }

    @SuppressLint("NewApi")
    private void showMyCustomNativeBanner(final ViewGroup nbanner_container) {
        final CustomAdModel appModal = getMyCustomAd("NativeBanner");
        if (appModal != null) {

            View inflate2 = LayoutInflater.from(activity).inflate(R.layout.cust_native_banner, nbanner_container, false);
            ImageView imageView2 = inflate2.findViewById(R.id.icon);
            TextView textView = inflate2.findViewById(R.id.primary);
            TextView textView2 = inflate2.findViewById(R.id.secondary);

            TextView txt_rate = inflate2.findViewById(R.id.txt_rate);
            TextView txt_download = inflate2.findViewById(R.id.txt_download);
            Button cta = inflate2.findViewById(R.id.cta);


            LinearLayout ll_rate_download = inflate2.findViewById(R.id.ll_rate_download);

            String action_str = appModal.getApp_packageName();
            if (action_str.contains("http")) {
                ll_rate_download.setVisibility(View.GONE);

            } else {
                ll_rate_download.setVisibility(View.VISIBLE);
            }

            Glide
                    .with(activity)
                    .load(appModal.getApp_logo())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            nbanner_container.removeAllViews();
                            nextNativeBannerPlatform(nbanner_container);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView2);

            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            txt_rate.setText(appModal.getApp_rating());
            txt_download.setText(appModal.getApp_download());

            cta.setText(appModal.getApp_buttonName());
            try {
                cta.setTextColor(ColorStateList.valueOf(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_TextColor", ""))));
                cta.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_Color", ""))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            inflate2.findViewById(R.id.cta).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                       /* Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);*/

                        //TODO Hiral Native Banner Custom Click
                        getApp(activity, action_str);

                    } else {
                        Common.PlayStoreRedirectDialog(activity, action_str);
                    }

                }
            });
            nbanner_container.removeAllViews();
            nbanner_container.addView(inflate2);
            count_custNBAd++;


        } else {
            nextNativeBannerPlatform(nbanner_container);
        }

    }

    private void showNativeFacebookBanner(final ViewGroup container) {
        if (facebook_nb.isEmpty() || facebook_AdStatus == 0) {
            nextNativeBannerPlatform(container);
            return;
        }

        final NativeBannerAd nativeAd1 = new NativeBannerAd(activity, facebook_nb);
        nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                container.removeAllViews();
                container.setVisibility(View.VISIBLE);
                new Inflate_ADS(activity).inflate_NB_FB(nativeAd1, container);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                container.removeAllViews();
                nextNativeBannerPlatform(container);


            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAd1 == null || nativeAd1 != ad) {
                    return;
                }
                nativeAd1.downloadMedia();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        }).build());

    }

    private void showNativeAdmobBanner(final ViewGroup banner_container) {

        if (admob_b.isEmpty() || admob_AdStatus == 0) {
            nextNativeBannerPlatform(banner_container);
            return;
        }

        /*final AdView mAdView = new AdView(activity);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(admob_b);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                banner_container.removeAllViews();

                nextNativeBannerPlatform(banner_container);

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                banner_container.removeAllViews();
                banner_container.addView(mAdView);
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });*/

        final LayoutInflater from = LayoutInflater.from(activity);
        banner_container.removeAllViews();
        try {

            AdLoader.Builder builder = new AdLoader.Builder(activity, admob_b);
            builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @SuppressLint("WrongConstant")
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    View view = LayoutInflater.from(activity).inflate(R.layout.ads_am_activity_native_ads_temp_small, banner_container, false);
                    NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                    banner_container.removeAllViews();
                    banner_container.addView(view.getRootView());
                    view.findViewById(R.id.cardSmallTemplate).setVisibility(0);
                    ((TemplateView) view.findViewById(R.id.my_template_small)).setStyles(build);
                    ((TemplateView) view.findViewById(R.id.my_template_small)).setNativeAd(nativeAd);
                }
            });
            builder.withAdListener(new com.google.android.gms.ads.AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    Log.e("AMNativeBanner", "Error-" + loadAdError.getMessage());
                    nextNativeBannerPlatform(banner_container);
                }
            }).build().loadAd(new AdRequest.Builder().build());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNative(ViewGroup nativeAdContainer, String admobN, String facebookN) {
        if (!mysharedpreferences.getBoolean("InAppPurchased", false)) {
            turnShowNative(nativeAdContainer, ADMOB_N[0], FACEBOOK_N[0], TopOn_N[0]);
        }
    }

    public void showNative(ViewGroup nativeAdContainer) {

        turnShowNative(nativeAdContainer, "random", "random", "random");
    }

    public void turnShowNative(ViewGroup nativeAdContainer, String admobN, String facebookN, String topOnN) {
        this.admob_n = admobN;
        this.facebook_n = facebookN;
        this.topOn_n = topOnN;
        if (app_adShowStatus == 0) {
            return;
        }

        count_native++;
        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdNative", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceNative", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowNative", "");


        native_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                native_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_native % alernateAd.length == j) {
                    index = j;
                    native_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (native_sequence.size() != 0) {
                    if (!native_sequence.get(0).equals(adSequence[j])) {
                        native_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (native_sequence.size() != 0) {
            displayNative(native_sequence.get(0), nativeAdContainer);
        }

    }

    private void displayNative(String platform, ViewGroup nativeAdContainer) {
        if (platform.equals(ADMOB) && admob_AdStatus == 1) {
            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "N");
            }

            showAdmobNative(nativeAdContainer);

        } else if (platform.equals(FACEBOOK) && facebook_AdStatus == 1) {
            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_n.equals("random")) && !facebook_n.isEmpty()) {
                facebook_n = getRandomPlacementId(FACEBOOK, "N");
            }

            showFacebookNative(nativeAdContainer);
        } else if (platform.equals(TopOn) && topOn_AdStatus == 1) {
            if ((topOn_loadAdIdsType == 0 || topOn_loadAdIdsType == 2 || topOn_n.equals("random")) && !topOn_n.isEmpty()) {
                topOn_n = getRandomPlacementId(TopOn, "N");
            }

            showTopOnNative(nativeAdContainer);
        } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {
            showMyCustomNative(nativeAdContainer);
        } else {
            nextNativePlatform(nativeAdContainer);
        }
    }

    private void showTopOnNative(ViewGroup nativeAdContainer) {
        if (topOn_n.isEmpty() || topOn_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer);
            return;
        }
        if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
            nativeAdContainer.setVisibility(View.VISIBLE);
            View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_medium, nativeAdContainer, false).getRootView();
            nativeAdContainer.removeAllViews();
            TextView placeholder_text = view.findViewById(R.id.placeholder_text);
            placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
            nativeAdContainer.addView(view);
        }
        atNativeAdView = new ATNativeAdView(activity);

        final NativeDemoRender anyThinkRender = new NativeDemoRender(activity);
        atNatives = new ATNative(activity, topOn_n, new ATNativeNetworkListener() {
            @Override
            public void onNativeAdLoaded() {
                com.anythink.nativead.api.NativeAd nativeAd = atNatives.getNativeAd();

                if (nativeAd != null) {
                    if (atNativeAdView != null) {
                        atNativeAdView.removeAllViews();


                        if (atNativeAdView.getParent() == null) {
                            nativeAdContainer.removeAllViews();
                            nativeAdContainer.addView(atNativeAdView);
                        }
                    }
                    nativeAd.renderAdView(atNativeAdView, new NativeDemoRender(activity));
                    nativeAd.prepare(atNativeAdView, anyThinkRender.getClickView(), null);

                }

            }

            @Override
            public void onNativeAdLoadFail(com.anythink.core.api.AdError adError) {
                Log.e("toponerror", adError.toString());

            }
        });
        atNatives.makeAdRequest();

    }

    private void nextNativePlatform(ViewGroup nativeAdContainer) {

        if (native_sequence.size() != 0) {
            native_sequence.remove(0);
            if (native_sequence.size() != 0) {
                displayNative(native_sequence.get(0), nativeAdContainer);
            }
        }
    }

    @SuppressLint("NewApi")
    private void showMyCustomNative(final ViewGroup nativeAdContainer) {


        if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
            nativeAdContainer.setVisibility(View.VISIBLE);
            View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_small, nativeAdContainer, false).getRootView();
            nativeAdContainer.removeAllViews();
            TextView placeholder_text = view.findViewById(R.id.placeholder_text);
            placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
            nativeAdContainer.addView(view);
        }

        final CustomAdModel appModal = getMyCustomAd("Native");
        if (appModal != null) {
            nativeAdContainer.setVisibility(View.VISIBLE);
            final View inflate = LayoutInflater.from(activity).inflate(R.layout.cust_native, nativeAdContainer, false);
            ImageView imageView = inflate.findViewById(R.id.media_view);
            ImageView imageView2 = inflate.findViewById(R.id.icon);
            TextView textView = inflate.findViewById(R.id.primary);
            TextView textView2 = inflate.findViewById(R.id.body);
            TextView txt_rate = inflate.findViewById(R.id.txt_rate);
            TextView txt_download = inflate.findViewById(R.id.txt_download);
            Button cta = inflate.findViewById(R.id.cta);

            LinearLayout ll_rate_download = inflate.findViewById(R.id.ll_rate_download);

            String action_str = appModal.getApp_packageName();
            if (action_str.contains("http")) {
                ll_rate_download.setVisibility(View.GONE);

            } else {
                ll_rate_download.setVisibility(View.VISIBLE);
            }
            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            txt_rate.setText(appModal.getApp_rating());
            txt_download.setText(appModal.getApp_download());
            cta.setText(appModal.getApp_buttonName());

            try {
                cta.setTextColor(ColorStateList.valueOf(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_TextColor", ""))));
                cta.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_Color", ""))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            inflate.findViewById(R.id.cta).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
               /*         Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);*/

                        //TODO Hiral Native Banner Custom Click
                        getApp(activity, action_str);

                    } else {
                        Common.PlayStoreRedirectDialog(activity, action_str);
                    }
                }
            });

            Glide
                    .with(activity)
                    .load(appModal.getApp_banner())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            nativeAdContainer.removeAllViews();
                            nextNativePlatform(nativeAdContainer);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                            return false;
                        }
                    })
                    .into(imageView);

            Glide
                    .with(activity)
                    .load(appModal.getApp_logo())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView2);

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {

                        //TODO Hiral Native Banner Custom Click
                        getApp(activity, action_str);
                    } else {
                        Common.PlayStoreRedirectDialog(activity, action_str);
                    }
                }
            });

            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(inflate);
            count_custNativeAd++;


        } else {
            nextNativePlatform(nativeAdContainer);
        }


    }

    private void showFacebookNative(final ViewGroup nativeAdContainer) {
        if (facebook_n.isEmpty() || facebook_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer);
            return;
        }
        if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
            nativeAdContainer.setVisibility(View.VISIBLE);
            View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_medium, nativeAdContainer, false).getRootView();
            nativeAdContainer.removeAllViews();
            TextView placeholder_text = view.findViewById(R.id.placeholder_text);
            placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
            nativeAdContainer.addView(view);
        }
        final com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, facebook_n);

        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                nextNativePlatform(nativeAdContainer);


            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                new Inflate_ADS(activity).inflate_NATIV_FB(nativeAd, nativeAdContainer);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        }).build());
    }

    private void showAdmobNative(final ViewGroup nativeAdContainer) {
        if (admob_n.isEmpty() || admob_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer);
            return;
        }

        if (mysharedpreferences.getString("NativeAdMainType", "").equalsIgnoreCase("Advanced")) {
            if (mysharedpreferences.getString("NativeAdSubType", "").equalsIgnoreCase("Big")) {

                //Code Here
                if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
                    nativeAdContainer.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_big, nativeAdContainer, false).getRootView();
                    nativeAdContainer.removeAllViews();
                    TextView placeholder_text = view.findViewById(R.id.placeholder_text);
                    placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
                    nativeAdContainer.addView(view);
                }

                final AdLoader adLoader = new AdLoader.Builder(activity, admob_n)
                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {
                                // Show the ad.
                                new Inflate_ADS(activity).inflate_NATIV_ADMOB_BIG(nativeAd, nativeAdContainer);


                            }
                        })
                        .withAdListener(new com.google.android.gms.ads.AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                // Handle the failure by logging, altering the UI, and so on.
                                nextNativePlatform(nativeAdContainer);


                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder()
                                // Methods in the NativeAdOptions.Builder class can be
                                // used here to specify individual options settings.
                                .build())
                        .build();
                adLoader.loadAd(new AdRequest.Builder().build());

            } else if (mysharedpreferences.getString("NativeAdSubType", "").equalsIgnoreCase("Medium")) {
                if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
                    nativeAdContainer.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_medium, nativeAdContainer, false).getRootView();
                    nativeAdContainer.removeAllViews();
                    TextView placeholder_text = view.findViewById(R.id.placeholder_text);
                    placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
                    nativeAdContainer.addView(view);
                }

                final AdLoader adLoader = new AdLoader.Builder(activity, admob_n)
                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {
                                // Show the ad.
                                new Inflate_ADS(activity).inflate_NATIV_ADMOB(nativeAd, nativeAdContainer);


                            }
                        })
                        .withAdListener(new com.google.android.gms.ads.AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                // Handle the failure by logging, altering the UI, and so on.
                                nextNativePlatform(nativeAdContainer);


                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder()
                                // Methods in the NativeAdOptions.Builder class can be
                                // used here to specify individual options settings.
                                .build())
                        .build();
                adLoader.loadAd(new AdRequest.Builder().build());
            } else if (mysharedpreferences.getString("NativeAdSubType", "").equalsIgnoreCase("Small")) {

                //Code Here
                if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
                    nativeAdContainer.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_small, nativeAdContainer, false).getRootView();
                    nativeAdContainer.removeAllViews();
                    TextView placeholder_text = view.findViewById(R.id.placeholder_text);
                    placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
                    nativeAdContainer.addView(view);
                }

                final AdLoader adLoader = new AdLoader.Builder(activity, admob_n)
                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {
                                // Show the ad.
                                new Inflate_ADS(activity).inflate_NATIV_ADMOB_SMALL(nativeAd, nativeAdContainer);


                            }
                        })
                        .withAdListener(new com.google.android.gms.ads.AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                // Handle the failure by logging, altering the UI, and so on.
                                nextNativePlatform(nativeAdContainer);


                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder()
                                // Methods in the NativeAdOptions.Builder class can be
                                // used here to specify individual options settings.
                                .build())
                        .build();
                adLoader.loadAd(new AdRequest.Builder().build());

            }

        } else if (mysharedpreferences.getString("NativeAdMainType", "").equalsIgnoreCase("Template")) {
            if (mysharedpreferences.getString("NativeAdSubType", "").equalsIgnoreCase("Big")) {

                //Code Here
                if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
                    nativeAdContainer.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_big, nativeAdContainer, false).getRootView();
                    nativeAdContainer.removeAllViews();
                    TextView placeholder_text = view.findViewById(R.id.placeholder_text);
                    placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
                    nativeAdContainer.addView(view);
                }


                try {

                    AdLoader.Builder builder = new AdLoader.Builder(activity, admob_n);
                    builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @SuppressLint("WrongConstant")
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            View view = LayoutInflater.from(activity).inflate(R.layout.ads_am_activity_native_ads_temp_big, nativeAdContainer, false);
                            NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                            nativeAdContainer.removeAllViews();
                            nativeAdContainer.addView(view.getRootView());
                            view.findViewById(R.id.cardSmallTemplate).setVisibility(0);
                            ((TemplateView) view.findViewById(R.id.my_template_small)).setStyles(build);
                            ((TemplateView) view.findViewById(R.id.my_template_small)).setNativeAd(nativeAd);
                        }
                    });
                    builder.withAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            Log.e("AMNativeBanner", "Error-" + loadAdError.getMessage());
                            nextNativePlatform(nativeAdContainer);
                        }
                    }).build().loadAd(new AdRequest.Builder().build());


                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (mysharedpreferences.getString("NativeAdSubType", "").equalsIgnoreCase("Medium")) {

                //Code Here

                if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
                    nativeAdContainer.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_medium, nativeAdContainer, false).getRootView();
                    nativeAdContainer.removeAllViews();
                    TextView placeholder_text = view.findViewById(R.id.placeholder_text);
                    placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
                    nativeAdContainer.addView(view);
                }


                try {

                    AdLoader.Builder builder = new AdLoader.Builder(activity, admob_n);
                    builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @SuppressLint("WrongConstant")
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            View view = LayoutInflater.from(activity).inflate(R.layout.ads_am_activity_native_ads_temp_medium, nativeAdContainer, false);
                            NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                            nativeAdContainer.removeAllViews();
                            nativeAdContainer.addView(view.getRootView());
                            view.findViewById(R.id.cardSmallTemplate).setVisibility(0);
                            ((TemplateView) view.findViewById(R.id.my_template_small)).setStyles(build);
                            ((TemplateView) view.findViewById(R.id.my_template_small)).setNativeAd(nativeAd);
                        }
                    });
                    builder.withAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            Log.e("AMNativeBanner", "Error-" + loadAdError.getMessage());
                            nextNativePlatform(nativeAdContainer);
                        }
                    }).build().loadAd(new AdRequest.Builder().build());


                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (mysharedpreferences.getString("NativeAdSubType", "").equalsIgnoreCase("Small")) {

                if (mysharedpreferences.getString("NativePlaceHolder", "").equalsIgnoreCase("on")) {
                    nativeAdContainer.setVisibility(View.VISIBLE);
                    View view = LayoutInflater.from(activity).inflate(R.layout.sdk_default_ads_container_small, nativeAdContainer, false).getRootView();
                    nativeAdContainer.removeAllViews();
                    TextView placeholder_text = view.findViewById(R.id.placeholder_text);
                    placeholder_text.setText(mysharedpreferences.getString("NativePlaceHolderText", ""));
                    nativeAdContainer.addView(view);
                }


                try {

                    AdLoader.Builder builder = new AdLoader.Builder(activity, admob_n);
                    builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @SuppressLint("WrongConstant")
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            View view = LayoutInflater.from(activity).inflate(R.layout.ads_am_activity_native_ads_temp_small, nativeAdContainer, false);
                            NativeTemplateStyle build = new NativeTemplateStyle.Builder().build();
                            nativeAdContainer.removeAllViews();
                            nativeAdContainer.addView(view.getRootView());
                            view.findViewById(R.id.cardSmallTemplate).setVisibility(0);
                            ((TemplateView) view.findViewById(R.id.my_template_small)).setStyles(build);
                            ((TemplateView) view.findViewById(R.id.my_template_small)).setNativeAd(nativeAd);
                        }
                    });
                    builder.withAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            Log.e("AMNativeBanner", "Error-" + loadAdError.getMessage());
                            nextNativePlatform(nativeAdContainer);
                        }
                    }).build().loadAd(new AdRequest.Builder().build());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }

    //TODO Hiral
    public void CloseActivityWithAds(final Activity activity, final String str) {
        if (app_adShowStatus == 0 || !Common.isNetworkConnected(activity) || mysharedpreferences.getBoolean("InAppPurchased", false)) {
            intertitialCounter = 0;
            betaCounter = 0;
            sdkCounter = 0;
            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                activity.finish();
            }
            return;
        }

        int TIME_INTERVAL = Integer.parseInt(mysharedpreferences.getString("AdDelaytimeInterval", "0"));
        if (mShownAds + (TIME_INTERVAL * 1000) > System.currentTimeMillis()) {
            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                activity.finish();
            }
            return;
        }
        mShownAds = System.currentTimeMillis();

        if (impressionCountCheck()) {
            String redirectNewPackage = mysharedpreferences.getString("app_newPackageName", "");
            showRedirectDialog(redirectNewPackage);
            return;
        }

        Backprssornot = 1;
        if (mysharedpreferences.getString("BackPress_Priority", "").equalsIgnoreCase("Inter")) {
            int i = alternetCounter + 1;
            alternetCounter = i;
            if (i == app_innerClickCntSwAd) {
                alternetCounter = 0;
                int i2 = intertitialCounter;
                if (i2 == 0) {
                    AllInterstitialAdsPriorityGroupTwo.CloseActivityWithAds(activity, google_i_pre, str);
                    intertitialCounter++;
                } else if (i2 == 1) {
                    AllInterstitialAdsPriority1GroupTwo.CloseActivityWithAds(activity, google_i_pre, str);
                    intertitialCounter = 0;
                }
            } else if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                activity.finish();
            }
        } else if (mysharedpreferences.getString("BackPress_Priority", "").equalsIgnoreCase("Facebook")) {

            int i = alternetCounter + 1;
            alternetCounter = i;
            if (i == app_innerClickCntSwAd) {
                alternetCounter = 0;
                dialog_fbinter = new Dialog(activity, R.style.fulldialog);
                View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
                dialog_fbinter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_fbinter.setContentView(view);


                dialog_fbinter.setCancelable(false);
                TextView txt_adloading = dialog_fbinter.findViewById(R.id.txt_adloading);
                txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                dialog_fbinter.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                if (fbinterstitialAd1.isAdLoaded()) {

                    if (AppManage.Backprssornot == 0) {
                        AppManage.FrontshowadsCounter++;
                    }
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                        dialog_fbinter.show();

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {

                                    fbinterstitialAd1.show();
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog_fbinter.dismiss();
                            fbinterstitialAd1.show();
                        }

                    } else {
                        fbinterstitialAd1.show();
                    }

                    fbinterstitialAd1.buildLoadAdConfig().withAdListener(new AbstractAdListener() {
                        @Override
                        public void onError(Ad ad, AdError error) {
                            super.onError(ad, error);
                            Log.e("errorcodeFB", error.getErrorCode() + "  " + error.getErrorMessage());
                            dialog_fbinter.dismiss();
                            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                                activity.finish();
                            }
                            if (facebook_loadAdIdsType == 2) {
                                facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                            }
                            loadFacebookInterstitial(activity, facebook_i_pre);
                            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                                activity.finish();
                            }

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            super.onAdLoaded(ad);
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            super.onInterstitialDismissed(ad);
                            dialog_fbinter.dismiss();
                            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                                activity.finish();
                            }
                            if (facebook_loadAdIdsType == 2) {
                                facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                            }
                            loadFacebookInterstitial(activity, facebook_i_pre);


                        }
                    });

                } else {
                    if (facebook_loadAdIdsType == 2) {
                        facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                    }
                    loadFacebookInterstitial(activity, facebook_i_pre);
                    if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                        activity.finish();
                    }

                }

            } else if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                activity.finish();
            }


        } else if (mysharedpreferences.getString("BackPress_Priority", "").equalsIgnoreCase("TopOn")) {
            int i5 = alternetCounter + 1;
            alternetCounter = i5;
            if (i5 == app_innerClickCntSwAd) {
                alternetCounter = 0;
                dialog_fbinter = new Dialog(activity, R.style.fulldialog);
                View view = LayoutInflater.from(activity).inflate(R.layout.ad_progress_dialog, null);
                dialog_fbinter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_fbinter.setContentView(view);


                dialog_fbinter.setCancelable(false);
                TextView txt_adloading = dialog_fbinter.findViewById(R.id.txt_adloading);
                txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
                dialog_fbinter.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                if (topOnmInterstitialAd.isAdReady()) {
                    if (AppManage.Backprssornot == 0) {
                        AppManage.FrontshowadsCounter++;
                    }
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {

                        dialog_fbinter.show();

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {

                                    topOnmInterstitialAd.show(activity);
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog_fbinter.dismiss();
                            topOnmInterstitialAd.show(activity);
                        }

                    } else {
                        topOnmInterstitialAd.show(activity);
                    }
                    topOnmInterstitialAd.setAdListener(new ATInterstitialListener() {
                        @Override
                        public void onInterstitialAdLoaded() {

                        }

                        @Override
                        public void onInterstitialAdLoadFail(com.anythink.core.api.AdError adError) {
                            if (topOn_loadAdIdsType == 2) {
                                topOn_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                            }
                            loadTopOnInterstitial(activity, topOn_i_pre);

                            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                                activity.finish();
                            }
                        }

                        @Override
                        public void onInterstitialAdClicked(ATAdInfo atAdInfo) {

                        }

                        @Override
                        public void onInterstitialAdShow(ATAdInfo atAdInfo) {

                        }

                        @Override
                        public void onInterstitialAdClose(ATAdInfo atAdInfo) {
                            if (topOn_loadAdIdsType == 2) {
                                topOn_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                            }
                            loadTopOnInterstitial(activity, topOn_i_pre);

                            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                                activity.finish();
                            }
                        }

                        @Override
                        public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {

                        }

                        @Override
                        public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {

                        }

                        @Override
                        public void onInterstitialAdVideoError(com.anythink.core.api.AdError adError) {

                        }
                    });
                } else {
                    //  Toast.makeText(activity,"TopOn interAD failed to load",Toast.LENGTH_LONG).show();
//                fbinterstitialAd1.loadAd();

                    if (topOn_loadAdIdsType == 2) {
                        topOn_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                    }
                    loadTopOnInterstitial(activity, topOn_i_pre);
                    if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                        activity.finish();
                    }
                }
            } else if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                activity.finish();
            }

        } else {
            intertitialCounter = 0;
            betaCounter = 0;
            sdkCounter = 0;
            if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                activity.finish();
            }
        }

    }

    public interface MyCallback {
        void callbackCall();
    }

    public class NativeDemoRender implements ATNativeAdRenderer<CustomNativeAd> {

        Context mContext;
        List<View> mClickView = new ArrayList<>();
        View mCloseView;
        View mDevelopView;
        int mNetworkFirmId;

        public NativeDemoRender(Context context) {
            mContext = context;
        }

        @Override
        public View createView(Context context, int networkFirmId) {
            if (mDevelopView == null) {
                mDevelopView = LayoutInflater.from(context).inflate(R.layout.native_ad_item, null);
            }
            mNetworkFirmId = networkFirmId;
            if (mDevelopView.getParent() != null) {
                ((ViewGroup) mDevelopView.getParent()).removeView(mDevelopView);
            }
            return mDevelopView;
        }

        @Override
        public void renderAdView(View view, CustomNativeAd ad) {
            mClickView.clear();
            TextView titleView = view.findViewById(R.id.native_ad_title);
            TextView descView = view.findViewById(R.id.native_ad_desc);
            TextView ctaView = view.findViewById(R.id.native_ad_install_btn);
            TextView adFromView = view.findViewById(R.id.native_ad_from);
            FrameLayout contentArea = view.findViewById(R.id.native_ad_content_image_area);
            FrameLayout iconArea = view.findViewById(R.id.native_ad_image);
            final ATNativeImageView logoView = view.findViewById(R.id.native_ad_logo);

            // bind close button
            CustomNativeAd.ExtraInfo extraInfo = new CustomNativeAd.ExtraInfo.Builder()
                    .setCloseView(mCloseView)
                    .build();
            ad.setExtraInfo(extraInfo);

            titleView.setText("");
            descView.setText("");
            ctaView.setText("");
            adFromView.setText("");
            titleView.setText("");
            contentArea.removeAllViews();
            iconArea.removeAllViews();
            logoView.setImageDrawable(null);

            View mediaView = ad.getAdMediaView(contentArea, contentArea.getWidth());

            String type = CustomNativeAd.NativeAdConst.UNKNOWN_TYPE;
            switch (ad.getAdType()) {
                case CustomNativeAd.NativeAdConst.VIDEO_TYPE:
                    type = "Video";
                    break;
                case CustomNativeAd.NativeAdConst.IMAGE_TYPE:
                    type = "Image";
                    break;
            }
            Log.i("NativeDemoRender", "Ad type:" + type);

            if (ad.isNativeExpress()) {// Template rendering
                titleView.setVisibility(View.GONE);
                descView.setVisibility(View.GONE);
                ctaView.setVisibility(View.GONE);
                logoView.setVisibility(View.GONE);
                iconArea.setVisibility(View.GONE);
                if (mCloseView != null) {
                    mCloseView.setVisibility(View.GONE);
                }
                if (mediaView.getParent() != null) {
                    ((ViewGroup) mediaView.getParent()).removeView(mediaView);
                }

                contentArea.addView(mediaView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
                return;
            }

            // Custom rendering

            titleView.setVisibility(View.VISIBLE);
            descView.setVisibility(View.VISIBLE);
            ctaView.setVisibility(View.VISIBLE);
            logoView.setVisibility(View.VISIBLE);
            iconArea.setVisibility(View.VISIBLE);
            if (mCloseView != null) {
                mCloseView.setVisibility(View.VISIBLE);
            }
            View adiconView = ad.getAdIconView();


            final ATNativeImageView iconView = new ATNativeImageView(mContext);
            if (adiconView == null) {
                iconArea.addView(iconView);
                iconView.setImage(ad.getIconImageUrl());
                mClickView.add(iconView);
            } else {
                iconArea.addView(adiconView);
            }


            if (!TextUtils.isEmpty(ad.getAdChoiceIconUrl())) {
                logoView.setImage(ad.getAdChoiceIconUrl());
            } else {
//            logoView.setImageResource(R.drawable.ad_logo);
            }


            if (mediaView != null) {
                if (mediaView.getParent() != null) {
                    ((ViewGroup) mediaView.getParent()).removeView(mediaView);
                }

                contentArea.addView(mediaView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

            } else {

                final ATNativeImageView imageView = new ATNativeImageView(mContext);

                imageView.setImage(ad.getMainImageUrl());
                ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                contentArea.addView(imageView, params);

                mClickView.add(imageView);
            }

            titleView.setText(ad.getTitle());
            descView.setText(ad.getDescriptionText());
            ctaView.setText(ad.getCallToActionText());
            if (!TextUtils.isEmpty(ad.getAdFrom())) {
                adFromView.setText(ad.getAdFrom() != null ? ad.getAdFrom() : "");
                adFromView.setVisibility(View.VISIBLE);
            } else {
                adFromView.setVisibility(View.GONE);
            }

            mClickView.add(titleView);
            mClickView.add(descView);
            mClickView.add(ctaView);

        }

        public List<View> getClickView() {
            return mClickView;
        }

        public void setCloseView(ImageView closeView) {
            this.mCloseView = closeView;

        }
    }
}

