package com.plumbum.aapu.household;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DebtsLoanActivity extends AppCompatActivity implements Runnable{

    private String GET_DEBT_LIST_SQL = "SELECT * FROM DEBT";
    private String GET_LOAN_LIST_SQL ="SELECT * FROM LOAN" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts_loan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        start();

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
     * @desc     Use to get Loan List from database and set in XML
     */
    private void getLoanList()
    {

    }

    /**
     * @desc     Use to get Debt List from database and set in XML
     */
    private void getDebtList()
    {

    }


    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run()
    {

    }

    /**
     * @desc    Called to start using different threads
     */
    private void start()
    {

    }
}
