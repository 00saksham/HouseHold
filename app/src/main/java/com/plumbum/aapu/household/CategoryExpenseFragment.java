package com.plumbum.aapu.household;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.plumbum.aapu.household.Cursors.CategoryActivityCursor;
import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

import java.sql.Date;

/**
 * @desc  A fragment use to represent Expense Category List In Category Button
 */
public class CategoryExpenseFragment extends Fragment {


    private String GET_EXPENSE_LIST_SQL ="SELECT _id as _id,CATEGORY_NAME AS CATEGORY_NAME,CATEGORY_ICON AS CATEGORY_ICON FROM CATEGORY WHERE CATEGORY_TYPE='expense';";

    CategoryImplementation categoryImplementation = null;


    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link } of the containing
     * Activity's lifecycle.
     */
    Date date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_category, container, false);
        return view;
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getExpenseList();

        ListView listView = (ListView) getView().findViewById(R.id.fragment_expense_category_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                cursor.moveToPosition(position);
                int index= cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                Intent data = new Intent();
                data.putExtra("Index",index);
                getActivity().setResult(Activity.RESULT_OK,data);
                getActivity().finish();
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

        CategoryActivityCursor categoryActivityExpenseCursor = new CategoryActivityCursor(this.getContext(),cursor,0);

        ListView listView = (ListView) getView().findViewById(R.id.fragment_expense_category_list);
        listView.setAdapter(categoryActivityExpenseCursor);
    }



}
