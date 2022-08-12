package com.pesonal.adsdk;

import static com.pesonal.adsdk.AppManage.activity;
import static com.pesonal.adsdk.AppManage.getApp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pesonal.adsdk.myActivities.Common;


public class CustomAppOpenAds extends Dialog {
    public Context mContext;
    public OnCloseListener listener_positive;
    public SharedPreferences mysharedpreferences;
    CustomAdModel customAdModel;
    private LinearLayout ll_continue_app;
    private ImageView iv_myapp_logo;
    private ImageView imgButtom;
    private FrameLayout FlImage;
    private TextView txt_myapp_name;

    public CustomAppOpenAds(@NonNull Context context) {
        super(context);
        this.mContext = context;

    }

    public CustomAppOpenAds(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;

    }

    public CustomAppOpenAds(@NonNull Context context, int themeResId, CustomAdModel customAdModel) {
        super(context, themeResId);
        this.mContext = context;
        this.customAdModel = customAdModel;

    }

    protected CustomAppOpenAds(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomAppOpenAds setOnCloseListener(OnCloseListener onCloseListener) {
        this.listener_positive = onCloseListener;
        return this;
    }

    @SuppressLint("WrongConstant")
    public Point screen_size_get(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        return point;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cust_appopen);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = screen_size_get(getContext()).x;
        attributes.height = screen_size_get(getContext()).y;
        getWindow().setAttributes(attributes);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        if (customAdModel != null) {
            try {
                ll_continue_app = findViewById(R.id.ll_continue_app);
                iv_myapp_logo = findViewById(R.id.iv_myapp_logo);
                txt_myapp_name = findViewById(R.id.txt_myapp_name);
                imgButtom = findViewById(R.id.imgButtom);


                FlImage = findViewById(R.id.FlImage);


                mysharedpreferences = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);

                txt_myapp_name.setText(mysharedpreferences.getString("app_name", ""));
                if (!mysharedpreferences.getString("app_logo", "").isEmpty()) {
                    Glide
                            .with(mContext)
                            .load(mysharedpreferences.getString("app_logo", ""))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((ImageView) findViewById(R.id.iv_myapp_logo));
                }

                Glide
                        .with(mContext)
                        .load(customAdModel.getApp_banner())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgButtom);


                FlImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String action_str = customAdModel.getApp_packageName();
                        if (action_str.contains("http")) {
                            //TODO Hiral Native Banner Custom Click
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


                ll_continue_app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnCloseListener onCloseListener = listener_positive;
                        if (onCloseListener != null) {
                            onCloseListener.onAdsCloseClick();
                        }
                        if (mysharedpreferences.getString("SDKExpressMode", "").equalsIgnoreCase("on")) {
                            String action_str = customAdModel.getApp_packageName();
                            if (action_str.contains("http")) {

                                //TODO Hiral Native Banner Custom Click
                                getApp(mContext, action_str);
                            } else {
                                Common.PlayStoreRedirectDialog(activity, action_str);
                                // mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + action_str)));
                            }
                        }
                    }
                });


                AppManage.count_custAppOpenAd++;
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

                        //TODO Hiral Native Banner Custom Click
                        getApp(mContext, action_str);
                    } else {
                        Common.PlayStoreRedirectDialog(activity, action_str);
                        // mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + action_str)));
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

                //TODO Hiral Native Banner Custom Click
                getApp(mContext, action_str);
            } else {
                Common.PlayStoreRedirectDialog(activity, action_str);
                // mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + action_str)));
            }
        }
    }

    public interface OnCloseListener {
        void onAdsCloseClick();

        void setOnKeyListener();
    }
}
