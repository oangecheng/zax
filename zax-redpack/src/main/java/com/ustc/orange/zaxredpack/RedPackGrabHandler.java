package com.ustc.orange.zaxredpack;

import android.util.Log;

public class RedPackGrabHandler implements RedPackHandler<KwaiCoins> {

  private RedPackHandler<KwaiCoins> mNext;
  private GrabRedPackInfo<KwaiCoins> mRedPackInfo;

  @Override
  public void handle(GrabRedPackInfo<KwaiCoins> redPackInfo) {
    mRedPackInfo = redPackInfo;
    grab();
  }

  @Override
  public RedPackHandler<KwaiCoins> getNext() {
    return mNext;
  }

  @Override
  public void setNext(RedPackHandler<KwaiCoins> next) {
    mNext = next;
  }


  private void grab() {
    Log.d("orange", "grab: ");
  }

}
