package com.hua.hookbinder.server.power;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class PowerManagerHookHandler implements InvocationHandler {
    private Object mBase;

    public PowerManagerHookHandler(Object base, Class<?> stubClass) {

        try {
            Method asInterafceMethod = stubClass.getDeclaredMethod("asInterface", IBinder.class);
            this.mBase = asInterafceMethod.invoke(null, base);
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
        if("isPowerSaveMode".equals(method.getName())){
            return true;
        }

        return method.invoke(mBase,args);
    }
}
