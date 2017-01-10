package com.example.beomseok.greekday.util;

import android.os.AsyncTask;

/**
 * Created by beomseok on 2017. 1. 7..
 */
// background 작업에 사용할 data의 자료형: String 형
// background 작업 진행 표시를 위해 사용할 인자: Integer형
// background 작업의 결과를 표현할 자료형: Long

public class NetworkTask extends AsyncTask<String, Void, Object> {
    /** * doInBackground 실행되기 이전에 동작한다. */
    public static final String TOPPPING="topping";
    public static final String ISOPEN="in_open";
    public static final String BASE_SMALL="base_small";
    public static final String BASE_MEDIUM="base_medium";
    public static final String BASE_LARGE="base_large";

    NetWorkListener netWorkListener;
    public NetworkTask(NetWorkListener netWorkListener){
        this.netWorkListener = netWorkListener;
    }
    @Override
    protected String doInBackground(String... params) {
        // HTTP 요청 준비 작업

        HttpClient.Builder http = new HttpClient.Builder("GET", GLOBAL.api+"/type="+params[0]);
        // HTTP 요청 전송
        HttpClient get = http.create();
        get.request();

        // 응답 상태코드 가져오기
        int statusCode = get.getHttpStatusCode();
        // 응답 본문 가져오기
        String body = get.getBody();
        return body;
    }


    @Override
    protected void onPreExecute()
    { super.onPreExecute(); }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        netWorkListener.postSucceed(o);

    }
}
