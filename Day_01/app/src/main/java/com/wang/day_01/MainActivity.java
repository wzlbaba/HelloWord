package com.wang.day_01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 跳转
     */
    private Button mBt;
    private ImageView mIv;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);
        initData();
    }

    private void initData() {
       //透明
               ObjectAnimator animator1 = ObjectAnimator.ofFloat(mIv, "alpha", 1f, 0f, 1f, 0f, 1f);
               //旋转
               ObjectAnimator animator2 = ObjectAnimator.ofFloat(mIv, "rotation", 0f, 360f, 0f);
               //缩放
               ObjectAnimator animator3 = ObjectAnimator.ofFloat(mIv, "scaleX", 2f, 4f, 1f, 0.5f, 1f);
               //移动
               int height = 0;
               ObjectAnimator animator4 = ObjectAnimator.ofFloat(mIv, "translationY", height / 8, -100, height / 2);

               AnimatorSet animSet = new AnimatorSet();
               animSet.play(animator4).with(animator3).with(animator1).before(animator2);
               animSet.setDuration(5000l);
               animSet.start();
                 CountDownTimer  countDownTimer = new CountDownTimer(6000, 1000) {
                             @Override
                             public void onTick(long millisUntilFinished) {
                                 mTv.setText(millisUntilFinished / 1000 + "");
                             }

                             @Override
                             public void onFinish() {
                                 Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                 startActivity(intent);
                                 finish();
                             }
                         }.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
        }
    }
}
