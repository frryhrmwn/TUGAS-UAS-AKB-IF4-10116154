package com.akb.ferry.tugasuasakbif410116154.utils;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {

    public static boolean isValidNim(String nim) {
        Pattern patron = Pattern.compile("^[0-9]+$");
        return patron.matcher(nim).matches();
    }

    public static boolean isValidName(String name) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(name).matches();
    }

    public static boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String format(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        return sdf.format(date);
    }

    public static String formatMin(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", Locale.US);
        return sdf.format(date);
    }
}