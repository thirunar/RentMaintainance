package com.rentmaintainance.app.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rentmaintainance.app.model.Income;
import com.rentmaintainance.app.utils.DateUtil;

import static com.rentmaintainance.app.repository.PropertyRepository.KEY_HOUSE_NUMBER;
import static com.rentmaintainance.app.repository.PropertyRepository.TABLE_PROPERTY;

public class IncomeRepository extends MaintainceRepository {

    public static final String TABLE_INCOME = "income";
    public static final String KEY_PROPERTY_ID = "propertyId";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_DATE = "date";
    public static final String KEY_DETAILS = "details";
    public static final String CREATE_PROPERTY = "CREATE TABLE "
            + TABLE_INCOME + "( "
            + KEY_PROPERTY_ID + " TEXT, "
            + KEY_AMOUNT + " FLOAT, "
            + KEY_DETAILS + " TEXT, "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (" + KEY_PROPERTY_ID + ") REFERENCES " + TABLE_PROPERTY + " (" + KEY_HOUSE_NUMBER + ") " + ")";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_PROPERTY);
    }

    public long addIncome(Income income) {
        SQLiteDatabase db = masterRepository.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROPERTY_ID, income.propertyId());
        values.put(KEY_AMOUNT, income.amount());
        values.put(KEY_DATE, DateUtil.formatDateTime(income.date()));
        values.put(KEY_DETAILS, income.details());

        long rowId = db.insert(TABLE_INCOME, null, values);

        if (rowId == -1)
            throw new SQLiteException("Error inserting Property");

        return rowId;
    }
}
