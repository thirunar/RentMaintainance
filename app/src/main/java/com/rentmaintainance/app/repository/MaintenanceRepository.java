package com.rentmaintainance.app.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

public abstract class MaintenanceRepository {

    protected Repository masterRepository;


    public void updateMasterRepository(Repository repository) {
        this.masterRepository = repository;
    }

    abstract protected void onCreate(SQLiteDatabase database);

    public SparseArray<SparseArray<String>> content(Cursor cursor) {
        SparseArray<SparseArray<String>> content = new SparseArray<SparseArray<String>>();
        for (int row = 0; row < cursor.getCount(); row++) {

            int numberOfColumns = cursor.getColumnCount();
            SparseArray<String> columnAndValue = new SparseArray<String>();
            for (int column = 0; column < numberOfColumns; column++) {
                int type = cursor.getType(column);
                if (type == Cursor.FIELD_TYPE_STRING) {
                    columnAndValue.append(column, cursor.getString(column));
                } else if (type == Cursor.FIELD_TYPE_FLOAT) {
                    columnAndValue.append(column, Float.toString(cursor.getFloat(column)));
                } else if (type == Cursor.FIELD_TYPE_INTEGER) {
                    columnAndValue.append(column, Integer.toString(cursor.getInt(column)));
                }
            }
            content.put(row, columnAndValue);
        }

        return content;
    }


}
