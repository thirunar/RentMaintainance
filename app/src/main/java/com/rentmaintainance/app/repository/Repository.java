package com.rentmaintainance.app.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.rentmaintainance.app.AllConstants.DATABASE_NAME;
import static com.rentmaintainance.app.AllConstants.DATABASE_VERSION;

public class Repository extends SQLiteOpenHelper {
    private static final String LOG = "Repository";

    private MaintainceRepository[] repositories;

    public Repository(Context context, MaintainceRepository... repositories) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.repositories = repositories;

        for (MaintainceRepository repository : repositories) {
            repository.updateMasterRepository(this);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        for (MaintainceRepository repository : repositories) {
            repository.onCreate(database);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
