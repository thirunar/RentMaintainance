package com.rentmaintainance.app.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.SparseArray;
import com.rentmaintainance.app.model.Income;
import com.rentmaintainance.app.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import static com.rentmaintainance.app.repository.PropertyRepository.KEY_HOUSE_NUMBER;
import static com.rentmaintainance.app.repository.PropertyRepository.TABLE_PROPERTY;

public class IncomeRepository extends MaintenanceRepository {

    public static final String TABLE_INCOME = "income";
    public static final String KEY_PROPERTY_ID = "propertyId";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_DATE = "date";
    public static final String KEY_DETAILS = "details";
    public static final String CREATE_INCOME = "CREATE TABLE "
            + TABLE_INCOME + "( "
            + KEY_PROPERTY_ID + " TEXT, "
            + KEY_AMOUNT + " FLOAT, "
            + KEY_DETAILS + " TEXT, "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (" + KEY_PROPERTY_ID + ") REFERENCES " + TABLE_PROPERTY + " (" + KEY_HOUSE_NUMBER + ") " + ")";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_INCOME);
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

    public List<Income> getAllIncome() {
        Cursor cursor = getCursorForAllIncome();
        return readAllIncome(cursor);
    }

    private Cursor getCursorForAllIncome() {
        String query = "SELECT * FROM " + TABLE_INCOME;
        SQLiteDatabase db = masterRepository.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        return cursor;
    }

    public SparseArray<SparseArray<String>> getAllIncomeData() {
        return content(getCursorForAllIncome());
    }

    private List<Income> readAllIncome(Cursor cursor) {
        ArrayList<Income> incomes = new ArrayList<Income>();

        while (!cursor.isAfterLast()) {
            Income income = new Income();

            income.withPropertyId(cursor.getString(0));
            income.withAmount(cursor.getFloat(1));
            income.withDetails(cursor.getString(2));
            income.withDate(DateUtil.getDateTime(cursor.getString(3)));
            incomes.add(income);
            cursor.moveToNext();
        }
        cursor.close();

        return incomes;
    }
}
