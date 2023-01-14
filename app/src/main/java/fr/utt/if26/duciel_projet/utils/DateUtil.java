package fr.utt.if26.duciel_projet.utils;

import android.os.Build;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtil {

    public static String toFrenchDateFormat(LocalDateTime date){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.getDayOfMonth() + " "
                    + date.getMonth().toString().toLowerCase()+ " "
                    + date.getYear();
        }
        return "";
    }

    public static String durationInHoursAndMinutes(Duration duration){
        long minutes = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            minutes = duration.toMinutes();
        }

        return (int) (minutes / 60) + " hours " + (int)(minutes % 60) + " minutes";

    }

}
