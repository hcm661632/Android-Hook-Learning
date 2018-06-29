package com.hua.hookbinder.server.clipboard;

import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class BinderProxyHookHandler implements InvocationHandler{
    private static final String TAG = "HHH";

    IBinder base;
    Class<?> stub;
    Class<?> iinterface;
    public BinderProxyHookHandler(IBinder rawBinder) {
        this.base = rawBinder;

        try {
            stub = Class.forName("android.content.IClipboard$Stub");
            iinterface = Class.forName("android.content.IClipboard");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("queryLocalInterface".equals(method.getName())) {
            Log.d(TAG,"hook queryLocalInterface");
            return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                    new Class[]{iinterface},
                    new BinderHookHandler(base,stub));
        }
        Log.d(TAG, "method:" + method.getName());
        return method.invoke(base, args);
    }
}
