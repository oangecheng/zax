package com.ustc.orange.zaxredpack.show.test;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class RedPackShowHandler implements RedPackHandler<KwaiCoins> {

  private RedPackHandler<KwaiCoins> mNext;
  private GrabRedPackInfo<KwaiCoins> mRedPackInfo;
  private TextView mView;

  public RedPackShowHandler(TextView view) {
    mView = view;
  }

  @Override
  public void handle(GrabRedPackInfo<KwaiCoins> redPackInfo) {
    mRedPackInfo = redPackInfo;
    showPendant();
  }

  @Override
  public RedPackHandler<KwaiCoins> getNext() {
    return mNext;
  }

  @Override
  public void setNext(RedPackHandler<KwaiCoins> next) {
    mNext = next;
  }

  private void showPendant() {
    mView.setVisibility(View.VISIBLE);
    Log.d("orange", "showPendant: ");

    if (mNext!=null){
      mNext.handle(mRedPackInfo);
    }
  }
}
