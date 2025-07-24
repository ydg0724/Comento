package com.demo.comentoStatistic.service;


import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    public YearCountDto getYearLogins(String year){
        return statisticMapper.selectYearLogin(year);
    }
    public YearMonthCountDto getYearMonthLogins(String year,String month){
        return statisticMapper.selectYearMonthLogin(year+month);
    }

    public List<YearMonthCountDto> getMonthlyVisitors(String startDate, String endDate){
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return statisticMapper.selectMonthlyVisitors(params);
    }

    public List<YearMonthCountDto> getDailyVisitors(String startDate, String endDate){
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return statisticMapper.selectDailyVisitors(params);
    }

    public Double getAvgDailyLogins(String startDate, String endDate){
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return statisticMapper.selectAvgDailyLogin(params);
    }


    public List<YearMonthCountDto> getDeptMonthlyLogins(String startDate, String endDate){
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return statisticMapper.selectDeptMonthlyLogin(params);
    }


    public Integer getLoginCountWithHolidays(String startDate, String endDate){
        List<String> holidays = loadHolidaysFromFile("/holidays.csv");
        List<LocalDate> dateRange = Stream.iterate(LocalDate.parse(startDate), d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate)) + 1)
                .filter(d -> !holidays.contains(d.toString()))
                .toList();

        int total = 0;
        for (LocalDate date : dateRange) {
            Map<String, Object> param = new HashMap<>();
            param.put("date", date.toString());
            Integer count = statisticMapper.selectTotalLoginCount(param);
            total += (count != null) ? count : 0;
        }

        return total;
    }

    private List<String> loadHolidaysFromFile(String path) {
        List<String> holidays = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                holidays.add(line.trim()); // "2025-01-01" 형식
            }
        } catch (IOException e) {
            throw new RuntimeException("공휴일 파일 읽기 실패: " + e.getMessage());
        }
        return holidays;
    }
}
