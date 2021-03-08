package com.ustc.orange.zaxredpack.core;

public class BaseRedPack<REWARD> {

  public int mId;

  public REWARD mReward;

  public BaseRedPack<REWARD> setId(int id) {
    mId = id;
    return this;
  }

  public BaseRedPack<REWARD> setReward(REWARD reward) {
    mReward = reward;
    return this;
  }
}
