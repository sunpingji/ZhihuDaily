package pjsun.zhihudaily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import pjsun.zhihudaily.business.api.API;

public class MainActivity extends AppCompatActivity {
    Object ss[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkGo.get(API.News).execute(new AbsCallback<String>() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                try {
                    Logger.d(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String convertSuccess(Response response) throws Exception {
                return null;
            }
        });
    }
}
