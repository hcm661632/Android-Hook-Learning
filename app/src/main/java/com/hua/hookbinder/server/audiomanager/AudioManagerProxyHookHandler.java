package com.hua.hookbinder.server.audiomanager;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AudioManagerProxyHookHandler implements InvocationHandler {

    private IBinder mBase;
    private Class<?> stub;
    private Class<?> iinterface;


    public AudioManagerProxyHookHandler(IBinder rawAudioManagerBinder) {
        mBase = rawAudioManagerBinder;

        try {
            stub = Class.forName("android.media.IAudioService$Stub");
            iinterface = Class.forName("android.media.IAudioService");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
               new Class[]{iinterface},
               new AudioManagerHookHandler(mBase,stub));

    }
}

