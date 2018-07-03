package com.hua.hookbinder.service;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class ServiceHookHelper {


    public static void hookService(){
        //ContextImpl.startService  ---> startServiceCommon
        // ActivityManager.getService().startService,  Proxy.    ActivityManager.getService

        try {
            Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
            Field iActivityManagerSingletonField = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingletonField.setAccessible(true);
            Object IActivityManagerSingleton = iActivityManagerSingletonField.get(null);

            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mSingletonInstanceField = singletonClass.getDeclaredField("mInstance");
            mSingletonInstanceField.setAccessible(true);
            Object rawIActivityManager = mSingletonInstanceField.get(IActivityManagerSingleton);      // use this startService bindService
            Log.d("HHH","rawIActivityManager " + rawIActivityManager.getClass().getName());  //android.app.IActivityManager$Stub$Proxy


            //Proxy IActivityManager
            Class<?> iinterface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(activityManagerClass.getClassLoader(),
                    new Class[]{iinterface},
                    new IActivityManagerHandler(rawIActivityManager)
            );

            mSingletonInstanceField.set(IActivityManagerSingleton,proxy);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

}
