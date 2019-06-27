package com.wang.day_01;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DowLoadService extends Service {
    public DowLoadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ondata();
    }

    private void ondata() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //http://cdn.banmi.com/banmiapp/apk/banmi_330.apk
        Request request = new Request.Builder().url("http://cdn.banmi.com/banmiapp/apk/banmi_330.apk")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag","onFailure"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                long length = body.contentLength();
                InputStream inputStream = body.byteStream();
                saveFile(inputStream,length,Environment.getExternalStorageDirectory()+"/a.apk");
            }

            private void saveFile(InputStream inputStream, long length, String s) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(s));
                    int len = 0;
                    int count = 0;
                    byte[] bytes = new byte[1024 * 20];
                    while ((len= inputStream.read(bytes))!=-1){
                        inputStream.read(bytes,0,len);
                        count+=len;
                        Log.e("tag","下载进度"+count+"总进度"+len);
                        EnBean enBean = new EnBean();
                        enBean.setCount(count);
                        enBean.setCountlengt(length);
                        EventBus.getDefault().post(enBean);
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    Log.e("tag","下载完成");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


}
