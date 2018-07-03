package com.hua.hookbinder;

import android.app.Application;
import android.content.Context;

public class HookBinderApplication extends Application {

    private static Context sContext;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = base;
    }

    public static Context getContext(){
        return sContext;
    }
}
