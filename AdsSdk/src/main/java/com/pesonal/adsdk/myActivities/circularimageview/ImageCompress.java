package com.pesonal.adsdk.myActivities.circularimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;

class ImageCompress {
    /* access modifiers changed from: private */
    public InformImage informImage;
    /* access modifiers changed from: private */
    public Bitmap newBitmap;
    Context context;

    public ImageCompress(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public Bitmap getLowBitmap(String str) {
        new ImgCompression(str).execute();
        return this.newBitmap;
    }

    /* access modifiers changed from: protected */
    public void setOnInformImage(InformImage informImage2) {
        this.informImage = informImage2;
    }

    interface InformImage {
        void getLowLevelBitmap(Bitmap bitmap);
    }

    private class ImgCompression extends AsyncTask<Void, Void, Void> {
        private static final float maxHeight = 1280.0f;
        private static final float maxWidth = 1280.0f;
        private final String string;

        public ImgCompression(String str) {
            this.string = str;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            if (compressImage() == null) {
                return null;
            }
            Bitmap unused = ImageCompress.this.newBitmap = compressImage();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            if (ImageCompress.this.informImage != null) {
                ImageCompress.this.informImage.getLowLevelBitmap(ImageCompress.this.newBitmap);
            }
        }

        private Bitmap compressImage() {
            Bitmap bitmap;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap decodeFile = BitmapFactory.decodeFile(this.string, options);
            int i = options.outHeight;
            int i2 = options.outWidth;
            float f = (float) i2;
            float f2 = (float) i;
            float f3 = f / f2;
            if (f2 > 1280.0f || f > 1280.0f) {
                if (f3 < 1.0f) {
                    i2 = (int) ((1280.0f / f2) * f);
                    i = 1280;
                } else {
                    i = f3 > 1.0f ? (int) ((1280.0f / f) * f2) : 1280;
                    i2 = 1280;
                }
            }
            options.inSampleSize = calculateInSampleSize(options, i2, i);
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPurgeable = true;
            options.inTempStorage = new byte[16384];
            try {
                decodeFile = BitmapFactory.decodeFile(this.string, options);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
            try {
                bitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.RGB_565);
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                bitmap = null;
            }
            float f4 = (float) i2;
            float f5 = f4 / ((float) options.outWidth);
            float f6 = (float) i;
            float f7 = f6 / ((float) options.outHeight);
            float f8 = f4 / 2.0f;
            float f9 = f6 / 2.0f;
            Matrix matrix = new Matrix();
            matrix.setScale(f5, f7, f8, f9);
            Canvas canvas = new Canvas(bitmap);
            canvas.setMatrix(matrix);
            canvas.drawBitmap(decodeFile, f8 - ((float) (decodeFile.getWidth() / 2)), f9 - ((float) (decodeFile.getHeight() / 2)), new Paint(2));
            decodeFile.recycle();
            return bitmap;
        }

        private int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
            int i3;
            int i4 = options.outHeight;
            int i5 = options.outWidth;
            if (i4 > i2 || i5 > i) {
                i3 = Math.round(((float) i5) / ((float) i));
                int round = Math.round(((float) i4) / ((float) i2));
                if (round < i3) {
                    i3 = round;
                }
            } else {
                i3 = 1;
            }
            while (((float) (i5 * i4)) / ((float) (i3 * i3)) > ((float) (i2 * i * 2))) {
                i3++;
            }
            return i3;
        }
    }
}
