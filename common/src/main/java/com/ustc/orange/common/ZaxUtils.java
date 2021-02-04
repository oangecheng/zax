package com.ustc.orange.common;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.ustc.orange.common.base.ZaxApplication;

/**
 * description:
 * Author: orange910617@gmail.com
 * date:2019-11-11
 * remark:
 */
public class ZaxUtils {

  private static Application sApp;

  private ZaxUtils() {
  }

  public static int dp2px(float dpValue) {
    final float scale = context().getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int px2dp(float pxValue) {
    final float scale = context().getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static int sp2px(float spValue) {
    final float fontScale = context().getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
  }

  public static Drawable drawable(@DrawableRes int drawableResId) {
    return ContextCompat.getDrawable(context(), drawableResId);
  }

  public static Context context() {
    return sApp;
  }

  public static void setContext(Application a){
    sApp = a;
  }

  public static Resources res() {
    return context().getResources();
  }
}
