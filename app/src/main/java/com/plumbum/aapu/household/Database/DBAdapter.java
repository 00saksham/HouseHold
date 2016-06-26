package com.plumbum.aapu.household.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.plumbum.aapu.household.VO.CategoryVO;

/**
 * Created by Dawn on 6/18/2016.
 */
public class DBAdapter extends SQLiteOpenHelper
{

    private static DBAdapter instance;

    private static final String DATABASE_NAME = "HouseHold";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS ";
    private static final String DROP = "DROP TABLE IF EXISTS ";

    private static final String VARCHAR_20 = " VARCHAR(20),";
    private static final String VARCHAR_100 = " VARCHAR(100),";
    private static final String VARCHAR_20_NOT_NULL = " VARCHAR(20) NOT NULL ,";
    private static final String BOOLEAN_NOT_NULL = " BOOLEAN NOT NULL";
    private static final String FLOAT_NOT_NULL = " FLOAT NOT NULL,";
    private static final String BLOB_NOT_NULL = " BLOB NOT NULL";
    private static final String DATE = " DATE,";
    private static final String DATE_NOT_NULL = " DATE NOT NULL,";
    private static final String ID = " _ID INTEGER PRIMARY KEY AUTOINCREMENT ,";

    private static final String CATEGORY_TABLE_NAME = "CATEGORY";
    private static final String DEBT_TABLE_NAME = "DEBT";
    private static final String LOAN_TABLE_NAME = "LOAN";
    private static final String TRANSACTIONS_TABLE_NAME = "TRANSACTIONS";

    private static final String CATEGORY_COLUMN_CATEGORY_NAME = "CATEGORY_NAME ";
    private static final String CATEGORY_COLUMN_CATEGORY_ICON = "CATEGORY_ICON ";
    private static final String CATEGORY_COLUMN_CATEGORY_TYPE = "CATEGORY_TYPE ";

    private static final String DEBT_COLUMN_SUM = "SUM ";
    private static final String DEBT_COLUMN_BORROWER = "BORROWER ";
    private static final String DEBT_COLUMN_DATE_FROM = "DATE_FROM ";
    private static final String DEBT_COLUMN_DATE_TO = "DATE_TO ";
    private static final String DEBT_COLUMN_REMARKS = "REMARKS ";
    private static final String DEBT_COLUMN_EXCLUDE = "EXCLUDE ";

    private static final String LOAN_COLUMN_SUM = "SUM ";
    private static final String LOAN_COLUMN_LENDER = "LENDER ";
    private static final String LOAN_COLUMN_DATE_FROM = "DATE_FROM ";
    private static final String LOAN_COLUMN_DATE_TO = "DATE_TO ";
    private static final String LOAN_COLUMN_REMARKS = "REMARKS ";
    private static final String LOAN_COLUMN_EXCLUDE = "EXCLUDE ";

    private static final String TRANSACTION_COLUMN_SUM = "SUM ";
    private static final String TRANSACTION_COLUMN_DATE = "TRANSACTION_DATE ";
    private static final String TRANSACTION_COLUMN_REMARKS = "REMARKS ";
    private static final String TRANSACTION_COLUMN_CATEGORY_NAME = "CATEGORY_NAME ";
    private static final String TRANSACTION_COLUMN_CATEGORY_ICON = "CATEGORY_ICON ";



    /*
     *@desc - A private Constructor
     */

    private DBAdapter(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @desc -  Called when we need to generate a single insance of DBApapter
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     *
     */

    public static synchronized DBAdapter getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (instance == null) {
            instance = new DBAdapter(context);
        }
        return instance;
    }

    /**
     * @desc -  Called when the database is created for the first time. This is where the
     *          creation of tables and the initial population of the tables should happen.
     *
     * @param   db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CATEGORY_TABLE = CREATE + CATEGORY_TABLE_NAME + "(" + ID +
                                       CATEGORY_COLUMN_CATEGORY_NAME +""+ VARCHAR_20_NOT_NULL +
                                       CATEGORY_COLUMN_CATEGORY_TYPE +""+ VARCHAR_20_NOT_NULL +
                                       CATEGORY_COLUMN_CATEGORY_ICON +""+ BLOB_NOT_NULL  + ");";

        String CREATE_DEBT_TABLE = CREATE + DEBT_TABLE_NAME + "(" + ID +
                                   DEBT_COLUMN_SUM + FLOAT_NOT_NULL + DEBT_COLUMN_BORROWER +
                                   VARCHAR_20 + DEBT_COLUMN_DATE_FROM + DATE + DEBT_COLUMN_DATE_TO + DATE +
                                   DEBT_COLUMN_REMARKS + VARCHAR_100 + DEBT_COLUMN_EXCLUDE + BOOLEAN_NOT_NULL +");";

        String CREATE_LOAN_TABLE = CREATE + LOAN_TABLE_NAME + "(" + ID +
                                   LOAN_COLUMN_SUM + FLOAT_NOT_NULL + LOAN_COLUMN_LENDER + VARCHAR_20 +
                                   LOAN_COLUMN_DATE_FROM + DATE + LOAN_COLUMN_DATE_TO + DATE +
                                   LOAN_COLUMN_REMARKS + VARCHAR_100 + LOAN_COLUMN_EXCLUDE + BOOLEAN_NOT_NULL +");";

        String CREATE_TRANSACTION_TABLE = CREATE + TRANSACTIONS_TABLE_NAME + " (" + ID +
                                          TRANSACTION_COLUMN_SUM + FLOAT_NOT_NULL + TRANSACTION_COLUMN_DATE + DATE +
                                          TRANSACTION_COLUMN_REMARKS + VARCHAR_100 + TRANSACTION_COLUMN_CATEGORY_NAME +
                                          VARCHAR_20_NOT_NULL + TRANSACTION_COLUMN_CATEGORY_ICON +BLOB_NOT_NULL + ");";

        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_DEBT_TABLE);
        db.execSQL(CREATE_LOAN_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);

    }

    /**
     * Called when the database needs to be upgraded.
     *
     * @param   db         The database.
     * @param   oldVersion The old database version.
     * @param   newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP+CATEGORY_TABLE_NAME);
        db.execSQL(DROP+DEBT_TABLE_NAME);
        db.execSQL(DROP+LOAN_TABLE_NAME);
        db.execSQL(DROP+TRANSACTIONS_TABLE_NAME);

        onCreate(db);

    }

    /**
     * @desc    Use to add *something* to Database
     * @param   query - SQL query
     */

    public void anyQuery(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);
    }


    /**
     * @desc    Use to fetch result set from Database
     * @param   query - SQL query without ;
     * @return  cursor - A result set
     */
    public Cursor fetchQuery(String query)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(query,null);
    }

    public void deleteDatabase(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query);
    }


    public void insertCategory(CategoryVO categoryVO)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_COLUMN_CATEGORY_NAME,categoryVO.getCategory_name());
        contentValues.put(CATEGORY_COLUMN_CATEGORY_TYPE,categoryVO.getCategory_type());
        contentValues.put(CATEGORY_COLUMN_CATEGORY_ICON,categoryVO.getCategory_icon());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.insert(CATEGORY_TABLE_NAME,null,contentValues);
    }

}


