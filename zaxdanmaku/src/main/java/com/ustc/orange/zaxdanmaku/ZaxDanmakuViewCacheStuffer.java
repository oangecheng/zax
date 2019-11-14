package com.ustc.orange.zaxdanmaku;

import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ustc.orange.common.ZaxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.ViewCacheStuffer;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * description: 代码
 * Author: orange910617@gmail.com
 * date:2019-11-11
 * remark:
 */
public class ZaxDanmakuViewCacheStuffer extends ViewCacheStuffer<ZaxDanmakuViewCacheStuffer.DanmakuViewHolder> {

  private DanmakuView mDanmakuView;

  public ZaxDanmakuViewCacheStuffer(DanmakuView danmakuView) {
    mDanmakuView = danmakuView;
  }

  @Override
  public DanmakuViewHolder onCreateViewHolder(int viewType) {
    return new DanmakuViewHolder(
      LayoutInflater.from(mDanmakuView.getContext()).inflate(R.layout.danmaku_item, null));
  }

  @Override
  public void onBindViewHolder(int viewType, DanmakuViewHolder viewHolder, BaseDanmaku danmaku,
                               AndroidDisplayer.DisplayerConfig displayerConfig, TextPaint paint) {
    ZaxDanmakuInfo info = (ZaxDanmakuInfo) danmaku.tag;
    viewHolder.mContent.setText(info.mContent);
    viewHolder.mAvatar.setImageDrawable(info.mDanmakuDrawable);
  }

  @Override
  public void prepare(BaseDanmaku danmaku, boolean fromWorkerThread) {
    super.prepare(danmaku, fromWorkerThread);
    getImageDrawable(danmaku);
  }

  @Override
  public void releaseResource(BaseDanmaku danmaku) {
    super.releaseResource(danmaku);
  }

  private void getImageDrawable(BaseDanmaku danmaku) {
    Disposable disposable = Observable.timer(100, TimeUnit.MILLISECONDS)
      .subscribe(count -> {
        ZaxDanmakuInfo info = (ZaxDanmakuInfo) danmaku.tag;
        info.mDanmakuDrawable = ZaxUtils.drawable(R.drawable.danmaku_avatar);
        mDanmakuView.invalidateDanmaku(danmaku, false);
      }, throwable -> {

      });
  }

  public static class DanmakuViewHolder extends ViewCacheStuffer.ViewHolder {

    public ImageView mAvatar;
    public TextView mContent;

    public DanmakuViewHolder(View itemView) {
      super(itemView);
      mAvatar = itemView.findViewById(R.id.danmaku_item_avatar);
      mContent = itemView.findViewById(R.id.danmaku_item_content);
    }
  }
}
