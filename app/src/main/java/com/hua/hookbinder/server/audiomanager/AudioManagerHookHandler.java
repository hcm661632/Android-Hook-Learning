package com.hua.hookbinder.server.audiomanager;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AudioManagerHookHandler implements InvocationHandler {
    private Object mBase;
    public AudioManagerHookHandler(IBinder base, Class<?> stub) {
        mBase = base;
        try {
            Method asInterfaceMethod = stub.getDeclaredMethod("asInterface", IBinder.class);
            mBase = asInterfaceMethod.invoke(null,base);
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

        if("getStreamMaxVolume".equals(method.getName())){
            return 2;
        }

        return method.invoke(mBase,args);
    }
}
