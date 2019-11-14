package com.ustc.orange.zax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ustc.orange.zaxdanmaku.ZaxDanmakuActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button danmaku = findViewById(R.id.btn_danmakus);
    danmaku.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ZaxDanmakuActivity.class);
      startActivity(intent);
    });
  }
}
