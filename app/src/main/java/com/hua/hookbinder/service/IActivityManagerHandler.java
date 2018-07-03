package com.hua.hookbinder.service;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;

import com.hua.hookbinder.HookBinderApplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

class IActivityManagerHandler implements InvocationHandler {
    private Object mActivityManager;
    public IActivityManagerHandler(Object rawIActivityManager) {
            mActivityManager = rawIActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /* Android 8.0
            public ComponentName startService(IApplicationThread caller, Intent service,
                String resolvedType, boolean requireForeground, String callingPackage, int userId)
            throws TransactionTooLargeException
        */
        if("startService".equals(method.getName())){

            Log.d("HHH","IActivityManagerHandler startService" + Arrays.toString(args));
            // Find the first Intent in args
            Pair<Integer,Intent> integerIntentPair = foundFirstIntentOfArgs(args);
            Intent newIntent = new Intent();

            //代理service的包名,也就是我们自己的包名
            String stubPackage = HookBinderApplication.getContext().getPackageName();
            ComponentName componentName = new ComponentName(stubPackage,ProxyService.class.getName());

            newIntent.setComponent(componentName);
            // 把我们原始要启动的TargetService先存起来
            newIntent.putExtra("extra_target_intent",integerIntentPair.second);
            // 替换掉Intent, 达到欺骗AMS的目的
            args[integerIntentPair.first] = newIntent;

            return method.invoke(mActivityManager,args);
        } else if("stopService".equals(method.getName())) {
            Log.d("HHH","IActivityManagerHandler stopService" + Arrays.toString(args));
            return method.invoke(mActivityManager,args);
        }

        return method.invoke(mActivityManager, args);
    }





    private Pair<Integer,Intent> foundFirstIntentOfArgs(Object...args){
        int index = 0;

        for(int i = 0;i<args.length;i++) {
            if(args[i] instanceof Intent) {
                index = i;
                break;
            }
        }
        return Pair.create(index,(Intent)args[index]);

    }


}
