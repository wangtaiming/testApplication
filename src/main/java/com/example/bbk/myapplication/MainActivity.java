
package com.example.bbk.myapplication;

import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //test11111
    ///here

    private OkHttpClient client;
    private TextView textView;
    private static final String TAG = "urlTest";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.text);
        Log.d(TAG, "tc: "+Thread.currentThread().getName());
        Log.d(TAG, "pc: "+ Process.myPid());
        net();
    }

    public void net(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "t: "+Thread.currentThread().getName());
                Log.d(TAG, "p: "+ Process.myPid());

                client=new OkHttpClient();
                final Request request=new Request.Builder()
                        .url("https://baidu.com")
                        .build();
                Call call=client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: ok");
                        textView.setText(response.body().toString());
                    }
                });
            }
        }).start();

    }

}

