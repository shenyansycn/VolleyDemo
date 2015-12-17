package com.shenyan.volleytestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.china.library.Request.GsonRequest;
import com.china.library.VolleyInit.VolleyQueueManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.shenyan.volleytestdemo.bean.GetVerifyCodeRequest;
import com.shenyan.volleytestdemo.bean.GetVerifyCodeResponse;

import java.util.HashMap;

public class TestActivity extends AppCompatActivity {
    private String url = "http://api.sknus.com/v1.0/account/sendSmsCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("go clicked");
                GetVerifyCodeRequest getVerifyCodeRequest = new GetVerifyCodeRequest();
                getVerifyCodeRequest.setMobile("13552996629");
                getVerifyCodeRequest.setSmsBusiType("1");
                getVerifyCodeRequest.setIPv4("192.168.1.1");
                getVerifyCodeRequest.setTimestamp(String.valueOf(System.currentTimeMillis()));
                Gson gson = new GsonBuilder().create();
                String request = gson.toJson(getVerifyCodeRequest);
                Logger.json(request);


                HashMap<String, String> map = new HashMap<>();
                map.put("jsonParam", request);

                GsonRequest<GetVerifyCodeResponse> gsonRequest = new GsonRequest<>(Request.Method.POST, url, map, GetVerifyCodeResponse.class, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        GetVerifyCodeResponse response1 = (GetVerifyCodeResponse) response;
                        Logger.i("META message = %s - code = %s", response1.getMeta().getMessage(), response1.getMeta().getCode());
                        Logger.i("DATA resend_timestamp = %s", response1.getData().getResend_timestamp());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleyQueueManager.getInstance().addToRequestQueue(gsonRequest);
            }
        });
    }
}
