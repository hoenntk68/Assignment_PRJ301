/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hp
 */
public class TimeUtil {

    public static String getMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysUntilMonday = (dayOfWeek - Calendar.MONDAY + 7) % 7;
        calendar.add(Calendar.DAY_OF_YEAR, -daysUntilMonday);
        String monString = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        Date mon = calendar.getTime();
        return monString;
    }

    public static String getMonday(String date) {
        Calendar calendar = Calendar.getInstance();
        int num[] = getTimeNumbers(date);
        calendar.set(num[0], num[1], num[2]);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysUntilMonday = (dayOfWeek - Calendar.MONDAY + 7) % 7;
        calendar.add(Calendar.DAY_OF_YEAR, -daysUntilMonday);
        String monString = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        Date mon = calendar.getTime();
        return monString;
    }

    public static String getDateString(Calendar date) {
        return date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH);
    }

    public static int[] getTimeNumbers(String sdate) {
        int[] nums = new int[3];
        String[] arr = sdate.split("[-/]");
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(arr[i]);
            if (i == 1) {
                nums[i]--;
            }
        }
        return nums;
    }
}
