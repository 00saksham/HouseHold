package com.plumbum.aapu.household;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

public class CategoriesActivity extends AppCompatActivity implements Runnable {

    CategoryImplementation categoryImplementation;
    private String GET_SAVING_LIST_SQL = "SELECT * FROM CATEGORY WHERE CATEGORY_TYPE='EXPENSE'";
    private String GET_EXPENSE_LIST_SQL ="SELECT * FROM CATEGORY WHERE CATEGORY_TYPE='SAVING'" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        Cursor cursor;
    }

    /**
     *  @desc    Use to get Saving List from database and set in XML
     */
    private void getSavingList()
    {
        Cursor cursor;
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {

    }

    /**
     * @desc    Called to start using different threads
     */
    public void start()
    {

    }
}
