package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import com.demo.comentoStatistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value="/api/stats/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") String year) {
        return ResponseEntity.ok(statisticService.getYearLogins(year));

    }

    @RequestMapping(value="/api/stats/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month) {
        return ResponseEntity.ok(statisticService.getYearMonthLogins(year, month));
    }


    @RequestMapping(value = "/api/stats/monthly-visitors",produces = "application/json")
    @ResponseBody
    public List<YearMonthCountDto> getMonthlyVisitors(@RequestParam String startDate, @RequestParam String endDate) {
        List<YearMonthCountDto> result = statisticService.getMonthlyVisitors(startDate, endDate);
        Map<String, Object> response = new HashMap<>();
        response.put("startDate", result);
        response.put("endDate", result);
        response.put("monthlyVisitors", result);

        return ResponseEntity.ok(statisticService.getMonthlyVisitors(startDate, endDate)).getBody();
    }

    @RequestMapping(value = "/api/stats/daily-visitors", produces = "application/json")
    @ResponseBody
    public List<YearMonthCountDto> getDailyVisitors(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(statisticService.getDailyVisitors(startDate, endDate)).getBody();
    }

    @RequestMapping(value = "/api/stats/avg-logins", produces = "application/json")
    @ResponseBody
    public Double getAvgDailyLogins(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(statisticService.getAvgDailyLogins(startDate, endDate)).getBody();
    }

    @RequestMapping(value = "/api/stats/logins-holidays", produces = "application/json")
    @ResponseBody
    public Integer getLoginCountWithHolidays(@RequestParam String startDate, @RequestParam String endDate) {
        return statisticService.getLoginCountWithHolidays(startDate, endDate);
    }

    @RequestMapping(value = "/api/stats/logins-dept", produces = "application/json")
    @ResponseBody
    public List<YearMonthCountDto> getDeptMonthlyLogins(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String dept) {
        return statisticService.getDeptMonthlyLogins(startDate, endDate, dept);
    }

}
