package org.wenxueliu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.*;
import java.util.Locale;
import java.util.TimeZone;


import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

//@RunWith(JUnitPlatform.class)
public class ScheduleServiceTest {

    @BeforeAll
    static void setup() {
        // Initialize connection to file.
        System.out.println("--------------- START -------------");
    }

    @Test
    public void doScheduleSingleTimeZone() {
        TimeZone tzone = TimeZone.getDefault();
        // Assume that the timezone is US/Mountain
        assumeTrue(tzone.getDisplayName().equals("US/Mountain"));

        // Test doSchedule method
        ScheduleService scheduleService = new ScheduleService();
        assertTrue(scheduleService.doSchedule());
    }

    @Test
    public void doScheduleLocaleNonUS() {
        // Assume that the current locale is US
        Locale currentLocale = Locale.getDefault();
        assumeTrue(currentLocale.equals(Locale.US));

        // Test doSchedule method
        ScheduleService scheduleService = new ScheduleService();
        assertTrue(scheduleService.doSchedule());
    }

    @Test
    public void backupCalendarWindows() {
        System.out.println(System.getProperty("os.name"));
        assumeTrue(System.getProperty("os.name").startsWith("Linux"));

        System.out.println(System.getProperty("os.name"));
        // Test doSchedule method
        ScheduleService scheduleService = new ScheduleService();
        assertTrue(scheduleService.backupCalendar());
    }

    @AfterAll
    static void done() {
        // Closes connection to the file
        System.out.println("--------------- END -------------");
    }
}
