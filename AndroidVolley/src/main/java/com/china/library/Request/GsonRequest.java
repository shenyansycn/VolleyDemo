package com.china.library.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ShenYan on 2015/7/2. Project ChinaPay
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private Map<String, String> params = null;
    private final Response.Listener<T> listener;
    private String requestBody = null;

    public GsonRequest(int method, String url, Map<String, String> params, Class<T> clazz, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.params = params;
        this.listener = listener;
    }
    public GsonRequest(int method, String url, String requestBody, Class<T> clazz, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.requestBody = requestBody;
        this.listener = listener;
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getParams();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return requestBody != null ? requestBody.getBytes() : super.getBody();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Logger.i("stateCode = %s", response.statusCode );

//            for (String key : response.headers.keySet()) {
//                Logger.i("%s : %s", key, response.headers.get(key));
//            }
            String json = new String(response.data, "UTF-8");
            Logger.json(json);
            Logger.i(json);
            if (clazz == null) {
                return null;
            } else {
                return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {

            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {

            return Response.error(new ParseError(e));
        }
    }


    @Override
    protected void deliverResponse(T response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }
}
