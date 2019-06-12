package com.wang.day03_lx;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        CountDownTimer countDownTimer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTv.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, SchoolActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }
}
