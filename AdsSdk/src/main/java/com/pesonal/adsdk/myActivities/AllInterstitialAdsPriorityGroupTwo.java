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

public class AllInterstitialAdsPriorityGroupTwo {

    public static InterstitialAd AMInterstitial = null;
    public static boolean FBCreateLoadedFlag = false;
    public static boolean FBCreateRequestFlag = false;

    public static String FinishTag = "";

    public static onInterstitialAdsClose adsListener1;
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
                if (!FBCreateLoadedFlag) {
                    FinishTag = str;
                    FBCreateLoadedFlag = true;
                    FBCreateRequestFlag = true;
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
                    InterstitialAd unused = AllInterstitialAdsPriorityGroupTwo.AMInterstitial = interstitialAd;
                    AllInterstitialAdsPriorityGroupTwo.FBCreateRequestFlag = false;
                    Log.e("AdsPriorityGroupTwo", "onAdLoaded.");
                    AllInterstitialAdsPriorityGroupTwo.AMCallBack();
                }

                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Log.e("AdsPriorityGroupTwo", "Error." + loadAdError.getCode() + "-" + loadAdError);
                    AllInterstitialAdsPriorityGroupTwo.LoadSDKInterstitialAd();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void AMCallBack() {
        AMInterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
            public void onAdDismissedFullScreenContent() {

                Log.e("AdsPriorityGroupTwo", "The ad was dismissed.");

                if (!AllInterstitialAdsPriorityGroupTwo.FinishTag.equals("Fail")) {
                    AllInterstitialAdsPriorityGroupTwo.adsListener1.onAdsClose();
                }
                AppManage.Strcheckad = "StrClosed";


            }

            public void onAdFailedToShowFullScreenContent(AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);

                Log.e("AdsPriorityGroupTwo", "onAdFailedToShowFullScreenContent.");

                if (!AllInterstitialAdsPriorityGroupTwo.FinishTag.equals("Fail")) {
                    AllInterstitialAdsPriorityGroupTwo.adsListener1.onAdsClose();
                }
                AppManage.Strcheckad = "StrClosed";
            }

            public void onAdShowedFullScreenContent() {

                Log.e("AdsPriorityGroupTwo", "The ad was shown.");

                if (AppManage.Backprssornot == 0) {
                    AppManage.FrontshowadsCounter++;
                }
            }
        });
    }


    private static void OurThemeAddddd(Context context) {
        CustomAdModel customAdModel = AppManage.getInstance(context).getMyCustomAd("Interstitial");
        if (customAdModel != null) {
            try {
                final CustomIntAds customIntAds = new CustomIntAds(context, customAdModel);
                customIntAds.setCanceledOnTouchOutside(false);
                customIntAds.setCancelable(false);
                customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                    public void onAdsCloseClick() {
                        customIntAds.dismiss();
                        if (!FinishTag.equals("Fail")) {
                            adsListener1.onAdsClose();
                        }
                    }

                    public void setOnKeyListener() {
                        customIntAds.dismiss();
                        if (!FinishTag.equals("Fail")) {
                            adsListener1.onAdsClose();
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


        if (FBCreateLoadedFlag) {
            AppManage.Strcheckad = "StrOpen";
            try {
                adsListener1 = oninterstitialadsclose;
                final InterstitialAd interstitialAd = AMInterstitial;
                if (interstitialAd != null) {
                    FinishTag = str;

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


                    FBCreateLoadedFlag = false;
                    AllInterstitialAdsPriority1GroupTwo.LoadInterstitialAd(mContext, google_i, FinishTag);
                    return;
                }
                FBCreateLoadedFlag = false;
                FinishTag = str;
                if (AppManage.Backprssornot == 1) {
                    if (!FinishTag.equals("Fail")) {
                        adsListener1.onAdsClose();
                    }
                    AppManage.Strcheckad = "StrClosed";
                } else {
                    //  ((Activity)context).finish();
                    //  OurThemeAddddd(mContext);
                }
                AllInterstitialAdsPriority1GroupTwo.LoadInterstitialAd(mContext, google_i, FinishTag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            adsListener1 = oninterstitialadsclose;
            FinishTag = str;
            //  OurThemeAddddd(context);
            ((Activity) context).finish();
            AllInterstitialAdsPriority1GroupTwo.LoadInterstitialAd(mContext, google_i, FinishTag);
        }
    }

    public static void ChangeActivityWithAds(final Activity activity, String google_i, final Class cls, final String str) {
        if (!FBCreateRequestFlag) {
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
        if (!FBCreateRequestFlag) {
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
        if (!FBCreateRequestFlag) {
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
            Log.e("sdkInter1", "Load");
            FBCreateRequestFlag = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
