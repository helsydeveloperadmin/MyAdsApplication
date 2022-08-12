package com.pesonal.adsdk;

import static com.pesonal.adsdk.AppManage.activity;
import static com.pesonal.adsdk.AppManage.getApp;
import static com.pesonal.adsdk.AppManage.mysharedpreferences;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pesonal.adsdk.myActivities.Common;
import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CustomIntAds extends Dialog {
    public Context mContext;
    public OnCloseListener listener_positive;
    CustomAdModel customAdModel;
    private LinearLayout LLTop;
    private ImageView imgButtom;
    private TextView txt_title;
    private TextView txtAppDesc;
    private TextView txtRatings;
    private TextView tvTimer;
    private RatingBar rating_bar;
    private ProgressBar progressBarCircle;
    private RelativeLayout rlClose;
    private CardView ImgClose;

    public CustomIntAds(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomIntAds(@NonNull Context context, CustomAdModel customAdModel) {
        super(context);
        this.mContext = context;
        this.customAdModel = customAdModel;
    }

    public CustomIntAds(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomIntAds(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomIntAds setOnCloseListener(OnCloseListener onCloseListener) {
        this.listener_positive = onCloseListener;
        return this;
    }

    @SuppressLint("WrongConstant")
    public Point screen_size_get(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        return point;
    }

    public String hmsTimeFormatter(long j) {
        return String.format("%02d", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j))));
    }

    @SuppressLint("WrongConstant")
    private void fn_countdown() {
        if (!mysharedpreferences.getString("SkipAfter", "").trim().equalsIgnoreCase("")) {
            new CountDownTimer((Integer.parseInt(mysharedpreferences.getString("SkipAfter", "")) + 1), 100) {
                public void onTick(long j) {
                    tvTimer.setText(hmsTimeFormatter(j));
                    progressBarCircle.setProgress((int) (j / 100));
                }

                @SuppressLint("WrongConstant")
                public void onFinish() {
                    progressBarCircle.setVisibility(8);
                    tvTimer.setVisibility(8);
                    rlClose.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
                    ImgClose.setVisibility(0);
                }
            }.start().start();
            return;
        }
        progressBarCircle.setVisibility(8);
        tvTimer.setVisibility(8);
        rlClose.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.transparent));
        ImgClose.setVisibility(0);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sdk_interstitial);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = screen_size_get(getContext()).x;
        attributes.height = screen_size_get(getContext()).y;
        getWindow().setAttributes(attributes);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        if (customAdModel != null) {
            fn_countdown();
            try {
                imgButtom = findViewById(R.id.imgButtom);
                txt_title = findViewById(R.id.txtAppName);
                txtAppDesc = findViewById(R.id.txtAppDesc);
                tvTimer = findViewById(R.id.tv_timer);
                progressBarCircle = findViewById(R.id.progressBarCircle);
                rlClose = findViewById(R.id.rlClose);
                ImgClose = findViewById(R.id.ImgClose);
                rating_bar = findViewById(R.id.rating_bar);
                txtRatings = findViewById(R.id.txtRatings);

                float nextInt = (float) (new Random().nextInt(2) + 4);
                rating_bar.setRating(nextInt);
                txtRatings.setText("(" + nextInt + ")");

                Glide
                        .with(mContext)
                        .load(customAdModel.getApp_logo())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) findViewById(R.id.imgLogo));


                if (!customAdModel.getApp_banner().equals("")) {
                    Picasso.get().load(customAdModel.getApp_banner()).into(imgButtom);
                }
                txt_title.setText(customAdModel.getApp_name());
                txtAppDesc.setText(customAdModel.getApp_shortDecription());

                findViewById(R.id.imgButtom).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {

                            getApp(mContext, action_str);
                        } else {
                            Common.PlayStoreRedirectDialog(activity, action_str);
                        }
                        OnCloseListener onCloseListener = listener_positive;
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }

                    }
                });
                findViewById(R.id.imgInstall1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {

                            getApp(mContext, action_str);
                        } else {
                            Common.PlayStoreRedirectDialog(activity, action_str);
                        }
                        OnCloseListener onCloseListener = listener_positive;
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }

                    }
                });

                findViewById(R.id.ImgClose).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OnCloseListener onCloseListener = listener_positive;
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                            if (mysharedpreferences.getString("SDKExpressMode", "").equalsIgnoreCase("on")) {
                                String action_str = customAdModel.getApp_packageName();
                                if (action_str.contains("http")) {

                                    getApp(mContext, action_str);
                                } else {
                                    Common.PlayStoreRedirectDialog(activity, action_str);
                                }
                            }
                        }
                    }
                });


                AppManage.count_custIntAd++;
            } catch (Exception e) {
                OnCloseListener onCloseListener = listener_positive;
                if (onCloseListener != null) {
                    onCloseListener.onAdsCloseClick();
                }
            }
        } else {
            OnCloseListener onCloseListener = listener_positive;
            if (onCloseListener != null) {
                onCloseListener.onAdsCloseClick();
            }
        }

        setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                OnCloseListener onCloseListener = listener_positive;
                if (onCloseListener != null) {
                    onCloseListener.onAdsCloseClick();
                }
                if (mysharedpreferences.getString("SDKExpressMode", "").equalsIgnoreCase("on")) {
                    String action_str = customAdModel.getApp_packageName();
                    if (action_str.contains("http")) {

                        getApp(mContext, action_str);
                    } else {
                        Common.PlayStoreRedirectDialog(activity, action_str);
                    }
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mysharedpreferences.getString("SDKExpressMode", "").equalsIgnoreCase("on")) {
            String action_str = customAdModel.getApp_packageName();
            if (action_str.contains("http")) {

                getApp(mContext, action_str);
            } else {
                Common.PlayStoreRedirectDialog(activity, action_str);
            }
        }
    }

    public interface OnCloseListener {
        void onAdsCloseClick();

        void setOnKeyListener();
    }
}
