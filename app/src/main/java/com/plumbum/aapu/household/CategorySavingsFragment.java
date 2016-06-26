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

/**
 * @desc  A fragment use to represent Saving Category List In Category Button
 */
public class CategorySavingsFragment extends Fragment {


    private String GET_SAVING_LIST_SQL = "SELECT _id as _id,CATEGORY_NAME AS CATEGORY_NAME,CATEGORY_ICON AS CATEGORY_ICON FROM CATEGORY WHERE CATEGORY_TYPE='saving';";

    CategoryImplementation categoryImplementation = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savings_category, container, false);
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

        getSavingList();

        ListView listView1 = (ListView) getView().findViewById(R.id.fragment_savings_category_list);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
     *  @desc    Use to get Saving List from database and set in XML
     */
    private void getSavingList()
    {
        Cursor cursor = categoryImplementation.getInstance().fetchCategory(GET_SAVING_LIST_SQL);

        Log.v("Cursor Saving",Integer.toString(cursor.getCount()));

        CategoryActivityCursor categoryActivityExpenseCursor = new CategoryActivityCursor(this.getContext(),cursor,0);

        ListView listView = (ListView) getView().findViewById(R.id.fragment_savings_category_list);
        listView.setAdapter(categoryActivityExpenseCursor);
    }


}
