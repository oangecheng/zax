package com.ustc.orange.zaxredpack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RedPackActivity extends Activity {

  TextView mTextView;
  Button mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_red_pack);

    mTextView = findViewById(R.id.red_pack);
    mButton = findViewById(R.id.red_pack_test);

    RedPackChain redPackChain = new RedPackChain(mTextView);

    mButton.setOnClickListener(view -> {
      redPackChain.handle();
    });

  }
}