package com.ustc.orange.zaxdanmaku;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.common.collect.Maps;
import com.ustc.orange.common.ZaxUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.ViewCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * description:
 * Author: orange910617@gmail.com
 * date:2019-11-11
 * remark:
 */
public class ZaxDanmakuActivity extends Activity {

  private DanmakuView mDanmakuView;
  private DanmakuContext mDanmakuContext = DanmakuContext.create();
  private BaseDanmakuParser mDanmakuParser;
  private ViewCacheStuffer mViewCacheStuffer;
  private List<ZaxDanmakuInfo> mDanmakuInfos = new LinkedList<>();
  private long mDanmakuPrepareTime = 1000;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.damaku_main_layout);
    initViews();
    initDanmaku();

    Disposable disposable = Observable.timer(200, TimeUnit.MILLISECONDS)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(aLong -> {
        for (int i = 0; i < 20; i++) {
          ZaxDanmakuInfo danmakuInfo = new ZaxDanmakuInfo();
          danmakuInfo.mContent = "我是第" + (i + 1) + "条弹幕";
          danmakuInfo.mUsername = "orange";
          mDanmakuInfos.add(danmakuInfo);
        }
        startLoop();
      });
  }

  private void initViews() {
    mDanmakuView = findViewById(R.id.danmaku_view);
  }

  private void initDanmaku() {
    mDanmakuView.enableDanmakuDrawingCache(false);
    mDanmakuView.setCallback(new DrawHandler.Callback() {
      @Override
      public void prepared() {
        mDanmakuView.start();
      }

      @Override
      public void updateTimer(DanmakuTimer timer) {
      }

      @Override
      public void danmakuShown(BaseDanmaku danmaku) {
      }

      @Override
      public void drawingFinished() {
      }

    });

    mDanmakuView.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {
      @Override
      public boolean onDanmakuClick(IDanmakus danmakus) {
        ZaxDanmakuInfo info = (ZaxDanmakuInfo) (danmakus.first().tag);
        Log.d("orangetest", "onDanmakuClick" + info.mContent);
        return true;
      }

      @Override
      public boolean onDanmakuLongClick(IDanmakus danmakus) {
        Log.d("orangetest", "onDanmakuLongClick");
        return false;
      }

      @Override
      public boolean onViewClick(IDanmakuView view) {
        Log.d("orangetest", "onViewClick");
        return false;
      }
    });
    mViewCacheStuffer = new ZaxDanmakuViewCacheStuffer(mDanmakuView);
    // 设置最大显示行数
    Map<Integer, Integer> maxLinesPair = Maps.newHashMap();
    maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3);
    // 设置是否禁止重叠
    Map<Integer, Boolean> overlappingEnablePair = Maps.newHashMap();
    overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
    mDanmakuContext = DanmakuContext.create();
    mDanmakuContext.setScrollSpeedFactor(2f)
      .setDanmakuMargin(ZaxUtils.dp2px(10))
      .setMaximumLines(maxLinesPair)
      .preventOverlapping(overlappingEnablePair)
      .setCacheStuffer(mViewCacheStuffer, null);
    mDanmakuParser = new BaseDanmakuParser() {
      @Override
      protected IDanmakus parse() {
        return new Danmakus();
      }
    };
    mDanmakuView.prepare(mDanmakuParser, mDanmakuContext);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (mDanmakuView != null && mDanmakuView.isPrepared()) {
      mDanmakuView.pause();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
      mDanmakuView.resume();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mDanmakuView != null) {
      // dont forget release!
      mDanmakuView.release();
      mDanmakuView = null;
    }
  }

  private void startLoop() {
    Disposable disposable = Observable
      .interval(1000, TimeUnit.MILLISECONDS, Schedulers.newThread())
      .subscribe(count -> {
        if (mDanmakuView == null || mDanmakuView.isPaused() || !mDanmakuView.isPrepared()) {
          return;
        }
        int index = count.intValue() % mDanmakuInfos.size();
        ZaxDanmakuInfo info = mDanmakuInfos.get(index);
        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory
          .createDanmaku(BaseDanmaku.TYPE_SCROLL_RL, mDanmakuContext);
        danmaku.priority = 0;
        danmaku.setTag(info);
        danmaku.setTime(mDanmakuPrepareTime);
        mDanmakuPrepareTime = (long) (mDanmakuPrepareTime + Math.random() * 3000);
        danmaku.setTimer(mDanmakuParser.getTimer());
        danmaku.flags = mDanmakuContext.mGlobalFlagValues;
        mDanmakuView.addDanmaku(danmaku);
      });
  }
}
