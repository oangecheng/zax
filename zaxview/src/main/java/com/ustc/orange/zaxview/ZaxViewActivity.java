package com.ustc.orange.zaxview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.ustc.orange.zaxview.hollow.LiveHollowTextBitmap;
import com.ustc.orange.zaxview.simple.ZaxArcTextView;
import com.ustc.orange.zaxview.simple.ZaxSpan;

/**
 *
 */
public class ZaxViewActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.zax_view_activity);
    showZaxArcTextView();
    showHollow();
    showSpan();
  }

  private void showZaxArcTextView() {
    ZaxArcTextView arcTextView = findViewById(R.id.zax_arc_text_view);
    arcTextView.setText("我是弧形文字，并且居中显示，哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
    arcTextView.setTextColor(R.color.color_white);
    arcTextView.setTextSize(30);
    arcTextView.refresh();
  }

  private void showHollow(){
    LiveHollowTextBitmap b = new LiveHollowTextBitmap.Builder()
      .text("智全牛逼")
      .textSize(100)
      .textPadding(10)
      .textCoverAlpha(10)
      .textTypeface(Typeface.DEFAULT_BOLD)
      .backgroundColor(Color.parseColor("#77FF0000"))
      .cornerRadius(10)
      .build();
    final ImageView view = findViewById(R.id.hollow);
    view.setImageBitmap(b.createHollowTextBitmap());
  }

  private void showSpan() {
    SpannableStringBuilder builder = new SpannableStringBuilder();
    builder.append("我爱中国123456");
    builder.append("~");
    builder.setSpan(new ZaxSpan(), builder.length()-1, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    TextView tv = findViewById(R.id.span);
    tv.setTextSize(20);
    tv.setText(builder);
  }
}
