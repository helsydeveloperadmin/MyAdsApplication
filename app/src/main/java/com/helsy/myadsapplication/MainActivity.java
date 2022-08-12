package com.helsy.myadsapplication;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_B;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pesonal.adsdk.ADS_SplashActivity;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.getDataListner;
import com.pesonal.adsdk.myActivities.AdModel;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ADS_SplashActivity {

    private LinearLayout linearBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearBanner = findViewById(R.id.linearBanner);
        getSDKData();
    }

    private void getSDKData() {
        ADSinit(MainActivity.this, false, 1, new getDataListner() {
            @Override
            public void onSuccess() {
                AppManage.Strcheckad = "StrClosed";

                if (AppManage.mysharedpreferences.getString("BannerAdLoadPlace", "").equalsIgnoreCase("onCreate")) {
                    AppManage.getInstance(MainActivity.this).showBanner((ViewGroup) linearBanner, ADMOB_B[0], FACEBOOK_B[0]);
                }
            }

            @Override
            public void onUpdate(String url) {
                Log.e("my_log", "onUpdate: " + url);
            }

            @Override
            public void onRedirect(String url) {
                Log.e("my_log", "onRedirect: " + url);
            }

            @Override
            public void onReload() {

            }

            @Override
            public void onGetExtradata(JSONObject extraData) {
                Log.e("LLL_Extra_Response : ", "" + extraData);
                try {
                    String MainActivity_ON_OFF_BannerAds = extraData.getString("MainActivity_ON_OFF_BannerAds");
                    AdModel m = new AdModel();
                    m.setMainActivity_ON_OFF_BannerAds(MainActivity_ON_OFF_BannerAds);

                    adModelArrayList.add(m);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}