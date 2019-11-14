package com.ustc.orange.common.base;

import android.app.Application;
import android.content.Context;

/**
 * description:
 * Author: orange910617@gmail.com
 * date:2019-11-11
 * remark:
 */
public class ZaxApplication extends Application {

  private static Application sApp;

  public static Context getContext() {
    return sApp;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    sApp = this;
  }
}
