package com.devteam.util.text;

import java.util.Date;


import com.devteam.util.dataformat.DataSerializer;
import org.junit.jupiter.api.Test;

public class DateUtilUnitTest {
  @Test
  public void test() {
    Date date = new Date();
    String compactDateTimeValue = DateUtil.asCompactDateTime(date);
    System.out.println("compactDateTimeValue = " + compactDateTimeValue);
    date = DateUtil.parseCompactDateTime(compactDateTimeValue);
  }

  @Test
  public void testLocalCompactDatetime() {
    Date date = new Date();
    System.out.println(DataSerializer.JSON.toString(date));
    String compactDateTimeValue = DateUtil.asLocalDateTime(date);
    System.out.println("local date time = " + compactDateTimeValue);
  }
}
