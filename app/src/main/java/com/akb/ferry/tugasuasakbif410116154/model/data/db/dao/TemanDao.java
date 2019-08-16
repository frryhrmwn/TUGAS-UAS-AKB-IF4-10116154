package com.akb.ferry.tugasuasakbif410116154.model.data.db.dao;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface TemanDao {

    @Query("SELECT * FROM people ORDER BY nim ASC")
    LiveData<List<Teman>> findAllTemans();

    @Query("SELECT * FROM people")
    List<Teman> getAllChannels();

    @Query("SELECT * FROM people WHERE id=:id")
    Teman findTemanById(String id);

    @Query("SELECT * FROM people WHERE id=:id")
    Teman findTeman(long id);

    @Insert(onConflict = IGNORE)
    long insertTeman(Teman teman);

    @Update
    int updateTeman(Teman teman);

    @Update
    void updateTeman(List<Teman> teman);

    @Delete
    void deleteTeman(Teman teman);

    @Query("DELETE FROM people")
    void deleteAll();
}
