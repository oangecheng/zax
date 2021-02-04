package com.ustc.orange.common.statemachine;

import android.util.Log;

public class TestStateMachine extends StateMachine {

  private State mIdleState = new IdleState();
  private State mWorkState = new WorkState();

  public TestStateMachine() {
    addState(mIdleState);
    addState(mWorkState);

    sendMsg(new Message(IdleState.class));
  }

  public class IdleState extends State {
    @Override
    public void handle(Message message) {
      Log.d("orangeLog", "handle:" + "  idle");
    }
  }

  public class WorkState extends State {
    @Override
    public void handle(Message message) {
      Log.d("orangeLog", "handle:" + "  work");
    }
  }
}
