package com.shenyan.volleytestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.china.library.VolleyInit.VolleyImageLoaderManager;
import com.china.library.VolleyInit.VolleyQueueManager;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {
    private String newUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        final ImageView icon = (ImageView) findViewById(R.id.imageview);
        Button go = (Button) findViewById(R.id.button);
        final Button showImage = (Button) findViewById(R.id.button1);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("onClick GO");

               String Url = "http://graph.facebook.com/130757673948254/picture?type=square";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            int statusCode = error.networkResponse.statusCode;
                            Logger.i("onErrorResponse state Code = " + error.networkResponse.statusCode);
                            Logger.i("onErrorResponse error = " + error.networkResponse.headers);
                            if (statusCode == 301 || statusCode == 302) {
                                newUrl = error.networkResponse.headers.get("Location");
                                Logger.i("onErrorResponse new URL = " + newUrl);
                                if (null != newUrl && !"".equals(newUrl)) {
                                    showImage.setEnabled(true);
                                }
                            }
                        } else {
                            Logger.i("onErrorResponse error is null");
                        }
                    }
                });
                VolleyQueueManager.getInstance().addToRequestQueue(stringRequest);
            }
        });
        showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("onClick showImage new Url = " + newUrl);
                VolleyImageLoaderManager.getInstance().getImageLoader().get(newUrl, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        if (response != null) {
                            icon.setImageBitmap(response.getBitmap());
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }
        });
    findViewById(R.id.gototest).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, TestActivity.class));
        }
    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
