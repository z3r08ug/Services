package dev.uublabs.services.services;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.uublabs.services.Model.Book;

/**
 * Created by Admin on 11/22/2017.
 */

public class MyBoundService extends Service
{
    private static final String TAG = MyBoundService.class.getSimpleName() + "_TAG";
    List<Book> books;
    IBinder iBinder = new MyBinder();

    public MyBoundService()
    {

    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate: MyBoundService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(TAG, "onBind: MyBoundService");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.d(TAG, "onUnbind: MyBoundService");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyBoundService");
    }

    public void initData()
    {
        books = new ArrayList<>();
        books.add(new Book("TLOTR", "Fantasy"));
        books.add(new Book("The Hobbit", "Fantasy"));
        books.add(new Book("Eragon", "Fantasy"));
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public boolean addBook(Book book)
    {
        return books.add(book);
    }

    public class MyBinder extends Binder
    {
        public MyBoundService getService()
        {
            return MyBoundService.this;
        }
    }
}
