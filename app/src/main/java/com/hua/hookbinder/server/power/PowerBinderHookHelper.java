package com.hua.hookbinder.server.power;

import android.content.Context;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class PowerBinderHookHelper {


    public static void hookPowerManagerService(){

        Class<?> serviceManager = null;
        try {
            serviceManager = Class.forName("android.os.ServiceManager");
            Method getService = serviceManager.getDeclaredMethod("getService", String.class);
            IBinder rawPowerManagerService = (IBinder) getService.invoke(null, Context.POWER_SERVICE);

            Object hookIPowerBinder = Proxy.newProxyInstance(serviceManager.getClassLoader(),
                    new Class[]{IBinder.class},
                    new PowerManagerProxyHookHandler(rawPowerManagerService));


            Field cacheField = serviceManager.getDeclaredField("sCache");
            cacheField.setAccessible(true);
            Map<String,IBinder> cache = (Map<String, IBinder>) cacheField.get(null);
            cache.put(Context.POWER_SERVICE,(IBinder) hookIPowerBinder);


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
