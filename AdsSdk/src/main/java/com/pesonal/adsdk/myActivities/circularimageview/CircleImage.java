package com.pesonal.adsdk.myActivities.circularimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;

import com.pesonal.adsdk.R;

import java.io.IOException;

public class CircleImage extends ImageView {
    private static final ScaleType SCALE_TYPE = ScaleType.FIT_XY;
    private final float DEFAULT_SHADOW_RADIUS;
    private final Matrix matrix;
    private final Paint paint;
    private final Paint paintBorder;
    private final Paint shadowPaint;
    /* access modifiers changed from: private */
    public Bitmap image;
    private boolean addShadow;
    private int bitmapHeight;
    private BitmapShader bitmapShader;
    private int bitmapWidth;
    private int borderRadius;
    private int borderWidth;
    private int color;
    private Context context;
    private int drawRadius;
    private int shadowColor;
    private float shadowRadius;

    public CircleImage(Context context2) {
        super(context2);
        this.shadowPaint = new Paint();
        this.paint = new Paint();
        this.paintBorder = new Paint();
        this.shadowColor = ViewCompat.MEASURED_STATE_MASK;
        this.matrix = new Matrix();
        this.DEFAULT_SHADOW_RADIUS = 10.0f;
        this.context = context2;
    }

    public CircleImage(Context context2, AttributeSet attributeSet) {
        this(context2, attributeSet, 0);
        this.context = context2;
    }

    public CircleImage(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.shadowPaint = new Paint();
        this.paint = new Paint();
        this.paintBorder = new Paint();
        this.shadowColor = ViewCompat.MEASURED_STATE_MASK;
        this.matrix = new Matrix();
        this.DEFAULT_SHADOW_RADIUS = 10.0f;
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.CircleImage, i, 0);
        this.borderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleImage_civ_border_width, 0);
        this.color = obtainStyledAttributes.getColor(R.styleable.CircleImage_civ_border_color, -1);
        this.addShadow = obtainStyledAttributes.getBoolean(R.styleable.CircleImage_civ_add_shadow, false);
        this.shadowColor = obtainStyledAttributes.getColor(R.styleable.CircleImage_civ_shadow_color, ViewCompat.MEASURED_STATE_MASK);
        this.shadowRadius = obtainStyledAttributes.getFloat(R.styleable.CircleImage_civ_shadow_radius, 10.0f);
        obtainStyledAttributes.recycle();
        setup();
    }


    public void setImageResource(int i) {
        super.setImageResource(i);
        getDrawableToBitmap(getDrawable());
        setup();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        getDrawableToBitmap(drawable);
        setup();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.image = bitmap;
        setup();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.image = getImageFromUri(uri);
        setup();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) this.borderRadius, this.paintBorder);
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) this.drawRadius, this.paint);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setup();
    }

    /* access modifiers changed from: private */
    public void setup() {
        if (this.image != null && getWidth() != 0 && getHeight() != 0) {
            this.paint.setAntiAlias(true);
            this.paintBorder.setAntiAlias(true);
            this.paintBorder.setColor(this.color);
            this.paintBorder.setStyle(Paint.Style.STROKE);
            this.paintBorder.setStrokeWidth((float) this.borderWidth);
            this.shadowPaint.setStyle(Paint.Style.STROKE);
            this.shadowPaint.setStrokeWidth((float) this.borderWidth);
            this.shadowPaint.setColor(-3355444);
            this.bitmapWidth = this.image.getWidth();
            this.bitmapHeight = this.image.getHeight();
            BitmapShader bitmapShader2 = new BitmapShader(this.image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.bitmapShader = bitmapShader2;
            this.paint.setShader(bitmapShader2);
            setLayerType(1, null);
            this.borderRadius = Math.min((getWidth() - this.borderWidth) / 2, (getHeight() - this.borderWidth) / 2);
            int min = Math.min((getWidth() - (this.borderWidth * 2)) / 2, (getHeight() - (this.borderWidth * 2)) / 2);
            this.drawRadius = min;
            if (this.addShadow) {
                float f = this.shadowRadius;
                this.borderRadius = (int) (((float) this.borderRadius) - f);
                this.drawRadius = (int) (((float) min) - f);
                this.paintBorder.setShadowLayer(f, 0.0f, 3.0f, this.shadowColor);
            }
            scaleImage();
            invalidate();
        }
    }

    private Bitmap getImageFromUri(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(this.context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getDrawableToBitmap(Drawable drawable) {
        if (drawable != null) {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
            drawable.draw(canvas);
            this.image = createBitmap;
        }
    }

    public int getBorderWidth() {
        return this.borderWidth;
    }

    public void setBorderWidth(int i) {
        this.borderWidth = i;
        setup();
    }

    public int getBorderColor() {
        return this.color;
    }

    public void setBorderColor(int i) {
        this.color = i;
        setup();
    }

    public boolean getAddShadow() {
        return this.addShadow;
    }

    public void setAddShadow(boolean z) {
        this.addShadow = z;
    }

    public float getShadowRadius() {
        return this.shadowRadius;
    }

    public void setShadowRadius(float f) {
        this.shadowRadius = f;
    }

    public int getShadowColor() {
        return this.shadowColor;
    }

    public void setShadowColor(int i) {
        this.shadowColor = i;
    }

    private void scaleImage() {
        float f;
        float f2;
        this.matrix.set(null);
        float f3 = 0.0f;
        if (this.bitmapWidth * getHeight() > this.bitmapHeight * getWidth()) {
            f2 = ((float) getHeight()) / ((float) this.bitmapHeight);
            f3 = (((float) getWidth()) - (((float) this.bitmapWidth) * f2)) * 0.5f;
            f = 0.0f;
        } else {
            f2 = ((float) getWidth()) / ((float) this.bitmapWidth);
            f = (((float) getHeight()) - (((float) this.bitmapHeight) * f2)) * 0.5f;
        }
        this.matrix.setScale(f2, f2);
        Matrix matrix2 = this.matrix;
        int i = this.borderWidth;
        matrix2.postTranslate((float) (((int) (f3 + 0.5f)) + i), (float) (((int) (f + 0.5f)) + i));
        this.bitmapShader.setLocalMatrix(this.matrix);
    }

    private void drawShadow() {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, this.paintBorder);
        }
        Paint paint2 = this.paintBorder;
        float f = this.shadowRadius;
        paint2.setShadowLayer(f, 0.0f, f / 2.0f, this.shadowColor);
    }

    public void loadHighResolutionImage(String str) {
        ImageCompress imageCompress = new ImageCompress(this.context);
        imageCompress.getLowBitmap(str);
        imageCompress.setOnInformImage(new ImageCompress.InformImage() {
            public void getLowLevelBitmap(Bitmap bitmap) {
                Bitmap unused = CircleImage.this.image = bitmap;
                CircleImage.this.setup();
            }
        });
    }
}
