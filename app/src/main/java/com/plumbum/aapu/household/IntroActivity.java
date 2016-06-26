package com.plumbum.aapu.household;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.Database.Initialize;
import com.plumbum.aapu.household.Image.ImageConvertor;
import com.plumbum.aapu.household.Implementaion.UserImplementation;

import java.io.File;

public class IntroActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    SQLiteDatabase sqLiteDatabase;
    DBAdapter dbAdapter = null;
    UserImplementation userImplementation=null;
    ImageConvertor imageConvertor;
    Initialize initialize;

    String userName;
    double userStartingBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (doesDatabaseExist(this) == false)       //Check to see if Database present or not
        {
            setContentView(R.layout.activity_intro);    //Setting Layout for Intro
        }
        else
        {
            startMainActivity(); //Internal Method to Intro Activity
        }

    }

    /**
     * @param context - Current activity Context
     * @return a boolean
     * @desc Method to check if Database is present or not
     */

    private static boolean doesDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath("HouseHold");
        return dbFile.exists();
    }


    /**
     * @param view - View which calls this method
     * @desc Use to set User Details in Shared Preference File - "UserDetails"
     */
    public void setUserDetails(View view) {
        EditText editTextName = (EditText) findViewById(R.id.activity_intro_user_name);
        EditText editTextSum = (EditText) findViewById(R.id.activity_intro_user_starting_balance);


        if (editTextSum.getText().toString().length() != 0) {
            userStartingBalance = Double.valueOf(editTextSum.getText().toString());
        } else {
            userStartingBalance = 0;
        }

        userName = editTextName.getText().toString();

        if (userName.length() != 0) {


            if (editTextSum.getText().toString().length() != 0)
            {
                initializeDatabase();
            } else {
                Toast.makeText(getApplicationContext(), "Please Enter an Amount",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter User Name",
                    Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @desc    Use to Initialize Database
     */
    private void initializeDatabase()
    {
        sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);

        imageConvertor = new ImageConvertor(this);
        initialize = new Initialize();

        userImplementation.getInstance().addUser(userName,userStartingBalance);

        String expense_array[] = getResources().getStringArray(R.array.category_expense);
        String saving_array[]  = getResources().getStringArray(R.array.category_saving);
        String extra_array[]   = getResources().getStringArray(R.array.Extra);

        byte[][] imageBytes = imageConvertor.getAllImages();

        initialize.initializeExpenseCategory(expense_array,imageBytes);
        initialize.initializeSavingCategory(saving_array,imageBytes);
        initialize.initializeExtra(extra_array,imageBytes);

        startMainActivity();
    }

    /**
     * @desc    Use to Start Main Activity
     */
    private void startMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
