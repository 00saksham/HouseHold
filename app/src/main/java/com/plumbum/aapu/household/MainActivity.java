package com.plumbum.aapu.household;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.Image.ImageConvertor;
import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable {

    byte imageInByte[];
    ImageConvertor imageConvertor;
    CategoryImplementation categoryImplementation = null;
    Resources resources;

    private String THREAD_NAME="HEllo";
    ViewPager viewPager;
    TabLayout tabLayout;

    Thread THREAD_HELLO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        DBAdapter dbAdapter = null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);

        viewPager = (ViewPager) findViewById(R.id.content_main_viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddTransactionActivity.class);
                startActivity(intent);
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

    }

    public void hello(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        DBAdapter dbAdapter=null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HistoryFragment(),"HISTORY");
        adapter.addFragment(new TransactionFragment(), "TRANSACTION");
        adapter.addFragment(new FutureFragment(), "FUTURE");

        viewPager.setAdapter(adapter);

        adapter.getItem(1);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
