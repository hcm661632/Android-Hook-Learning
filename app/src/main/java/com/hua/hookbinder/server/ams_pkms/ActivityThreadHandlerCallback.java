package com.hua.hookbinder.server.ams_pkms;

import android.os.Handler;
import android.os.Message;

class ActivityThreadHandlerCallback implements Handler.Callback {
    public ActivityThreadHandlerCallback(Handler mH) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
