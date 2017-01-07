package com.example.beomseok.greekday;

import android.app.Application;
import android.content.res.Configuration;

import com.tsengvn.typekit.Typekit;

/**
 * Created by beomseok on 2016. 12. 28..
 */

public class GreekDayApplication extends Application {


    /** onCreate()
     * 액티비티, 리시버, 서비스가 생성되기전 어플리케이션이 시작 중일때
     * Application onCreate() 메서드가 만들어 진다고 나와 있습니다.
     * by. Developer 사이트
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/dxlbastd_b.otf"))
                .addBold(Typekit.createFromAsset(this, "fonts/dxlbastd_b.otf"));
    }

    /**
     * onConfigurationChanged()
     * 컴포넌트가 실행되는 동안 단말의 화면이 바뀌면 시스템이 실행 한다.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
