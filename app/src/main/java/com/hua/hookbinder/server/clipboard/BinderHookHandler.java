package com.hua.hookbinder.server.clipboard;

import android.content.ClipData;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class BinderHookHandler implements InvocationHandler{

    private static final String TAG = "BinderHookHandler";

    Object base;
    public BinderHookHandler(IBinder base,Class<?> stubClass) {
        try {
            Method asInterafceMethod = stubClass.getDeclaredMethod("asInterface", IBinder.class);
            this.base = asInterafceMethod.invoke(null, base);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if("getPrimaryClip".equals(method.getName())) {

            Log.d(TAG,"hook getPrimaryClip");
            return ClipData.newPlainText(null,"You ard Hooked");
        }

        if("hasPrimaryClip".equals(method.getName())) {

            return true;
        }

        return method.invoke(base,args);
    }
}
