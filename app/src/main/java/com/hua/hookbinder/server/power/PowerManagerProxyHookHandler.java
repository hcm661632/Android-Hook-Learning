package com.hua.hookbinder.server.power;

import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class PowerManagerProxyHookHandler implements InvocationHandler {
    private Object mBase;
    Class<?> stub;
    Class<?> iinterface;
    public PowerManagerProxyHookHandler(IBinder rawPowerManagerService) {
        mBase = rawPowerManagerService;

        try {
            stub = Class.forName("android.os.IPowerManager$Stub");
            iinterface = Class.forName("android.os.IPowerManager");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 这一步可有可无， 貌似是多余的操作
        /*if("queryLocalInterface".equals(method.getName())) {
            Log.d("HHH","PowerManagerProxyHookHandler queryLocalInterface");
        }*/


        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                new Class[]{iinterface},
                new PowerManagerHookHandler(mBase,stub)
                );


    }
}
