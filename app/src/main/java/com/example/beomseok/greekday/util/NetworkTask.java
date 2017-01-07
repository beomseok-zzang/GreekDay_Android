package com.example.beomseok.greekday.util;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by beomseok on 2017. 1. 7..
 */

public class NetworkTask extends AsyncTask {
    /** * doInBackground 실행되기 이전에 동작한다. */
    @Override protected void onPreExecute()
    { super.onPreExecute(); }


    @Override
    protected Object doInBackground(Object[] objects) {
        // HTTP 요청 준비 작업
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://52.78.247.195:8080/Greekday/android");
        // HTTP 요청 전송
        HttpClient post = http.create(); post.request();
        // 응답 상태코드 가져오기
        int statusCode = post.getHttpStatusCode();
        // 응답 본문 가져오기
        String body = post.getBody();
        return body;
    }

    protected void onPostExecute(String s)
    {
        Log.d("HTTP_RESULT", s);
    }


}
