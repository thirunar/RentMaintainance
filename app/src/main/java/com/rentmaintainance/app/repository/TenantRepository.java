package com.rentmaintainance.app.repository;

import android.database.sqlite.SQLiteDatabase;

import static com.rentmaintainance.app.repository.PropertyRepository.KEY_HOUSE_NUMBER;
import static com.rentmaintainance.app.repository.PropertyRepository.TABLE_PROPERTY;

public class TenantRepository extends MaintainceRepository {

    public static final String TABLE_TENANT = "tenant";

    public final String KEY_ID = "id";
    public final String KEY_NAME = "name";
    public final String KEY_NUMBER_OF_OCCUPANTS = "numberOfOccupants";
    public final String KEY_ITEMS = "items";
    public final String KEY_STATUS = "status";
    public final String KEY_PROPERTY_ID = "propertyId";
    public final String KEY_DATE_OCCUPIED = "dateOccupied";
    public final String KEY_DATE_VACATED = "dateVacated";
    public final String KEY_PHONE_NUMBER = "phoneNumber";
    public final String CREATE_TENANT = "CREATE TABLE "
            + TABLE_TENANT + "("
            + KEY_ID + " TEXT PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_NUMBER_OF_OCCUPANTS + " INTEGER, "
            + KEY_ITEMS + " TEXT, "
            + KEY_STATUS + " TEXT, "
            + " FOREIGN KEY (" + KEY_PROPERTY_ID + ") REFERENCES " + TABLE_PROPERTY + " (" + KEY_HOUSE_NUMBER + "), "
            + KEY_DATE_OCCUPIED + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + KEY_DATE_VACATED + " DATETIME, "
            + KEY_PHONE_NUMBER + " TEXT, "
            + "PRIMARY KEY (" + KEY_PROPERTY_ID + ", " + KEY_STATUS + ")" + ")";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TENANT);
    }
}
