package com.n26.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class ISO8601Converter {

    public static LocalDateTime fromISO8601UTC(String date) {
        try {
            TemporalAccessor ta = DateTimeFormatter.ISO_DATE_TIME.parse(date);
            Instant i = Instant.from(ta);
            Date d = Date.from(i);
            return d.toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static long convertToMillisecs(String date){
        return fromISO8601UTC(date).atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

}

