package com.plumbum.aapu.household;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.Image.ImageConvertor;
import com.plumbum.aapu.household.Implementaion.CategoryImplementation;
import com.plumbum.aapu.household.Implementaion.TransactionImplementation;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTransactionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText editTextAmount ;
    EditText editTextRemarks;
    TextView textViewCategory;
    TextView textViewDate;

    final int requestCode=1;
    String category_type;
    String category_name;
    String remarks;
    double amount;
    String format_date;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("HouseHold",MODE_PRIVATE,null);
        DBAdapter dbAdapter = null;
        dbAdapter.getInstance(this).onCreate(sqLiteDatabase);



        editTextAmount = (EditText) findViewById(R.id.content_add_transaction_edit_amount);
        editTextRemarks = (EditText) findViewById(R.id.content_add_transaction_remarks);
        textViewCategory = (TextView) findViewById(R.id.content_add_transaction_category_name);
        textViewDate = (TextView) findViewById(R.id.content_add_transaction_date);


        textViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTransactionActivity.this,CategoryButtonActivity.class);
                startActivityForResult(intent,requestCode);
            }
        });
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String textDate = df.format(calendar.getTime());

        textViewDate.setText(textDate);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaction();
                Intent intent = new Intent(AddTransactionActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void picker(View view)
    {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                AddTransactionActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        FragmentManager fragmentManager = getFragmentManager(); // See the IMPORT
        dpd.show(fragmentManager,"Hello");
    }


    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int default_value = 0;
        int index=0;

        if (this.requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                index = data.getIntExtra("Index",default_value);
            }
        }

        getCategory(index);
    }


    private void getCategory(int id)
    {
        CategoryImplementation categoryImplementation = null;
        String SQL = "Select CATEGORY_NAME as CATEGORY_NAME,CATEGORY_ICON as CATEGORY_ICON,CATEGORY_TYPE AS CATEGORY_TYPE FROM CATEGORY where _ID="+id+";";

        ImageConvertor imageConvertor = new ImageConvertor(this);

        ImageView imageView = (ImageView) findViewById(R.id.content_add_transaction_category_icon);
        textViewCategory = (TextView) findViewById(R.id.content_add_transaction_category_name);

        Cursor cursor = categoryImplementation.getInstance().fetchCategory(SQL);
        cursor.moveToFirst();

        bitmap = imageConvertor.convertByteInImage(cursor.getBlob(cursor.getColumnIndexOrThrow("CATEGORY_ICON")));

        category_name = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY_NAME"));
        category_type = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY_TYPE"));

        imageView.setImageBitmap(bitmap);
        textViewCategory.setText(category_name);


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


        format_date = simpleDateFormat.format(calender.getTime());

        TextView textView = (TextView) findViewById(R.id.content_add_transaction_date);
        textView.setText(format_date);
    }


    public void addTransaction()
    {
        ImageConvertor imageConvertor = new ImageConvertor(this);
        byte[] imageByte = imageConvertor.convertBitmapInByte(bitmap);
        amount = Double.parseDouble(editTextAmount.getText().toString());
        remarks = editTextRemarks.getText().toString();

        TransactionImplementation transactionImplementation = null;

        transactionImplementation.getInstance().addTransaction(amount,format_date,remarks,imageByte,category_name,category_type);
    }
}
