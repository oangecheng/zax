package com.ustc.orange.zaxview.simple;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *
 */
public class ZaxHollowSpan extends DynamicDrawableSpan {

  @Override
  public Drawable getDrawable() {
    return null;
  }

  @Override
  public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
    return super.getSize(paint, text, start, end, fm);
  }

  @Override
  public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
    super.draw(canvas, text, start, end, x, top, y, bottom, paint);
  }
}
