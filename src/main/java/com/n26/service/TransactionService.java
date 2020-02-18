package com.n26.service;

import com.n26.model.Transaction;
import com.n26.utils.ISO8601Converter;
import com.n26.validator.TransactionValidator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class TransactionService {

    public List<Transaction> transactionList = new ArrayList<>();

    private Date date;

    private BigDecimal amount;

    private boolean olderThan60seconds;

    public HttpStatus addTransaction(Transaction transaction) {

        transactionList.add(transaction);
        return HttpStatus.CREATED;
    }

    public List<Transaction> getAllTransactions() {
        return transactionList;
    }

    public List<Transaction> getLast60SecondTransactions() {
        return this.getAllTransactions().stream().filter(transaction -> {
            long time = ISO8601Converter.convertToMillisecs(transaction.getTimestamp())
                    - LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
            if (time < 60000) {
                return true;
            }
            return true;
        }).collect(Collectors.toList());
    }

    public void deleteTransations(){
        setTransactionList(new ArrayList());
    }

}
