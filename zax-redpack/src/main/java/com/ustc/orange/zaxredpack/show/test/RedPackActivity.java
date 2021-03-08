package com.ustc.orange.zaxredpack.show.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ustc.orange.zaxredpack.R;

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