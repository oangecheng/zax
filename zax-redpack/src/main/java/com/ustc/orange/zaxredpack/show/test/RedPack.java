package com.ustc.orange.zaxredpack.show.test;

import androidx.annotation.Nullable;

public class RedPack<REWARD> {

  private REWARD mReward;
  private String mId;

  public RedPack() {
  }

  @Nullable
  public REWARD getReward() {
    return mReward;
  }

  public void setReward(REWARD reward) {
    mReward = reward;
  }

  @Nullable
  public String getId() {
    return mId;
  }

  public void setId(String id) {
    mId = id;
  }
}
