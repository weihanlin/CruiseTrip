package com.example.cruiseTrip.util;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public int differentiatingDays(String fromDay, String toDay) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date firstDate = sdf.parse(fromDay);
        Date secondDate = sdf.parse(toDay);

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        return (int)TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
