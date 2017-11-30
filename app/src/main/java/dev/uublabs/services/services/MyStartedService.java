package dev.uublabs.services.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Admin on 11/22/2017.
 */

public class MyStartedService extends Service
{
    private static final String TAG = MyStartedService.class.getSimpleName() + "_TAG";

    public  MyStartedService()
    {

    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate: Started Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(TAG, "onStartCommand: " + intent.getStringExtra("data") + " "
                + Thread.currentThread());
        //To stop the service after the task is completed
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        throw new UnsupportedOperationException("Can't bind to MyStartedService");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyStartedService");
    }
}
