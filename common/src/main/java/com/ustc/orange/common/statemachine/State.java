package com.ustc.orange.common.statemachine;

public abstract class State {

  public abstract void handle(Message message);
}
