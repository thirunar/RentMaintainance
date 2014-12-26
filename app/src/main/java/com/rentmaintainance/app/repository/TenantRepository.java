package com.rentmaintainance.app.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.rentmaintainance.app.model.Tenant;
import com.rentmaintainance.app.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.rentmaintainance.app.utils.DateUtil.getDateTime;

public class TenantRepository extends MaintenanceRepository {

    public static final String TABLE_TENANT = "tenant";

    public static String KEY_ID = "id";
    public static String KEY_NAME = "name";
    public static String KEY_STATUS = "status";
    public static String KEY_DATE_OCCUPIED = "dateOccupied";
    public static String KEY_DATE_VACATED = "dateVacated";
    public static String KEY_PHONE_NUMBER = "phoneNumber";
    public static String CREATE_TENANT = "CREATE TABLE "
            + TABLE_TENANT + "("
            + KEY_ID + " TEXT PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_STATUS + " TEXT, "
            + KEY_DATE_OCCUPIED + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + KEY_DATE_VACATED + " DATETIME, "
            + KEY_PHONE_NUMBER + " TEXT " + ")";

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TENANT);
    }

    public long addTenant(Tenant tenant) {
        SQLiteDatabase db = masterRepository.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, UUID.randomUUID().toString());
        values.put(KEY_NAME, tenant.name());
        values.put(KEY_STATUS, tenant.status());
        values.put(KEY_DATE_OCCUPIED, DateUtil.formatDateTime(tenant.dateOccupied()));
        values.put(KEY_PHONE_NUMBER, tenant.phoneNumber());

        long rowId = db.insert(TABLE_TENANT, KEY_ID, values);

        if (rowId == -1)
            throw new SQLiteException("Error inserting Property");

        return rowId;
    }

    public List<Tenant> getAllTenants() {
        String query = "SELECT * FROM " + TABLE_TENANT;
        SQLiteDatabase db = masterRepository.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        return readAllTenants(cursor);
    }

    private List<Tenant> readAllTenants(Cursor cursor) {
        List<Tenant> tenants = new ArrayList<Tenant>();

        while (!cursor.isAfterLast()) {
            Tenant tenant = new Tenant();
            tenant.withId(cursor.getString(0))
                    .withName(cursor.getString(1))
                    .withStatus(cursor.getString(2))
                    .withDateOccupied(getDateTime(cursor.getString(3)))
                    .withPhoneNumber(cursor.getString(5));
            tenants.add(tenant);
            cursor.moveToNext();
        }
        cursor.close();
        return tenants;
    }

}
