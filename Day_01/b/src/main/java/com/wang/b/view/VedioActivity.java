package com.wang.b.view;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wang.b.R;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VedioActivity extends AppCompatActivity {

    private JZVideoPlayerStandard JZVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        initView();
    }

    private void initView() {
        JZVideoPlayer = (JZVideoPlayerStandard) findViewById(R.id.jz_video_player);
        JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        JZVideoPlayer.TOOL_BAR_EXIST = false;
        JZVideoPlayer.setUp("http://ssb-video.oss-cn-qingdao.aliyuncs.com/Video_1003_20161027140007.mp4"
                , JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "加载成功");
        Glide.with(getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01c10808f98a39bd4f.jpg")
                .into(JZVideoPlayer.thumbImageView);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

}
