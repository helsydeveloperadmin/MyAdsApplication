package com.pesonal.adsdk;

import static com.pesonal.adsdk.AppManage.mysharedpreferences;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class OIActivity extends ADS_SplashActivity {

    String OIActivity_ON_OFF_InterAds;
    String OIActivity_ON_OFF_BannerAds;
    String OIActivity_AM_FB_InterAds;
    String OIActivity_AM_FB_BannerAds;
    String OIActivity_AM_FB_Backpress;
    String OIActivity_AM_Inter;
    String OIActivity_AM_Banner;
    String OIActivity_FB_Inter;
    String OIActivity_FB_Banner;
    private WebView mWebV;
    private ProgressDialog pd;
    private InterstitialAd mInterstitialAd;
    private com.facebook.ads.InterstitialAd interstitialAd;

    @Override
    protected void onResume() {
        super.onResume();
    }

    public int getCurrentVersionCode() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    getPackageName(), 0);
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        AudienceNetworkAds.initialize(this);

        Intent i = getIntent();
        String urlToBeOpened = i.getStringExtra("urlToBeOpened");

        setContentView(R.layout.activity_oi);

        ADSinit_OI(OIActivity.this, getCurrentVersionCode(), new getDataListner() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onUpdate(String url) {

            }

            @Override
            public void onRedirect(String url) {

            }

            @Override
            public void onReload() {

            }

            @Override
            public void onGetExtradata(JSONObject extraData) {
                Log.e("my_log1", "ongetExtradata: " + extraData.toString());

                try {

                    OIActivity_ON_OFF_InterAds = extraData.getString("OIActivity_ON_OFF_InterAds");
                    OIActivity_ON_OFF_BannerAds = extraData.getString("OIActivity_ON_OFF_BannerAds");

                    OIActivity_AM_FB_InterAds = extraData.getString("OIActivity_AM_FB_InterAds");
                    OIActivity_AM_FB_BannerAds = extraData.getString("OIActivity_AM_FB_BannerAds");

                    OIActivity_AM_FB_Backpress = extraData.getString("OIActivity_AM_FB_Backpress");

                    OIActivity_AM_Inter = extraData.getString("OIActivity_AM_Inter");
                    OIActivity_AM_Banner = extraData.getString("OIActivity_AM_Banner");

                    OIActivity_FB_Inter = extraData.getString("OIActivity_FB_Inter");
                    OIActivity_FB_Banner = extraData.getString("OIActivity_FB_Banner");

                    if (!mysharedpreferences.getBoolean("InAppPurchased", false)) {
                        //load all condition and ads hare
                        if (OIActivity_ON_OFF_InterAds.equals("on")) {
                            if (OIActivity_AM_FB_InterAds.equals("AM"))
                                showAMInter();
                            else
                                showFBInter();

                        }

                        if (OIActivity_ON_OFF_BannerAds.equals("on")) {
                            if (OIActivity_AM_FB_BannerAds.equals("AM"))
                                showAMBanner();
                            else
                                showFBBanner();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        mWebV = findViewById(R.id.webv);

        pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.show();

        mWebV.getSettings().setJavaScriptEnabled(true);
        mWebV.getSettings().setLoadWithOverviewMode(true);
        mWebV.getSettings().setUseWideViewPort(true);
        mWebV.setWebViewClient(new MyWebViewClient());
        mWebV.loadUrl(urlToBeOpened);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (OIActivity_AM_FB_Backpress.equals("AM"))
            showAMInter();

        if (OIActivity_AM_FB_Backpress.equals("FB"))
            showFBInter();
    }

    private void showFBBanner() {
        //  IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID
        final com.facebook.ads.AdView adView = new com.facebook.ads.AdView(OIActivity.this, OIActivity_FB_Banner, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                ((ViewGroup) findViewById(R.id.banner_container)).removeAllViews();
                // nextBannerPlatform(banner_container);

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                ((ViewGroup) findViewById(R.id.banner_container)).removeAllViews();
                ((ViewGroup) findViewById(R.id.banner_container)).addView(adView);
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
        Display display = OIActivity.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(OIActivity.this, adWidth);
    }

    private void showAMBanner() {
        final AdView mAdView = new AdView(OIActivity.this);
        AdSize adSize = getAdSize();
        mAdView.setAdSize(adSize);
        mAdView.setAdUnitId(OIActivity_AM_Banner);
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
                ((ViewGroup) findViewById(R.id.banner_container)).removeAllViews();
                //  nextBannerPlatform(banner_container);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                ((ViewGroup) findViewById(R.id.banner_container)).removeAllViews();
                ((ViewGroup) findViewById(R.id.banner_container)).addView(mAdView);
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

    private void showFBInter() {
        interstitialAd = new com.facebook.ads.InterstitialAd(this, OIActivity_FB_Inter);
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                //Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                //  Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                //  Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                //   Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                //Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                // Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }

    private void showAMInter() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, OIActivity_AM_Inter, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.show(OIActivity.this);
                        //  Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        // Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // System.out.println("on finish");
            if (pd.isShowing()) {
                pd.dismiss();
            }

        }
    }
}