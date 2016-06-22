package com.plumbum.aapu.household;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

public class CategoriesActivity extends AppCompatActivity implements Runnable {

    CategoryImplementation categoryImplementation;
    private String GET_SAVING_LIST_SQL = "SELECT * FROM CATEGORY WHERE CATEGORY_TYPE='EXPENSE'";
    private String GET_EXPENSE_LIST_SQL ="SELECT * FROM CATEGORY WHERE CATEGORY_TYPE='SAVING'" ;

    private String CATEGORY_ICON ="category_icon";
    private String CATEGORY_NAME ="category_name";

    private Thread THREAD_EXPENSE;
    private Thread THREAD_SAVING;

    private String THREAD_NAME_EXPENSE;
    private String THREAD_NAME_SAVING;


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
        Cursor cursor = categoryImplementation.getInstance().fetchCategory(GET_EXPENSE_LIST_SQL);

        ListView listView = (ListView) findViewById(R.id.content_categories_list_expense);

        String[] from = new String[]{CATEGORY_NAME,CATEGORY_ICON};
        int[] to = new int[] {R.id.category_list_card_view_category_name,R.id.category_list_card_view_category_icon};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.category_list_card_view,cursor,from,to,0);
        listView.setAdapter(simpleCursorAdapter);
    }

    /**
     *  @desc    Use to get Saving List from database and set in XML
     */
    private void getSavingList()
    {
        Cursor cursor = categoryImplementation.getInstance().fetchCategory(GET_SAVING_LIST_SQL);

        ListView listView = (ListView) findViewById(R.id.content_categories_list_saving);

        String[] from = new String[]{CATEGORY_NAME,CATEGORY_ICON};
        int[] to = new int[] {R.id.category_list_card_view_category_name,R.id.category_list_card_view_category_icon};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.category_list_card_view,cursor,from,to,0);
        listView.setAdapter(simpleCursorAdapter);
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
