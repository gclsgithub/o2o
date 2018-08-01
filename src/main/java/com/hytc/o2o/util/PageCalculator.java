package com.hytc.o2o.util;

public class PageCalculator {
    public static Integer caculatateNowPageIndex(int indexNum, int pageSize) {
        return indexNum > 0 ? (indexNum-1)*pageSize:0;
    }
}
