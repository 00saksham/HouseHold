package com.plumbum.aapu.household;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

import java.util.ArrayList;
import java.util.List;

public class CategoryButtonActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    ViewPager viewPager;
    SQLiteDatabase sqLiteDatabase;
    CategoryImplementation categoryImplementation=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        DBAdapter dbAdapter = null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);

        viewPager = (ViewPager) findViewById(R.id.content_category_button_pager);
        setupViewPager(viewPager);// a method


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoryExpenseFragment(), "EXPENSE");
        adapter.addFragment(new CategorySavingsFragment(), "SAVING");

        viewPager.setAdapter(adapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
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

    public void returnInstance()
    {
        DBAdapter dbAdapter=null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);
    }




}
