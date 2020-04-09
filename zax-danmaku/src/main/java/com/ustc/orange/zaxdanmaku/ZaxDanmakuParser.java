package com.ustc.orange.zaxdanmaku;

import java.util.List;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

import static master.flame.danmaku.danmaku.model.IDanmakus.ST_BY_TIME;

/**
 * description:
 * Author: orange910617@gmail.com
 * date:2019-11-11
 * remark:
 */
public class ZaxDanmakuParser extends BaseDanmakuParser {

  private static final long DEFAULT_OFFSET_MS = 5000;
  private DanmakuContext mDanmakuContext;
  private List<ZaxDanmakuInfo> mDanmakuInfos;

  public ZaxDanmakuParser(DanmakuContext danmakuContext) {
    mDanmakuContext = danmakuContext;
  }

  public ZaxDanmakuParser setDanmakuInfos(List<ZaxDanmakuInfo> danmakuInfos) {
    this.mDanmakuInfos = danmakuInfos;
    return this;
  }

  @Override
  protected IDanmakus parse() {
    IDanmakus danmakus = new Danmakus(ST_BY_TIME, false, mDanmakuContext.getBaseComparator());
    if (mDanmakuInfos != null && !mDanmakuInfos.isEmpty()) {
      long preDanmakuShowTime = DEFAULT_OFFSET_MS;
      for (ZaxDanmakuInfo info : mDanmakuInfos) {
        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory
          .createDanmaku(BaseDanmaku.TYPE_SCROLL_RL, mDanmakuContext);
        danmaku.priority = 0;
        danmaku.setTag(info);
        danmaku.setTime(preDanmakuShowTime);
        preDanmakuShowTime = (long) (preDanmakuShowTime + Math.random() * 3000);
        danmaku.setTimer(mTimer);
        danmaku.flags = mDanmakuContext.mGlobalFlagValues;
        danmakus.addItem(danmaku);
      }
    }
    return danmakus;
  }


}
