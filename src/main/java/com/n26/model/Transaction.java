package com.n26.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;


@Getter
@Setter
public class Transaction {
    @NumberFormat
    private String amount;
    private String timestamp;

    public Transaction() {

    }

    public Transaction(String amount, String timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
