package com.ustc.orange.common.statemachine;

public class Message {

  public Class<? extends State> mTargetState;
  public Object mObj;

  public Message(Class<? extends State> targetState) {
    mTargetState = targetState;
  }
}
