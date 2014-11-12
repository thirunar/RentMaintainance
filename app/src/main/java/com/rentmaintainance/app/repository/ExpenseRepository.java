package com.rentmaintainance.app.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rentmaintainance.app.model.Expense;
import com.rentmaintainance.app.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import static com.rentmaintainance.app.repository.PropertyRepository.KEY_HOUSE_NUMBER;
import static com.rentmaintainance.app.repository.PropertyRepository.TABLE_PROPERTY;

public class ExpenseRepository extends MaintenanceRepository {

    public static final String TABLE_EXPENSE = "expense";
    public static final String KEY_PROPERTY_ID = "propertyId";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_DATE = "date";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_DETAILS = "details";
    public static final String CREATE_EXPENSE = "CREATE TABLE "
            + TABLE_EXPENSE + "( "
            + KEY_PROPERTY_ID + " TEXT, "
            + KEY_AMOUNT + " FLOAT, "
            + KEY_DETAILS + " TEXT, "
            + KEY_CATEGORY + " TEXT, "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (" + KEY_PROPERTY_ID + ") REFERENCES " + TABLE_PROPERTY + " (" + KEY_HOUSE_NUMBER + ") " + ")";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_EXPENSE);
    }

    public long addExpense(Expense expense) {
        SQLiteDatabase db = masterRepository.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROPERTY_ID, expense.propertyId());
        values.put(KEY_AMOUNT, expense.amount());
        values.put(KEY_DATE, DateUtil.formatDateTime(expense.date()));
        values.put(KEY_DETAILS, expense.details());
        values.put(KEY_CATEGORY, expense.category());

        long rowId = db.insert(TABLE_EXPENSE, null, values);

        if (rowId == -1)
            throw new SQLiteException("Error inserting Property");

        return rowId;
    }

    public List<Expense> getAllExpense() {
        String query = "SELECT * FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = masterRepository.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        return readAllExpense(cursor);
    }

    private List<Expense> readAllExpense(Cursor cursor) {
        ArrayList<Expense> incomes = new ArrayList<Expense>();

        while (!cursor.isAfterLast()) {
            Expense income = new Expense();

            income.withPropertyId(cursor.getString(0));
            income.withAmount(cursor.getFloat(1));
            income.withDetails(cursor.getString(2));
            income.withCategory(cursor.getString(3));
            income.withDate(DateUtil.getDateTime(cursor.getString(4)));
            incomes.add(income);
            cursor.moveToNext();
        }
        cursor.close();

        return incomes;
    }

}
