package com.china.library.listener;

import com.android.volley.VolleyError;

/**
 * Created by ShenYan on 15/12/17. Project VolleyTestDemo
 */
public interface ResponseListener<T> {
    void onResponse(T response);

    void onError(VolleyError error);
}
