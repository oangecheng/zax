package com.ustc.orange.zaxredpack.show;

import com.ustc.orange.zaxredpack.GrabRedPackInfo;
import com.ustc.orange.zaxredpack.KwaiCoins;
import com.ustc.orange.zaxredpack.RedPackHandler;

public class RedPackJoinHandler implements RedPackHandler<KwaiCoins> {

  private RedPackHandler<KwaiCoins> mNext;
  private GrabRedPackInfo<KwaiCoins> mRedPackInfo;

  @Override
  public void handle(GrabRedPackInfo<KwaiCoins> redPackInfo) {
    mRedPackInfo = redPackInfo;
    join();
  }

  @Override
  public RedPackHandler<KwaiCoins> getNext() {
    return mNext;
  }

  @Override
  public void setNext(RedPackHandler<KwaiCoins> next) {
    mNext = next;
  }


  private void join() {

  }


}
