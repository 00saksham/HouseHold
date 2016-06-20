package com.plumbum.aapu.household;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);    //Setting Layout for Intro

        /*if (doesDatabaseExist(this) == false)       //Check to see if Database present or not
        {

        }
        else
        {
            Intent intent = new Intent(this,MainActivity.class);    //Calling Main Activity
            startActivity(intent);
        }*/

    }

    private static boolean doesDatabaseExist(Context context)   //Method to check if Database is present or not
    {
        File dbFile = context.getDatabasePath("HouseHold");
        return dbFile.exists();
    }
}
