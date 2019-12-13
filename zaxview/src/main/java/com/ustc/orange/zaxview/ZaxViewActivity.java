package com.ustc.orange.zaxview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.ustc.orange.zaxview.simple.ZaxArcTextView;

/**
 *
 */
public class ZaxViewActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.zax_view_activity);
    showZaxArcTextView();
  }

  private void showZaxArcTextView() {
    ZaxArcTextView arcTextView = findViewById(R.id.zax_arc_text_view);
    arcTextView.setText("我是弧形文字，并且居中显示，哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
    arcTextView.setTextColor(Color.WHITE);
    arcTextView.setTextSize(30);
    arcTextView.refresh();
  }
}
