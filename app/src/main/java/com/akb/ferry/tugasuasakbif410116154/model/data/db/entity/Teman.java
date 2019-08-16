package com.akb.ferry.tugasuasakbif410116154.model.data.db.entity;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "people")

public class Teman {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String nim;
    public String nama;
    public String kelas;
    public String telepon;
    public String email;
    public String sosmed;

    @Ignore
    public Teman() {
        this.nim = "";
        this.nama = "";
        this.kelas = "";
        this.telepon = "";
        this.email = "";
        this.sosmed = "";
    }

    public Teman(String nim, String nama, String kelas, String telepon, String email, String sosmed) {
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
        this.telepon = telepon;
        this.email = email;
        this.sosmed = sosmed;
    }
}