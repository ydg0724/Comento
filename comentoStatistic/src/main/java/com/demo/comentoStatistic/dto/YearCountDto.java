package com.demo.comentoStatistic.dto;

public class YearCountDto {
    private String yearMonth;
    private int totCnt;

    // 반드시 getter/setter 존재해야 함
    public String getYearMonth() { return yearMonth; }
    public void setYearMonth(String yearMonth) { this.yearMonth = yearMonth; }

    public int getTotCnt() { return totCnt; }
    public void setTotCnt(int totCnt) { this.totCnt = totCnt; }
}
