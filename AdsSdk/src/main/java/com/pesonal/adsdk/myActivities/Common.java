package com.pesonal.adsdk.myActivities;

import static com.pesonal.adsdk.ADS_SplashActivity.qureka_count;
import static com.pesonal.adsdk.ADS_SplashActivity.qureka_flag;
import static com.pesonal.adsdk.AppManage.app_adShowStatus;
import static com.pesonal.adsdk.AppManage.getApp;
import static com.pesonal.adsdk.AppManage.mysharedpreferences;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pesonal.adsdk.R;
import com.pesonal.adsdk.myActivities.circularimageview.PushDownAnim;
import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Common {
    public static boolean isNetworkConnected(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @SuppressLint("WrongConstant")
    public static void rateApp(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName()));
        try {
            intent.setFlags(67108864);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())).setFlags(67108864));
        }
    }

    @SuppressLint("WrongConstant")
    public static void shareApp(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.app_name));
            intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\nhttps://play.google.com/store/apps/details?id=" + context.getPackageName() + "\n\n");
            intent.setFlags(67108864);
            context.startActivity(Intent.createChooser(intent, "choose one"));
        } catch (Exception unused) {
        }
    }

    public static void privacyPolicy(Context context) {
        try {
            Intent intent1 = new Intent("android.intent.action.VIEW");
            intent1.setData(Uri.parse(mysharedpreferences.getString("app_privacyPolicyLink", "")));
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        } catch (Exception e) {

        }
    }

    public static void setFadeAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        view.startAnimation(alphaAnimation);
    }

    public static boolean hasPermissions(Context context, String... strArr) {
        if (Build.VERSION.SDK_INT < 23 || context == null || strArr == null) {
            return true;
        }
        for (String checkSelfPermission : strArr) {
            if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static String hmsTimeFormatter(long j) {
        return String.format("%02d", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j))));
    }


    public static void showGiftDialog(final Context context) {

        final Dialog dialog = new Dialog(context, R.style.Adfulldialog);

        dialog.setContentView(R.layout.festsk_sdk_native_dialog_ad_view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        final TextView tvTimer = dialog.findViewById(R.id.tv_timer);
        final ProgressBar progressBarCircle = dialog.findViewById(R.id.progressBarCircle);
        final RelativeLayout rlClose = dialog.findViewById(R.id.rlClose);
        final CardView ImgClose = dialog.findViewById(R.id.ImgClose);
        ImageView nativeAdsInterstitalLayout = dialog.findViewById(R.id.NativeAdsInterstitalLayout);


        if (!mysharedpreferences.getString("SkipAfter", "").trim().equalsIgnoreCase("")) {
            new CountDownTimer((Integer.parseInt(mysharedpreferences.getString("SkipAfter", "")) + 1), 100) {
                public void onTick(long j) {
                    tvTimer.setText(hmsTimeFormatter(j));
                    progressBarCircle.setProgress((int) (j / 100));
                }

                @SuppressLint("WrongConstant")
                public void onFinish() {
                    // SdkCommonPlace.closeFlag = true;
                    progressBarCircle.setVisibility(8);
                    tvTimer.setVisibility(8);
                    rlClose.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                    ImgClose.setVisibility(0);
                }
            }.start().start();
        }
        if (!mysharedpreferences.getString("Dialog_Img_Link", "").equals("")) {
            Picasso.get().load(mysharedpreferences.getString("Dialog_Img_Link", "")).into(nativeAdsInterstitalLayout);
        }
        nativeAdsInterstitalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                try {
                    if (mysharedpreferences.getString("Dialog_Redirect_URL", "").contains("http")) {
                        /*    Uri marketUri = Uri.parse(action_str);
                            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                            mContext.startActivity(marketIntent);*/
                        //TODO Hiral Native Banner Custom Click
                        getApp(context, mysharedpreferences.getString("Dialog_Redirect_URL", ""));
                    } else {
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + mysharedpreferences.getString("Dialog_Redirect_URL", ""))));
                    }
                    //  context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(mysharedpreferences.getString("Dialog_Redirect_URL", ""))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        ImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static boolean checkGifViewVisivbleOrNot(Activity activity) {
        return (Common.isNetworkConnected(activity)) && app_adShowStatus != 0 &&
                !mysharedpreferences.getString("Dialog_Img_Link", "").equalsIgnoreCase("");

    }

    public static void PlayStoreRedirectDialog(final Context activity, final String action_str) {
        if (mysharedpreferences.getString("PlayStoreRedirectDialog", "").equalsIgnoreCase("on")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Do you want to Redirect to  Play Store?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + action_str)));
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog create = builder.create();
            create.show();
        } else {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + action_str)));
        }
    }

    @SuppressLint("WrongConstant")
    public static void zoomGlowEffect(View view) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat("scaleX", 0.9f), PropertyValuesHolder.ofFloat("scaleY", 0.9f));
        ofPropertyValuesHolder.setDuration(200);
        ofPropertyValuesHolder.setRepeatCount(-1);
        ofPropertyValuesHolder.setRepeatMode(2);
        ofPropertyValuesHolder.start();
    }

    public static PushDownAnim Animation(ViewGroup viewGroup) {
        PushDownAnim pushDownAnim = new PushDownAnim(viewGroup);
        PushDownAnim.setPushDownAnimTo(viewGroup);
        pushDownAnim.setScale(1, 8.0f);
        pushDownAnim.setDurationPush(50);
        pushDownAnim.setDurationRelease(125);
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = PushDownAnim.DEFAULT_INTERPOLATOR;
        pushDownAnim.setInterpolatorPush(accelerateDecelerateInterpolator);
        pushDownAnim.setInterpolatorRelease(accelerateDecelerateInterpolator);
        return pushDownAnim;
    }

    public static PushDownAnim Animation(View view) {
        PushDownAnim pushDownAnim = new PushDownAnim(view);
        PushDownAnim.setPushDownAnimTo(view);
        pushDownAnim.setScale(1, 8.0f);
        pushDownAnim.setDurationPush(50);
        pushDownAnim.setDurationRelease(125);
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = PushDownAnim.DEFAULT_INTERPOLATOR;
        pushDownAnim.setInterpolatorPush(accelerateDecelerateInterpolator);
        pushDownAnim.setInterpolatorRelease(accelerateDecelerateInterpolator);
        return pushDownAnim;
    }


    public static void openCustomTab(Activity activity, CustomTabsIntent customTabsIntent, Uri uri) {
        String packageName = "com.android.chrome";
        if (packageName != null) {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(activity, uri);
        } else {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }

    public static void fun_qureka(Activity activity) {
        qureka_count++;
        if (mysharedpreferences == null)
            mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);

        if (mysharedpreferences != null) {
            if (qureka_count == Integer.parseInt(mysharedpreferences.getString("Qureka_count", "0"))) {
                qureka_flag = false;
            }
        }
        try {
            CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
            customIntent.setToolbarColor(ContextCompat.getColor(activity, R.color.colorAccent));
            openCustomTab(activity, customIntent.build(), Uri.parse(get_qureka_URL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get_qureka_name() {

        if (mysharedpreferences != null) {
            String[] array = mysharedpreferences.getString("Qureka_Names", "").split(",");
            // String[] array = adModelArrayList.get(0).Qureka_Names.split(",");
            String randomStr = array[new Random().nextInt(array.length)];
            return randomStr;
        }
        return "";
    }

    public static String get_qureka_URL() {
        if (mysharedpreferences != null) {
            String[] array = mysharedpreferences.getString("Qureka_Urls", "").split(",");
            // String[] array = adModelArrayList.get(0).Qureka_Urls.split(",");
            String randomStr = array[new Random().nextInt(array.length)];
            return randomStr;
        }
        return "";
    }

    public static String get_qureka_GIF() {
        if (mysharedpreferences != null) {
            String[] array = mysharedpreferences.getString("Qureka_GIFs", "").split(",");
//            String[] array = adModelArrayList.get(0).Qureka_GIFs.split(",");
            String randomStr = array[new Random().nextInt(array.length)];
            return randomStr;
        }
        return "";
    }

}
