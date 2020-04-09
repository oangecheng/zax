package com.ustc.orange.zaxview.simple;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ustc.orange.common.ZaxUtils;
import com.ustc.orange.zaxview.R;

import java.lang.ref.WeakReference;

/**
 *
 */
public class ZaxSpan extends DynamicDrawableSpan {

  private int mWidth;
  private Paint mPaint;
  private String mText = "1  橙子君gh";

  private int mHorizontalPadding = 10;
  private int mVerticalPadding = 10;

  private WeakReference<Drawable> mDrawableRef;

  private Rect mRect = new Rect();

  public ZaxSpan() {
    mPaint = new Paint();
    mPaint.setColor(Color.WHITE);
    mPaint.setAntiAlias(true);
    mPaint.setTextSize(ZaxUtils.sp2px(10));
    mWidth = (int) mPaint.measureText(mText, 0, mText.length()) + mHorizontalPadding * 2;
    mPaint.getTextBounds(mText, 0, mText.length(), mRect);
  }

  @Override
  public Drawable getDrawable() {
    Drawable drawable = ZaxUtils.drawable(R.drawable.zax_text_span_background);
    drawable.setBounds(0, 0, mWidth, (int) mPaint.getTextSize() + mVerticalPadding * 2);
    return drawable;
  }

  @Override
  public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
    return mWidth;
  }

  @Override
  public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
    Drawable drawable = getCachedDrawable();
    canvas.save();
    int transY = ((bottom - top) - drawable.getBounds().bottom) / 2 + top;
    canvas.translate(x, transY);
    drawable.draw(canvas);
    canvas.drawText(mText, 0, mText.length(), mHorizontalPadding, drawable.getBounds().centerY() - mRect.centerY(), mPaint);
    canvas.restore();
  }

  private Drawable getCachedDrawable() {
    WeakReference<Drawable> wr = mDrawableRef;
    Drawable d = null;

    if (wr != null) {
      d = wr.get();
    }

    if (d == null) {
      d = getDrawable();
      mDrawableRef = new WeakReference<>(d);
    }
    return d;
  }
}
