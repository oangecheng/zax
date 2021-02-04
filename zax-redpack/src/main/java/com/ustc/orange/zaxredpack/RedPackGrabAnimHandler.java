package com.ustc.orange.zaxredpack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

public class RedPackGrabAnimHandler implements RedPackHandler<KwaiCoins> {

  private TextView mTextView;
  private RedPackHandler<KwaiCoins> mNext;

  public RedPackGrabAnimHandler(TextView textView) {
    mTextView = textView;
  }

  @Override
  public void handle(GrabRedPackInfo<KwaiCoins> redPackInfo) {
    anim(redPackInfo);
  }

  @Override
  public RedPackHandler<KwaiCoins> getNext() {
    return mNext;
  }

  @Override
  public void setNext(RedPackHandler<KwaiCoins> next) {
    mNext = next;
  }

  private void anim(GrabRedPackInfo<KwaiCoins> redPackInfo) {
    ObjectAnimator alpha = ObjectAnimator.ofFloat(mTextView, View.SCALE_X, 1f, 0f);
    alpha.setDuration(3000);
    alpha.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        if (mNext != null) {
          mNext.handle(redPackInfo);
        }
      }
    });
    alpha.start();
  }

}
