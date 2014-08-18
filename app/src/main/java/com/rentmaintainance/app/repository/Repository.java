package com.rentmaintainance.app.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.rentmaintainance.app.model.Property;
import com.rentmaintainance.app.utils.DateUtil;

public class Repository extends SQLiteOpenHelper {
    private static final String LOG = "Repository";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "rentMaintainance";

    private static final String TABLE_PROPERTY = "property";

    private static final String KEY_HOUSE_NUMBER = "houseNumber";
    private static final String KEY_NAME = "name";
    private static final String KEY_RENT = "rent";
    private static final String KEY_ITEMS = "items";
    private static final String KEY_DETAILS = "details";
    private static final String KEY_DATE = "date";

    private static final String CREATE_PROPERTY = "CREATE TABLE "
            + TABLE_PROPERTY + "("
            + KEY_HOUSE_NUMBER + " TEXT PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_RENT + " FLOAT,"
            + KEY_ITEMS + " TEXT,"
            + KEY_DETAILS + " TEXT, "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";


    public Repository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROPERTY);
    }

    public long addPropertyDetails(Property property) throws SQLiteException{
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOUSE_NUMBER, property.getHouseNumber());
        values.put(KEY_NAME, property.getName());
        values.put(KEY_RENT, property.getRent());
        values.put(KEY_ITEMS, property.getItems());
        values.put(KEY_DETAILS, property.getDetails());
        values.put(KEY_DATE, DateUtil.formatDateTime(property.getDate()));

        long rowId = db.insert(TABLE_PROPERTY, KEY_HOUSE_NUMBER, values);

        if (rowId == -1)
            throw new SQLiteException("Error inserting date");

        return rowId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
