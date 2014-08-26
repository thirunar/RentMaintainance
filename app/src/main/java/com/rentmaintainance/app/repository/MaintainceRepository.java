package com.rentmaintainance.app.repository;

import android.database.sqlite.SQLiteDatabase;

public abstract class MaintainceRepository {

    protected Repository masterRepository;

    public void updateMasterRepository(Repository repository) {
        this.masterRepository = repository;
    }

    abstract protected void onCreate(SQLiteDatabase database);

}
