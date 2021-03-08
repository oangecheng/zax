package com.ustc.orange.zax;

import java.util.Arrays;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ustc.orange.zaxdanmaku.ZaxDanmakuActivity;
import com.ustc.orange.zaxview.ZaxViewActivity;

public class MainActivity extends AppCompatActivity {

  private int mCount  = 100000;
  private Random mRandom = new Random();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button danmaku = findViewById(R.id.btn_danmakus);
    danmaku.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ZaxDanmakuActivity.class);
      startActivity(intent);
    });

    Button zaxView = findViewById(R.id.btn_zaxview);
    zaxView.setOnClickListener(v -> {


      int[] array1 = new int[mCount];
      for (int i = 0; i < mCount ; i++) {
        array1[i] = mRandom.nextInt(mCount);
      }

      long s = System.nanoTime();
      int sum = 0;
      for (int i = 0; i < mCount; i++) {
        sum += array1[i];
      }
      long c = System.nanoTime() - s;
      Log.d("orangeLog", "onCreate:" + "  " + c);


      int[] array2 = new int[mCount];
      for (int i = 0; i < mCount; i++) {
        array2[i] = mRandom.nextInt(mCount);
      }
      Arrays.sort(array2);
      long s2 = System.nanoTime();
      int sum2 = 0;
      for (int i = 0; i < mCount; i++) {
        sum2 += array2[i];
      }
      long c2 = System.nanoTime() - s2;
      Log.d("orangeLog", "onCreate:" + "  " + c2);


      Intent intent = new Intent(MainActivity.this, ZaxViewActivity.class);
      startActivity(intent);
    });

  }
}
