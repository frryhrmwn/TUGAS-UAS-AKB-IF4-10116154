package com.akb.ferry.tugasuasakbif410116154.model.data.db;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.akb.ferry.tugasuasakbif410116154.model.data.db.dao.TemanDao;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;


@Database(entities = {Teman.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract TemanDao temanModel();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "temans")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
