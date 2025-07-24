package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);
    YearMonthCountDto selectYearMonthLogin(String yearMonth);

    List<YearMonthCountDto> selectMonthlyVisitors(Map<String, Object> params);
    List<YearMonthCountDto> selectDailyVisitors(Map<String, Object> params);
    Double selectAvgDailyLogin(Map<String, Object> params);
    Integer selectTotalLoginCount(Map<String, Object> params);
    List<YearMonthCountDto> selectDeptMonthlyLogin(Map<String, Object> params);
}
