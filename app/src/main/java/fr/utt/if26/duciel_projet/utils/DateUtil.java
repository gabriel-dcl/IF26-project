package fr.utt.if26.duciel_projet.utils;

import android.os.Build;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtil {

    public static String toFrenchDateFormat(LocalDateTime date){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.getDayOfMonth() + " / " + date.getMonth() + " / " + date.getYear();
        }
        return "";
    }

    public static String durationInHoursAndMinutes(Duration duration){
        long minutes = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            minutes = duration.toMinutes();
        }

        return "";
    }

}
