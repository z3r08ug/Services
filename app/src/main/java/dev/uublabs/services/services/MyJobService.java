package dev.uublabs.services.services;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by Admin on 11/22/2017.
 */

//This is only available from Lollipop onward....
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService
{

    private static final String TAG = MyJobService.class.getSimpleName() + "_TAG";

    @Override
    public boolean onStartJob(JobParameters params)
    {
        //start our service
        Log.d(TAG, "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(), MyStartedService.class);
        startService(intent);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params)
    {
        Log.d(TAG, "onStopJob: ");
        return false;
    }
}
