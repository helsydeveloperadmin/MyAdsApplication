package com.pesonal.adsdk.myActivities;

import static com.pesonal.adsdk.AppManage.mysharedpreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.pesonal.adsdk.R;

public class TemplateView extends FrameLayout {
    private static final String MEDIUM_TEMPLATE = "medium_template";
    private static final String SMALL_TEMPLATE = "small_template";
    private LinearLayout background;
    private Button callToActionView;
    private ImageView iconView;
    private MediaView mediaView;
    private NativeAd nativeAd;
    private NativeAdView nativeAdView;
    private TextView primaryView;
    private RatingBar ratingBar;
    private TextView secondaryView;
    private NativeTemplateStyle styles;
    private int templateMinHeight = 120;
    private int templateType = R.layout.ads_gnt_medium_template_view;
    private TextView tertiaryView;

    public TemplateView(Context context) {
        super(context);
    }

    public TemplateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context, attributeSet);
    }

    public TemplateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context, attributeSet);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TemplateView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context, attributeSet);
    }

    public void setStyles(NativeTemplateStyle nativeTemplateStyle) {
        this.styles = nativeTemplateStyle;
        applyStyles();
    }

    public NativeAdView getNativeAdView() {
        return this.nativeAdView;
    }

    private void applyStyles() {
        TextView textView;
        TextView textView2;
        TextView textView3;
        Button button;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        Button button2;
        Button button3;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        Button button4;
        TextView textView10;
        TextView textView11;
        TextView textView12;
        ColorDrawable mainBackgroundColor = this.styles.getMainBackgroundColor();
        if (mainBackgroundColor != null) {
            this.background.setBackground(mainBackgroundColor);
            TextView textView13 = this.primaryView;
            if (textView13 != null) {
                textView13.setBackground(mainBackgroundColor);
            }
            TextView textView14 = this.secondaryView;
            if (textView14 != null) {
                textView14.setBackground(mainBackgroundColor);
            }
            TextView textView15 = this.tertiaryView;
            if (textView15 != null) {
                textView15.setBackground(mainBackgroundColor);
            }
        }
        Typeface primaryTextTypeface = this.styles.getPrimaryTextTypeface();
        if (!(primaryTextTypeface == null || (textView12 = this.primaryView) == null)) {
            textView12.setTypeface(primaryTextTypeface);
        }
        Typeface secondaryTextTypeface = this.styles.getSecondaryTextTypeface();
        if (!(secondaryTextTypeface == null || (textView11 = this.secondaryView) == null)) {
            textView11.setTypeface(secondaryTextTypeface);
        }
        Typeface tertiaryTextTypeface = this.styles.getTertiaryTextTypeface();
        if (!(tertiaryTextTypeface == null || (textView10 = this.tertiaryView) == null)) {
            textView10.setTypeface(tertiaryTextTypeface);
        }
        Typeface callToActionTextTypeface = this.styles.getCallToActionTextTypeface();
        if (!(callToActionTextTypeface == null || (button4 = this.callToActionView) == null)) {
            button4.setTypeface(callToActionTextTypeface);
        }
        int primaryTextTypefaceColor = this.styles.getPrimaryTextTypefaceColor();
        if (primaryTextTypefaceColor > 0 && (textView9 = this.primaryView) != null) {
            textView9.setTextColor(primaryTextTypefaceColor);
        }
        int secondaryTextTypefaceColor = this.styles.getSecondaryTextTypefaceColor();
        if (secondaryTextTypefaceColor > 0 && (textView8 = this.secondaryView) != null) {
            textView8.setTextColor(secondaryTextTypefaceColor);
        }
        int tertiaryTextTypefaceColor = this.styles.getTertiaryTextTypefaceColor();
        if (tertiaryTextTypefaceColor > 0 && (textView7 = this.tertiaryView) != null) {
            textView7.setTextColor(tertiaryTextTypefaceColor);
        }
        int callToActionTypefaceColor = this.styles.getCallToActionTypefaceColor();
        if (callToActionTypefaceColor > 0 && (button3 = this.callToActionView) != null) {
            button3.setTextColor(callToActionTypefaceColor);
        }
        float callToActionTextSize = this.styles.getCallToActionTextSize();
        if (callToActionTextSize > 0.0f && (button2 = this.callToActionView) != null) {
            button2.setTextSize(callToActionTextSize);
        }
        float primaryTextSize = this.styles.getPrimaryTextSize();
        if (primaryTextSize > 0.0f && (textView6 = this.primaryView) != null) {
            textView6.setTextSize(primaryTextSize);
        }
        float secondaryTextSize = this.styles.getSecondaryTextSize();
        if (secondaryTextSize > 0.0f && (textView5 = this.secondaryView) != null) {
            textView5.setTextSize(secondaryTextSize);
        }
        float tertiaryTextSize = this.styles.getTertiaryTextSize();
        if (tertiaryTextSize > 0.0f && (textView4 = this.tertiaryView) != null) {
            textView4.setTextSize(tertiaryTextSize);
        }
        ColorDrawable callToActionBackgroundColor = this.styles.getCallToActionBackgroundColor();
        if (!(callToActionBackgroundColor == null || (button = this.callToActionView) == null)) {
            button.setBackground(callToActionBackgroundColor);
        }
        ColorDrawable primaryTextBackgroundColor = this.styles.getPrimaryTextBackgroundColor();
        if (!(primaryTextBackgroundColor == null || (textView3 = this.primaryView) == null)) {
            textView3.setBackground(primaryTextBackgroundColor);
        }
        ColorDrawable secondaryTextBackgroundColor = this.styles.getSecondaryTextBackgroundColor();
        if (!(secondaryTextBackgroundColor == null || (textView2 = this.secondaryView) == null)) {
            textView2.setBackground(secondaryTextBackgroundColor);
        }
        ColorDrawable tertiaryTextBackgroundColor = this.styles.getTertiaryTextBackgroundColor();
        if (!(tertiaryTextBackgroundColor == null || (textView = this.tertiaryView) == null)) {
            textView.setBackground(tertiaryTextBackgroundColor);
        }
        invalidate();
        requestLayout();
    }

    private boolean adHasOnlyStore(NativeAd nativeAd2) {
        return !TextUtils.isEmpty(nativeAd2.getStore()) && TextUtils.isEmpty(nativeAd2.getAdvertiser());
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "ResourceType"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setNativeAd(NativeAd nativeAd2) {
        this.nativeAd = nativeAd2;
        String store = nativeAd2.getStore();
        String advertiser = nativeAd2.getAdvertiser();
        String headline = nativeAd2.getHeadline();
        String body = nativeAd2.getBody();
        String callToAction = nativeAd2.getCallToAction();
        Double starRating = nativeAd2.getStarRating();
        NativeAd.Image icon = nativeAd2.getIcon();
        this.nativeAdView.setCallToActionView(this.callToActionView);
        this.nativeAdView.setHeadlineView(this.primaryView);
        this.nativeAdView.setMediaView(this.mediaView);
        this.secondaryView.setVisibility(0);
        if (adHasOnlyStore(nativeAd2)) {
            this.nativeAdView.setStoreView(this.secondaryView);
        } else if (!TextUtils.isEmpty(advertiser)) {
            this.nativeAdView.setAdvertiserView(this.secondaryView);
            store = advertiser;
        } else {
            store = "";
        }
        this.primaryView.setText(headline);
        this.callToActionView.setText(callToAction);

        //TODO Hiral
        try {
            callToActionView.getBackground().setColorFilter(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_Color", "")), PorterDuff.Mode.SRC_ATOP);
            callToActionView.setTextColor(Color.parseColor("#" + mysharedpreferences.getString("CTA_Buttton_TextColor", "")));
        } catch (Exception e) {
            callToActionView.getBackground().setColorFilter(R.color.colorPrimaryDark, PorterDuff.Mode.SRC_ATOP);
            e.printStackTrace();
        }

        if (starRating == null || starRating.doubleValue() <= 0.0d) {
            this.secondaryView.setText(store);
            this.secondaryView.setVisibility(0);
            this.ratingBar.setVisibility(8);
        } else {
            this.secondaryView.setVisibility(8);
            this.ratingBar.setVisibility(8);
            this.ratingBar.setMax(5);
            this.nativeAdView.setStarRatingView(this.ratingBar);
        }
        if (icon != null) {
            this.iconView.setVisibility(0);
            this.iconView.setImageDrawable(icon.getDrawable());
        } else {
            this.iconView.setVisibility(4);
        }
        TextView textView = this.tertiaryView;
        if (textView != null) {
            textView.setText(body);
            this.nativeAdView.setBodyView(this.tertiaryView);
        }
        this.nativeAdView.setNativeAd(nativeAd2);
    }


    public void destroyNativeAd() {
        this.nativeAd.destroy();
    }

    public String getTemplateTypeName() {
        if (this.templateType == R.layout.ads_gnt_medium_template_view) {
            return MEDIUM_TEMPLATE;
        }
        return this.templateType == R.layout.ads_gnt_small_template_view ? SMALL_TEMPLATE : "";
    }

    public int setTemplateTypeName(int i) {
        if (i == 0) {
            this.templateType = R.layout.ads_gnt_medium_template_view;
        } else if (i == 1) {
            this.templateType = R.layout.ads_gnt_small_template_view;
        }
        return this.templateType;
    }

    /* JADX INFO: finally extract failed */
    @SuppressLint("WrongConstant")
    private void initView(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TemplateView, 0, 0);
        try {
            this.templateType = obtainStyledAttributes.getResourceId(R.styleable.TemplateView_gnt_template_type, this.templateType);
            this.templateMinHeight = obtainStyledAttributes.getResourceId(R.styleable.TemplateView_gnt_template_min_height, this.templateMinHeight);
            obtainStyledAttributes.recycle();
            ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(this.templateType, this);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.nativeAdView = findViewById(R.id.native_ad_view);
        this.primaryView = findViewById(R.id.primary);
        this.secondaryView = findViewById(R.id.secondary);
        this.tertiaryView = findViewById(R.id.body);
        RatingBar ratingBar2 = findViewById(R.id.rating_bar);
        this.ratingBar = ratingBar2;
        ratingBar2.setEnabled(false);
        this.callToActionView = findViewById(R.id.cta);
        this.iconView = findViewById(R.id.icon);
        this.mediaView = findViewById(R.id.media_view);
        this.background = findViewById(R.id.background);
    }
}
