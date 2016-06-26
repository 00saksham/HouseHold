package com.plumbum.aapu.household;

import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.plumbum.aapu.household.Implementaion.DebtImplementation;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * @desc  A fragment use to represent Expense Category List In Category Button
 */
public class CategoryExpenseFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link } of the containing
     * Activity's lifecycle.
     */
    String formatDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_category, container, false);

        Button button = (Button) view.findViewById(R.id.button23);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker();

            }
        });

        picker();

        return view;
    }



    public void picker()
    {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                CategoryExpenseFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        FragmentManager fragmentManager = getActivity().getFragmentManager(); // See the IMPORT
        dpd.show(fragmentManager,"Hello");
    }

    public void addData()
    {

        DebtImplementation.getInstance().addDebt(200,"me",null,formatDate,"no",false);
    }

    public void showData() throws ParseException {
        Cursor cursor = DebtImplementation.getInstance().fetchDebt("Select date_to from debt");
        cursor.moveToLast();
        String newDate = cursor.getString(0);

        TextView textView = (TextView) getActivity().findViewById(R.id.hellloo);
        textView.setText(newDate);
    }

    /**
     * @param view        The view associated with this listener.
     * @param year        The year that was set.
     * @param monthOfYear The month that was set (0-11) for compatibility
     *                    with {@link Calendar}.
     * @param dayOfMonth  The day of the month that was set.
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Remember the capital month

        Calendar calender = new GregorianCalendar(year,monthOfYear,dayOfMonth);


        formatDate = simpleDateFormat.format(calender.getTime());


        TextView textView = (TextView) getView().findViewById(R.id.hellloobi);
        TextView textd = (TextView)  getView().findViewById(R.id.hellloobiy);

        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        textView.setText(date);
        textd.setText(formatDate);
        addData();
        try {
            showData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
