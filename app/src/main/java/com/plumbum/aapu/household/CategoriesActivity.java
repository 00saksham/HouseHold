package com.plumbum.aapu.household;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.plumbum.aapu.household.Cursors.CategoryActivityCursor;
import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

public class CategoriesActivity extends AppCompatActivity implements Runnable {

    CategoryImplementation categoryImplementation;

    private String GET_SAVING_LIST_SQL = "SELECT _id as _id,CATEGORY_NAME AS CATEGORY_NAME,CATEGORY_ICON AS CATEGORY_ICON FROM CATEGORY WHERE CATEGORY_TYPE='saving';";
    private String GET_EXPENSE_LIST_SQL ="SELECT _id as _id,CATEGORY_NAME AS CATEGORY_NAME,CATEGORY_ICON AS CATEGORY_ICON FROM CATEGORY WHERE CATEGORY_TYPE='expense';";

    private Thread THREAD_EXPENSE;
    private Thread THREAD_SAVING;

    private String THREAD_NAME_EXPENSE="EXPENSE";
    private String THREAD_NAME_SAVING="SAVING";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLiteDatabase  sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        DBAdapter dbAdapter = null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);

        start();    //Use to start everything in the thread


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * @desc    Use to get Expense List from database and set in XML
     */
    private void getExpenseList()
    {
        Cursor cursor = categoryImplementation.getInstance().fetchCategory(GET_EXPENSE_LIST_SQL);

        Log.v("Cursor",Integer.toString(cursor.getCount()));

        CategoryActivityCursor categoryActivityExpenseCursor = new CategoryActivityCursor(this,cursor,0);

        ListView listView = (ListView) findViewById(R.id.content_categories_list_expense);
        listView.setAdapter(categoryActivityExpenseCursor);
    }

    /**
     *  @desc    Use to get Saving List from database and set in XML
     */
    private void getSavingList()
    {
        Cursor cursor = categoryImplementation.getInstance().fetchCategory(GET_SAVING_LIST_SQL);

        Log.v("Cursor Saving",Integer.toString(cursor.getCount()));

        CategoryActivityCursor categoryActivityExpenseCursor = new CategoryActivityCursor(this,cursor,0);

        ListView listView = (ListView) findViewById(R.id.content_categories_list_saving);
        listView.setAdapter(categoryActivityExpenseCursor);
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run()
    {
        if(Thread.currentThread().getName()==THREAD_NAME_EXPENSE)
        {
            getExpenseList();

        }
        if(Thread.currentThread().getName()==THREAD_NAME_SAVING)
        {
            getSavingList();
        }

    }

    /**
     * @desc    Called to start using different threads
     */
    public void start()
    {
        THREAD_EXPENSE = new Thread(this,THREAD_NAME_EXPENSE);
        THREAD_SAVING  = new Thread(this,THREAD_NAME_SAVING);

        THREAD_EXPENSE.start();
        THREAD_SAVING.start();

    }
}
