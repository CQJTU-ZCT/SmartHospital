package com.cqjtu.tools;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author mevur
 * @date 18/2/1
 **/
public class PageHandler {
    public static void handlePage(Integer page, Integer limit) {
        if (null == page) {
            page = 1;
            limit = 20;
        } else if (null == limit) {
            limit = 20;
        }
    }
}
