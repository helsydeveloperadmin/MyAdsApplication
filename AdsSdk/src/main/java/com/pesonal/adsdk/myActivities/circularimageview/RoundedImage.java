package com.pesonal.adsdk.myActivities.circularimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.pesonal.adsdk.R;


public class RoundedImage extends ImageView {
    private final float DEFAULT_RADIUS;
    private final Path path;
    private final ScaleType scaleType;
    private float radius;
    private RectF rect;

    public RoundedImage(Context context) {
        super(context);
        this.path = new Path();
        this.DEFAULT_RADIUS = 20.0f;
        this.radius = 20.0f;
        ScaleType scaleType2 = ScaleType.FIT_XY;
        this.scaleType = scaleType2;
        setScaleType(scaleType2);
        init();
    }

    public RoundedImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        setScaleType(this.scaleType);
        init();
    }

    public RoundedImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.path = new Path();
        this.DEFAULT_RADIUS = 20.0f;
        this.radius = 20.0f;
        ScaleType scaleType2 = ScaleType.FIT_XY;
        this.scaleType = scaleType2;
        setScaleType(scaleType2);
        this.radius = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImage, i, 0).getFloat(R.styleable.RoundedImage_riv_rounded_radius, this.DEFAULT_RADIUS);
        init();
    }

    public int roundRadius() {
        return (int) this.radius;
    }

    public void setRoundedRadius(int i) {
        this.radius = (float) i;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.clipPath(this.path);
        super.onDraw(canvas);
    }

    private void init() {
        RectF rectF = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.rect = rectF;
        Path path2 = this.path;
        float f = this.radius;
        path2.addRoundRect(rectF, f, f, Path.Direction.CCW);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        init();
    }
}
