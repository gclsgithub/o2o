package com.hytc.o2o.util;

import org.apache.ibatis.annotations.Param;

public class PageUtil {

    private PageUtil() {

    }

    public static int caculate(int index, int pageSize) {
        return index > 0 ? index - 1 * pageSize : 0;
    }


    public static int calculatePageCount(int totalCount, int pageSize) {
        int idealPage = totalCount / pageSize;
        int totalPage = (totalCount % pageSize == 0) ? idealPage
                : (idealPage + 1);
        return totalPage;
    }
}
