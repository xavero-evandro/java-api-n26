package com.n26.controller;

import com.n26.model.Transaction;
import com.n26.service.TransactionService;
import com.n26.utils.ISO8601Converter;
import com.n26.utils.JSONUtils;
import com.n26.validator.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.*;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.GET, value = "/transactions")
    @ResponseBody
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transactions")
    @ResponseBody
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody Transaction transaction) {
        if (!JSONUtils.isJSONValid(transaction.toString())) {
            return new ResponseEntity<>(transaction, BAD_REQUEST);
        }
        if (!TransactionValidator.checkTransaction(transaction)) {
            return new ResponseEntity<>(transaction, UNPROCESSABLE_ENTITY);
        }

        long transactionTime = ISO8601Converter.convertToMillisecs(transaction.getTimestamp());
        long nowTime = LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();

        if ((nowTime - transactionTime) > 60000) {
            return new ResponseEntity<>(transaction, NO_CONTENT);
        } else {
            transactionService.addTransaction(transaction);
            return new ResponseEntity<>(transaction, CREATED);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/transactions")
    @ResponseBody
    public ResponseEntity<Object> deleteTransactions() {
        transactionService.deleteTransations();
        return new ResponseEntity<>(new Object().toString(), NO_CONTENT);
    }

}
