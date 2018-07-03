package com.hua.hookbinder.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 可以在 ProxyService 里面把任务转发给真正要启动的插件Service组件，要完成这个过程肯定需要创建一个对应
 * 的插件Service对象, 比如 PluginService；
 */
public class ProxyService extends Service {

    public ProxyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
