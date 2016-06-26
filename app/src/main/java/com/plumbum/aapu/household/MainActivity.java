package com.plumbum.aapu.household;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.Image.ImageConvertor;
import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

public class MainActivity extends AppCompatActivity implements Runnable {

    byte imageInByte[];
    ImageConvertor imageConvertor;
    CategoryImplementation categoryImplementation = null;
    Resources resources;

    private String THREAD_NAME="HEllo";

    Thread THREAD_HELLO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        THREAD_HELLO  = new Thread(this,THREAD_NAME);
        THREAD_HELLO.start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run()
    {
        hello();
    }

    public void hello(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        DBAdapter dbAdapter=null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);


        ImageConvertor imageConvertor = new ImageConvertor(this);
        imageInByte = imageConvertor.convertImageInByte(R.drawable.money);


        categoryImplementation.getInstance().addCategory("Hello",imageInByte,"Expense");
        Cursor cursor = categoryImplementation.getInstance().fetchCategory("select category_icon as icon from category");

        ImageView imageView = (ImageView) findViewById(R.id.abcd);
        cursor.moveToLast();

        BitmapFactory bitmapFactory = new BitmapFactory();
        byte[] hello = cursor.getBlob(0);


        imageView.setImageBitmap(imageConvertor.convertByteInImage(hello));

    }
}
