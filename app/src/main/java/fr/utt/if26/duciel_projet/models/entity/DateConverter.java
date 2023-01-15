package fr.utt.if26.duciel_projet.models.entity;

import android.os.Build;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;

public class DateConverter {

    @TypeConverter
    public static LocalDateTime toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            if (dateString.equals("0")) return null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return LocalDateTime.parse(dateString);
            }
        }
        return null;
    }

    @TypeConverter
    public static String toDateString(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }
}