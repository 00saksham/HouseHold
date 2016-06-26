package com.plumbum.aapu.household;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plumbum.aapu.household.Implementaion.DebtImplementation;

/**
 * @desc  A fragment use to represent Saving Category List In Category Button
 */
public class CategorySavingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savings_category, container, false);


        return view;
    }


}
