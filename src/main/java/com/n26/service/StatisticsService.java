package com.n26.service;

import com.n26.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    TransactionService transactionService;

    public Statistics getStatistics(){
        return new Statistics(transactionService.getLast60SecondTransactions());
    }

}
