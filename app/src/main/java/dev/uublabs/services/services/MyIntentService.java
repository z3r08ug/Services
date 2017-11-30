package dev.uublabs.services.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService
{
    private static final String TAG = MyIntentService.class.getSimpleName() + "_TAG";

    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate: MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data") + " " +
        Thread.currentThread());
        switch (intent.getAction())
        {
            case "Task1":
                Log.d(TAG, "onHandleIntent: Executing Task1");
                for (int i = 0; i < 5; i++)
                {
                    try
                    {
                        Log.d(TAG, "onHandleIntent: Completion" + i);
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "onHandleIntent: Completed Task1");
                break;
            case "Task2":
                Log.d(TAG, "onHandleIntent: Executing Task2");
                List<String> stringList = new ArrayList<>();
                for (int i = 1; i <= 3; i++)
                {
                    stringList.add("String " + i);
                }
                for (String item:stringList)
                    Log.d(TAG, "onHandleIntent: Success for " + item);
                Log.d(TAG, "onHandleIntent: Completed Task2");
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyIntentService");
    }
}
