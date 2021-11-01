package com.devteam.util.text;

import com.devteam.lib.util.dataformat.DataSerializer;
import com.devteam.lib.util.text.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateUtilUnitTest {
    @Test
    public void test() {
        Date date = new Date();
        String compactDateTimeValue = DateUtil.asCompactDateTime(date);
        System.out.println("compactDateTimeValue = " + compactDateTimeValue);
        date = DateUtil.parseCompactDateTime(compactDateTimeValue);
        System.out.println(DataSerializer.JSON.toString(date));
    }

    @Test
    public void testLocalCompactDatetime() {
        Date date = new Date();
        System.out.println(DataSerializer.JSON.toString(date));
        String compactDateTimeValue = DateUtil.asLocalDateTime(date);
        System.out.println("local date time = " + compactDateTimeValue);
    }
}
