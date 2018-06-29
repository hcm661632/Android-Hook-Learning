package com.hua.hookbinder;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hua.hookbinder.server.ams_pkms.PMSHookHelper;
import com.hua.hookbinder.server.audiomanager.AudioManagerProxyHookHandler;
import com.hua.hookbinder.server.clipboard.BinderHookHelper;
import com.hua.hookbinder.server.power.PowerBinderHookHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
       // BinderHookHelper.hookClipboardService();

       // getInstallApplication(getBaseContext());
      // PMSHookHelper.hookPackageManager2(this);                 // OK
      //  PowerBinderHookHelper.hookPowerManagerService();        // OK
        BinderHookHelper.hookAudioManager();                    // OK

    }

    @SuppressLint("WrongConstant")
    private void getInstallApplication(Context ctx) {

        PackageManager pm = ctx.getPackageManager();
        List<ApplicationInfo> installedApplications = pm.getInstalledApplications(PackageManager.MATCH_ALL);
        for(int i = 0;i<installedApplications.size();i++) {
            Log.d("HHH","Install app " + i + "  " + installedApplications.get(i).processName);
        }


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    @Override
    protected void onStart() {
        super.onStart();
        ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        am.getRunningServices(10);



        ClipboardManager cp = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

    }

    public void getPackageList(View view) {
//        getInstallApplication(view.getContext());

//        PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);
//        boolean isPowerSaveMode = pm.isPowerSaveMode();
//        Log.d("HHH","isPowerSaveMode " + isPowerSaveMode);

        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int streamAlarmMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        Log.d("HHH","streamAlarmMaxVolume = " + streamAlarmMaxVolume);
    }
}
