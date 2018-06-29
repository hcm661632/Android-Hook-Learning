package com.hua.hookbinder.server.ams_pkms;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

class PMKHookHandler implements InvocationHandler {

    private static final String TAG = "HHH";
    private Object mBase;
    public PMKHookHandler(Object rawIPackageManager) {
        mBase = rawIPackageManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Log.d(TAG, "method:" + method.getName() + " called with args:" + Arrays.toString(args));
        if("getInstalledApplications".equals(method.getName())) {
            Log.d(TAG, " getInstalledApplications will be null");

        }
        return method.invoke(mBase,args);
    }
}
