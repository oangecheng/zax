package com.ustc.orange.common.statemachine;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {

  private Map<Class<? extends State>, State> mStates = new HashMap<>();

  public StateMachine() {}

  public void addState(State state) {
    mStates.put(state.getClass(), state);
  }

  public void sendMsg(Message message) {
    State state = mStates.get(message.mTargetState);
    if (state != null) {
      state.handle(message);
    }
  }
}
