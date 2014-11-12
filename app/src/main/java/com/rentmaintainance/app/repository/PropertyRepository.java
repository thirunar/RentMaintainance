package com.rentmaintainance.app.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rentmaintainance.app.model.Property;
import com.rentmaintainance.app.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import static com.rentmaintainance.app.repository.TenantRepository.KEY_ID;
import static com.rentmaintainance.app.repository.TenantRepository.TABLE_TENANT;

public class PropertyRepository extends MaintenanceRepository {

    public static final String TABLE_PROPERTY = "property";
    public static final String KEY_HOUSE_NUMBER = "houseNumber";
    public static final String KEY_NAME = "name";
    public static final String KEY_RENT = "rent";
    public static final String KEY_ITEMS = "items";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_DATE = "dateAdded";
    public static final String KEY_TENANT = "tenantId";
    public static final String CREATE_PROPERTY = "CREATE TABLE "
            + TABLE_PROPERTY + "( "
            + KEY_HOUSE_NUMBER + " TEXT PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_RENT + " FLOAT, "
            + KEY_ITEMS + " TEXT, "
            + KEY_DETAILS + " TEXT, "
            + KEY_ADDRESS + " TEXT, "
            + KEY_TENANT + " TEXT, "
            + KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (" + KEY_TENANT + ") REFERENCES " + TABLE_TENANT + " (" + KEY_ID + ") " + ")";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_PROPERTY);
    }

    public long addPropertyDetails(Property property) throws SQLiteException {
        SQLiteDatabase db = masterRepository.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOUSE_NUMBER, property.getHouseNumber());
        values.put(KEY_NAME, property.getName());
        values.put(KEY_RENT, property.getRent());
        values.put(KEY_ITEMS, property.getItems());
        values.put(KEY_DETAILS, property.getDetails());
        values.put(KEY_ADDRESS, property.getAddress());
        values.put(KEY_DATE, DateUtil.formatDateTime(property.getDate()));
        values.put(KEY_TENANT, property.getTenantId());

        long rowId = db.insert(TABLE_PROPERTY, KEY_HOUSE_NUMBER, values);

        if (rowId == -1)
            throw new SQLiteException("Error inserting Property");

        return rowId;
    }

    public long updatePropertyDetails(Property property) {
        SQLiteDatabase db = masterRepository.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, property.getName());
        values.put(KEY_RENT, property.getRent());
        values.put(KEY_ITEMS, property.getItems());
        values.put(KEY_DETAILS, property.getDetails());
        values.put(KEY_ADDRESS, property.getAddress());

        return db.update(TABLE_PROPERTY, values, KEY_HOUSE_NUMBER + " = ?", new String[]{property.getHouseNumber()});
    }

    public List<Property> getAllProperties() {
        String query = "SELECT * FROM " + TABLE_PROPERTY;
        SQLiteDatabase db = masterRepository.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        return readAllProperties(cursor);
    }

    private List<Property> readAllProperties(Cursor cursor) {
        ArrayList<Property> properties = new ArrayList<Property>();

        while (!cursor.isAfterLast()) {
            Property property = new Property();
            property.setHouseNumber(cursor.getString(0));
            property.setName(cursor.getString(1));
            property.setRent(cursor.getFloat(2));
            property.setItems(cursor.getString(3));
            property.setDetails(cursor.getString(4));
            property.setAddress(cursor.getString(5));
            property.setDate(DateUtil.getDateTime(cursor.getString(7)));
            properties.add(property);
            cursor.moveToNext();
        }
        cursor.close();
        return properties;
    }
}
