package dev.uublabs.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import dev.uublabs.services.Model.Book;
import dev.uublabs.services.services.MyBoundService;
import dev.uublabs.services.services.MyIntentService;
import dev.uublabs.services.services.MyJobService;
import dev.uublabs.services.services.MyStartedService;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    boolean isBound;
    MyBoundService myBoundService;
    ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) service;
            myBoundService = myBinder.getService();
            isBound = true;
            myBoundService.initData();
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startServices(View view)
    {
        Intent startedIntent = new Intent(this, MyStartedService.class);
        Intent intentIntent = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this, MyBoundService.class);

        switch (view.getId())
        {
            case R.id.btn_started_service:
                startedIntent.putExtra("data", "This is a started service");
                startService(startedIntent);
                break;
            case R.id.btn_stop_started_service:
                stopService(startedIntent);
                break;
            case R.id.btn_started_intent_service:
                intentIntent.putExtra("data", "This is an intent service.");
                intentIntent.setAction("Task1");
                startService(intentIntent);
                break;
            case R.id.btn_started_intent_service_task2:
                intentIntent.putExtra("data", "This is an intent service.");
                intentIntent.setAction("Task2");
                startService(intentIntent);
                break;
            case R.id.btn_bind_service:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_bound_service_get_books:
                if (isBound)
                {
                    List<Book> books = myBoundService.getBooks();
                    for (Book book:books)
                    {
                        Log.d(TAG, "startServices: " + book);
                    }
                }
                break;
            case R.id.btn_bound_service_add_book:
                if (isBound)
                {
                    int random = new Random().nextInt(10);
                    Book book = new Book("Harry Potter " + String.valueOf(random), "Fantasy");
                    boolean wasBookAdded = myBoundService.addBook(book);
                    if (wasBookAdded)
                    {
                        Toast.makeText(myBoundService, "Book Added", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case  R.id.btn_unbind_service:
                if (isBound)
                {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case R.id.btn_schedule_service:
                ComponentName componentName = new ComponentName(this, MyJobService.class);
                JobInfo jobInfo = new JobInfo.Builder(0, componentName)
                        .setMinimumLatency(1000)
                        .setOverrideDeadline(5000)
                        .build();
                JobScheduler jobScheduler = getSystemService(JobScheduler.class);
                jobScheduler.schedule(jobInfo);
                break;
        }
    }
}
