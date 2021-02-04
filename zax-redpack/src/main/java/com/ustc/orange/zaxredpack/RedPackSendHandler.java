package com.ustc.orange.zaxredpack;

import java.util.concurrent.TimeUnit;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RedPackSendHandler implements RedPackHandler<KwaiCoins> {

  private RedPackHandler<KwaiCoins> mNext;
  private GrabRedPackInfo<KwaiCoins> mRedPackInfo;

  @Override
  public void handle(GrabRedPackInfo<KwaiCoins> redPackInfo) {
    mRedPackInfo = redPackInfo;
    sendRedPacket();
  }

  @Override
  public RedPackHandler<KwaiCoins> getNext() {
    return mNext;
  }

  @Override
  public void setNext(RedPackHandler<KwaiCoins> next) {
    mNext = next;
  }

  private void sendRedPacket() {
    GrabRedPackInfo<KwaiCoins> coinsRedPack = new GrabRedPackInfo<>(new RedPack<>());
    Disposable disposable = Observable.just(coinsRedPack)
        .delay(1000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(redPackInfo -> {
          mRedPackInfo = coinsRedPack;
          if (getNext() != null) {
            getNext().handle(mRedPackInfo);
          }
        }, throwable -> {
          Log.d("orange", "sendRedPacket: ");
        });
  }
}
