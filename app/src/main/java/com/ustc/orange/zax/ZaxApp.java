package com.ustc.orange.zax;

import android.app.Application;

import com.ustc.orange.common.ZaxUtils;

public class ZaxApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    ZaxUtils.setContext(this);
  }
}
