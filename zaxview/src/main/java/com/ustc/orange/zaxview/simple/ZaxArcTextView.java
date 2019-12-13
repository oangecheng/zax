package com.ustc.orange.zaxview.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 以圆弧为路径绘制文字
 * view会沿view的边框绘制文字， 并且居中显示
 */
public class ZaxArcTextView extends View {
  private String mText = "";
  private Path mPath = new Path();
  private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public ZaxArcTextView(Context context) {
    this(context, null);
  }

  public ZaxArcTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ZaxArcTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.WHITE);
  }

  public ZaxArcTextView setText(String text) {
    mText = text;
    return this;
  }

  public ZaxArcTextView setTextSize(int textSize) {
    mPaint.setTextSize(textSize);
    return this;
  }

  public ZaxArcTextView setTextColor(int color) {
    mPaint.setColor(color);
    return this;
  }

  public ZaxArcTextView refresh() {
    updateValues();
    invalidate();
    return this;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    updateValues();
  }

  private void updateValues() {
    float width = getMeasuredWidth(), height = getMeasuredHeight();
    float radius = (width * width * 0.125f) / height + height * 0.5f - mPaint.getTextSize();
    RectF arcRect = new RectF(width / 2 - radius, 0, width / 2 + radius, 2 * radius);
    float textWidth = mPaint.measureText(mText);
    float sweepAngle = (float) (textWidth * 180 / (Math.PI * radius));
    mPath.reset();
    mPath.addArc(arcRect, 270 - sweepAngle * 0.5f, sweepAngle);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawTextOnPath(mText, mPath, 0, mPaint.getTextSize(), mPaint);
  }
}

