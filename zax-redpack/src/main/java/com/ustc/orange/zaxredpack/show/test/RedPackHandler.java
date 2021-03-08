package com.ustc.orange.zaxredpack.show.test;

public interface RedPackHandler<T> {

  void handle(GrabRedPackInfo<T> redPackInfo);

  void setNext(RedPackHandler<T> next);

  RedPackHandler<T> getNext();
}
