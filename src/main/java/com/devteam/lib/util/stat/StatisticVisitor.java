package com.devteam.lib.util.stat;

public interface StatisticVisitor {
    public void onVisit(Statistic statistics, StatisticEntry statistic) ;
}
