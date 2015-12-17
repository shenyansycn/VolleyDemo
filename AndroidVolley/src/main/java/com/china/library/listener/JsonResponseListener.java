package com.china.library.listener;

import com.android.volley.VolleyError;

/**
 * Created by ShenYan on 15/12/17. Project VolleyTestDemo
 */
public interface JsonResponseListener<T> {
    void onResponse(T response);

    void onError(VolleyError error);
}
