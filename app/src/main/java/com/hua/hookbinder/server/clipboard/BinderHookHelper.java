package com.hua.hookbinder.server.clipboard;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;

import com.hua.hookbinder.server.audiomanager.AudioManagerProxyHookHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class BinderHookHelper {

    public static void hookClipboardService(){

        final String CLIPBOARD_SERVICE = "clipboard";

        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method getService = serviceManager.getDeclaredMethod("getService", String.class);
            IBinder rawBinder = (IBinder) getService.invoke(null, CLIPBOARD_SERVICE);


            IBinder hookedBinder = (IBinder) Proxy.newProxyInstance(serviceManager.getClassLoader(),
                    new Class<?>[]{IBinder.class},
                    new BinderProxyHookHandler(rawBinder));


            Field cacheField = serviceManager.getDeclaredField("sCache");
            cacheField.setAccessible(true);
            Map<String,IBinder> cache = (Map<String, IBinder>) cacheField.get(null);
            cache.put(CLIPBOARD_SERVICE,hookedBinder);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }


    // Hook AudioManager
    public static void hookAudioManager(){

        Class<?> serviceManager = null;
        try {
            serviceManager = Class.forName("android.os.ServiceManager");
            Method getService = serviceManager.getDeclaredMethod("getService", String.class);
            IBinder rawAudioManagerBinder = (IBinder) getService.invoke(null, Context.AUDIO_SERVICE);

            IBinder hookIPowerBinder =(IBinder) Proxy.newProxyInstance(serviceManager.getClassLoader(),
                    new Class[]{IBinder.class},
                    new AudioManagerProxyHookHandler(rawAudioManagerBinder));


            Field cacheField = serviceManager.getDeclaredField("sCache");
            cacheField.setAccessible(true);
            Map<String,IBinder> cache = (Map<String, IBinder>) cacheField.get(null);
            cache.put(Context.AUDIO_SERVICE,(IBinder) hookIPowerBinder);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }












}
