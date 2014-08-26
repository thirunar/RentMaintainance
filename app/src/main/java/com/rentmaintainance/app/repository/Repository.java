package com.rentmaintainance.app.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Repository extends SQLiteOpenHelper {
    private static final String LOG = "Repository";

    private MaintainceRepository[] repositories;

    public Repository(Context context, String databaseName, MaintainceRepository... repositories) {
        super(context, MaintainceRepository.DATABASE_NAME, null, MaintainceRepository.DATABASE_VERSION);
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
