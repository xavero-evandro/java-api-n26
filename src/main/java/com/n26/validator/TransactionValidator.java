package com.n26.validator;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.n26.model.Transaction;
import com.n26.utils.ISO8601Converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;


public class TransactionValidator {

    public static boolean checkTransaction(Transaction transaction) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.sss'Z'");
        df.setTimeZone(tz);

        try {
            JSONPObject json = new JSONPObject(transaction.toString(), Object.class);
            df.parse(transaction.getTimestamp());
            BigDecimal bd = new BigDecimal(transaction.getAmount().toString()).round(new MathContext(2));
            long transactionTime = ISO8601Converter.convertToMillisecs(transaction.getTimestamp());
            long nowTime = LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
            if ((nowTime - transactionTime) < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
