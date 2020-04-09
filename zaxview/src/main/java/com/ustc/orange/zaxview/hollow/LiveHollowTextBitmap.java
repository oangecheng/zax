package com.ustc.orange.zaxview.hollow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Size;

/**
 * description:
 * Author: orange910617@gmail.com
 * date:2019-09-16
 * remark:
 */
public class LiveHollowTextBitmap {
  private String mText;
  private Paint mTextPaint;
  private int mTextCoverAlpha;
  private int[] mTextPadding;
  private Paint mBackgroundPaint;
  private float mCornerRadius;
  private int mWidth;
  private int mHeight;

  public LiveHollowTextBitmap(@NonNull Builder builder) {
    mText = builder.mText;
    mBackgroundPaint = builder.mBackgroundPaint;
    mCornerRadius = builder.mCornerRadius;
    mTextPaint = builder.mTextPaint;
    mTextPadding = builder.mTextPadding;
    mTextCoverAlpha = builder.mTextLayer;
    Rect rect = new Rect();
    mTextPaint.getTextBounds(mText, 0, mText.length(), rect);
    mWidth = rect.right - rect.left + mTextPadding[0] + mTextPadding[2];
    mHeight = rect.bottom - rect.top + mTextPadding[1] + mTextPadding[3];
  }

  public Bitmap createHollowTextBitmap() {
    Bitmap hollowBmp = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(hollowBmp);
    canvas.drawRoundRect(0, 0, mWidth, mHeight, mCornerRadius, mCornerRadius, mBackgroundPaint);
    mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    canvas.drawText(mText, mTextPadding[0], getTextBaseLine(), mTextPaint);
    // 给镂空区域盖上蒙层，可选
    // 默认为黑色，如果有需要，自行在builder里扩展逻辑
    if (mTextCoverAlpha != 0) {
      mTextPaint.setAlpha(mTextCoverAlpha);
      mTextPaint.setXfermode(null);
      canvas.drawText(mText, mTextPadding[0], getTextBaseLine(), mTextPaint);
    }
    return hollowBmp;
  }

  private float getTextBaseLine() {
    Paint.FontMetricsInt metricsInt = mTextPaint.getFontMetricsInt();
    return mHeight - mTextPadding[3] - (metricsInt.ascent - metricsInt.top);
  }


  public static class Builder {
    private String mText;
    private float mCornerRadius;
    private int mTextLayer;
    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private int[] mTextPadding;

    public Builder() {
      mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      mTextPaint.setColor(Color.BLACK);
      mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      mBackgroundPaint.setColor(Color.WHITE);
      mTextPadding = new int[4];
    }

    public Builder text(@NonNull String text) {
      mText = text;
      return this;
    }

    public Builder textSize(int textSize) {
      mTextPaint.setTextSize(textSize);
      return this;
    }

    public Builder textTypeface(Typeface typeface) {
      mTextPaint.setTypeface(typeface);
      return this;
    }

    public Builder textCoverAlpha(@IntRange(from = 0, to = 255) int alpha) {
      mTextLayer = alpha;
      return this;
    }

    public Builder backgroundColor(@ColorInt int color) {
      mBackgroundPaint.setColor(color);
      return this;
    }

    public Builder textPadding(@Size(4) int[] padding) {
      mTextPadding = padding;
      return this;
    }

    public Builder textPadding(int padding) {
      for (int i = 0; i < 4; i++) {
        mTextPadding[i] = padding;
      }
      return this;
    }

    public Builder cornerRadius(float cornerRadius) {
      mCornerRadius = cornerRadius;
      return this;
    }

    public LiveHollowTextBitmap build() {
      return new LiveHollowTextBitmap(this);
    }
  }
}
