package com.rentmaintainance.app.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.rentmaintainance.app.AllConstants.DATABASE_NAME;
import static com.rentmaintainance.app.AllConstants.DATABASE_VERSION;

public class Repository extends SQLiteOpenHelper {
    private static final String LOG = "Repository";

    private MaintenanceRepository[] repositories;

    public Repository(Context context, MaintenanceRepository... repositories) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.repositories = repositories;

        for (MaintenanceRepository repository : repositories) {
            repository.updateMasterRepository(this);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        for (MaintenanceRepository repository : repositories) {
            repository.onCreate(database);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
