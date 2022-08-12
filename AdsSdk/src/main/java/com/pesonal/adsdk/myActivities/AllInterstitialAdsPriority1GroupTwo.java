package com.pesonal.adsdk.myActivities;

import static com.pesonal.adsdk.AppManage.app_adShowStatus;
import static com.pesonal.adsdk.AppManage.mysharedpreferences;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.CustomAdModel;
import com.pesonal.adsdk.CustomIntAds;
import com.pesonal.adsdk.R;

public class AllInterstitialAdsPriority1GroupTwo {

    public static InterstitialAd AMInterstitial1 = null;
    public static boolean FBCreateLoadedFlag1 = false;
    public static boolean FBCreateRequestFlag1 = false;

    public static String FinishTag1 = "";

    public static onInterstitialAdsClose adsListener2;
    private static Context mContext;
    private static Dialog dialog;

    public static void LoadInterstitialAd(Context context, String google_i, String str) {
        try {
            mContext = context;
            if (app_adShowStatus != 0) {
                MobileAds.initialize(context, new OnInitializationCompleteListener() {
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });
                MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(AppManage.TestDeviceID).build());
                if (!FBCreateLoadedFlag1) {
                    FinishTag1 = str;
                    FBCreateLoadedFlag1 = true;
                    FBCreateRequestFlag1 = true;
                    displayAdMobFourInAd(google_i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayAdMobFourInAd(String google_i) {
        AdRequest build = new AdRequest.Builder().build();
        try {
            Context context = mContext;
            InterstitialAd.load(context, google_i, build, new InterstitialAdLoadCallback() {
                public void onAdLoaded(InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    InterstitialAd unused = AllInterstitialAdsPriority1GroupTwo.AMInterstitial1 = interstitialAd;
                    AllInterstitialAdsPriority1GroupTwo.FBCreateRequestFlag1 = false;
                    Log.e("Priority1GroupTwo", "onAdLoaded.");
                    AllInterstitialAdsPriority1GroupTwo.AMCallBack();
                }

                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Log.e("Priority1GroupTwo", "Error." + loadAdError.getCode() + "-" + loadAdError);
                    AllInterstitialAdsPriority1GroupTwo.LoadSDKInterstitialAd();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void AMCallBack() {
        AMInterstitial1.setFullScreenContentCallback(new FullScreenContentCallback() {
            public void onAdDismissedFullScreenContent() {
                if (!AllInterstitialAdsPriority1GroupTwo.FinishTag1.equals("Fail")) {
                    AllInterstitialAdsPriority1GroupTwo.adsListener2.onAdsClose();
                }
                AppManage.Strcheckad = "StrClosed";


                Log.e("Priority1GroupTwo", "The ad was dismissed.");
            }

            public void onAdFailedToShowFullScreenContent(AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                Log.e("Priority1GroupTwo", "onAdFailedToShowFullScreenContent.");
                if (!AllInterstitialAdsPriority1GroupTwo.FinishTag1.equals("Fail")) {
                    AllInterstitialAdsPriority1GroupTwo.adsListener2.onAdsClose();
                }
                AppManage.Strcheckad = "StrClosed";
            }

            public void onAdShowedFullScreenContent() {
                Log.e("Priority1GroupTwo", "The ad was shown.");
                if (AppManage.Backprssornot == 0) {
                    AppManage.FrontshowadsCounter++;
                }
            }
        });
    }


    public static void OurThemeAdddd(Context context) {
        Log.e("sdkInter2", "Show");
        CustomAdModel customAdModel = AppManage.getInstance(context).getMyCustomAd("Interstitial");
        if (customAdModel != null) {
            try {
                final CustomIntAds customIntAds = new CustomIntAds(context, customAdModel);
                customIntAds.setCanceledOnTouchOutside(false);
                customIntAds.setCancelable(false);
                customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                    public void onAdsCloseClick() {
                        customIntAds.dismiss();
                        if (!FinishTag1.equals("Fail")) {
                            adsListener2.onAdsClose();
                        }
                    }

                    public void setOnKeyListener() {
                        customIntAds.dismiss();
                        if (!FinishTag1.equals("Fail")) {
                            adsListener2.onAdsClose();
                        }
                    }
                });
                customIntAds.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }

    }

    public static void ShowInterstitialAd(final Context context, String google_i, String str, onInterstitialAdsClose oninterstitialadsclose) {
        mContext = context;


        dialog = new Dialog(context, R.style.fulldialog);
        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        dialog.setCancelable(false);
        TextView txt_adloading = dialog.findViewById(R.id.txt_adloading);
        txt_adloading.setText(mysharedpreferences.getString("AdLoadingTitle", ""));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


        if (FBCreateLoadedFlag1) {
            AppManage.Strcheckad = "StrOpen";
            try {
                adsListener2 = oninterstitialadsclose;
                final InterstitialAd interstitialAd = AMInterstitial1;
                if (interstitialAd != null) {
                    FinishTag1 = str;
                    if (mysharedpreferences.getString("ShowInterAdLoadingDialog", "").equalsIgnoreCase("on")) {
                        dialog.show();

                        try {
                            new CountDownTimer(Integer.parseInt(mysharedpreferences.getString("AdDialogLoadingTime", "")), 10) {

                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    interstitialAd.show((Activity) context);
                                }
                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            dialog.dismiss();
                            interstitialAd.show((Activity) context);
                        }

                    } else {
                        interstitialAd.show((Activity) context);
                    }
                    FBCreateLoadedFlag1 = false;
                    AllInterstitialAdsPriorityGroupTwo.LoadInterstitialAd(mContext, google_i, FinishTag1);

                    return;
                }
                FBCreateLoadedFlag1 = false;
                FinishTag1 = str;
                if (AppManage.Backprssornot == 1) {
                    if (!FinishTag1.equals("Fail")) {
                        adsListener2.onAdsClose();
                    }
                    AppManage.Strcheckad = "StrClosed";
                } else {
                    // ((Activity)context).finish();
                    // OurThemeAdddd(mContext);
                }
                AppManage.Strcheckad = "StrClosed";
                AllInterstitialAdsPriorityGroupTwo.LoadInterstitialAd(mContext, google_i, FinishTag1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            adsListener2 = oninterstitialadsclose;
            FinishTag1 = str;
            ((Activity) context).finish();
            //  OurThemeAdddd(context);
            AllInterstitialAdsPriorityGroupTwo.LoadInterstitialAd(mContext, google_i, FinishTag1);
        }
    }

    public static void ChangeActivityWithAds(final Activity activity, String google_i, final Class cls, final String str) {
        if (!FBCreateRequestFlag1) {
            ShowInterstitialAd(activity, google_i, str, new onInterstitialAdsClose() {
                public void onAdsClose() {
                    activity.startActivity(new Intent(activity, cls));
                    if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                        activity.finish();
                    }
                }

                @Override
                public void onNoDataFound() {

                }
            });
            return;
        }
        activity.startActivity(new Intent(activity, cls));
        if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
            activity.finish();
        }
    }

    public static void CloseActivityWithAds(final Activity activity, String google_i, final String str) {
        if (!FBCreateRequestFlag1) {
            ShowInterstitialAd(activity, google_i, str, new onInterstitialAdsClose() {
                public void onAdsClose() {
                    if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
                        activity.finish();
                    }
                }

                @Override
                public void onNoDataFound() {

                }
            });
        } else if (str.equals("true") || str.equals("True") || str.equals("TRUE")) {
            activity.finish();
        }
    }

    public static void ShowAdsOnCreate(Context context, String google_i) {
        if (!FBCreateRequestFlag1) {
            ShowInterstitialAd(context, google_i, "false", new onInterstitialAdsClose() {
                public void onAdsClose() {
                    dialog.dismiss();
                }

                @Override
                public void onNoDataFound() {

                }
            });
        }
    }


    public static void LoadSDKInterstitialAd() {
        try {
            Log.e("sdkInter2", "Load");
            FBCreateRequestFlag1 = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
