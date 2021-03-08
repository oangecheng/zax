package com.ustc.orange.zaxredpack.show.test;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class RedPackChain {

  private RedPackHandler<KwaiCoins> mSendHandler;
  private RedPackHandler<KwaiCoins> mShowHandler;
  private RedPackHandler<KwaiCoins> mGrabHandler;

  private List<RedPackHandler<KwaiCoins>> mRedPackHandlersBetweenSendAndShow = new ArrayList<>();
  private List<RedPackHandler<KwaiCoins>> mRedPackHandlersBetweenShowAndGrab = new ArrayList<>();

  public RedPackChain(TextView view) {
    mSendHandler = new RedPackSendHandler();
    mShowHandler = new RedPackShowHandler(view);
    mGrabHandler = new RedPackGrabHandler();

    addHandlerBetweenSendAndShow(new RedPackSendAnimHandler(view));
    addHandlerBetweenShowAndGrab(new RedPackGrabAnimHandler(view));
  }

  public void handle() {
    RedPackHandler<KwaiCoins> index;
    index = iterator(mSendHandler, mRedPackHandlersBetweenSendAndShow);
    index.setNext(mShowHandler);
    index = iterator(mShowHandler, mRedPackHandlersBetweenShowAndGrab);
    index.setNext(mGrabHandler);
    mSendHandler.handle(new GrabRedPackInfo<>(new RedPack<>()));
  }

  private void addHandlerBetweenSendAndShow(RedPackHandler<KwaiCoins> handler) {
    mRedPackHandlersBetweenSendAndShow.add(handler);
  }

  private void addHandlerBetweenShowAndGrab(RedPackHandler<KwaiCoins> handler) {
    mRedPackHandlersBetweenShowAndGrab.add(handler);
  }

  private RedPackHandler<KwaiCoins> iterator(
      RedPackHandler<KwaiCoins> head,
      List<RedPackHandler<KwaiCoins>> handlers) {
    for (RedPackHandler<KwaiCoins> handler : handlers) {
      head.setNext(handler);
      head = handler;
    }
    return head;
  }
}
