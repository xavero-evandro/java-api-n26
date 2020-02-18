package com.n26.controller;

import com.n26.model.Statistics;
import com.n26.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    @ResponseBody
    public Statistics getTransactions() {
        return statisticsService.getStatistics();
    }
}
