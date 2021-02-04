package com.ustc.orange.zaxredpack;

public class GrabRedPackInfo<T> {

  private RedPack<T> mRedPack;
  private String mToken;

  public GrabRedPackInfo(RedPack<T> redPack) {
    mRedPack = redPack;
  }

  public void setToken(String token) {
    mToken = token;
  }

  public RedPack<T> getRedPack() {
    return mRedPack;
  }
}
