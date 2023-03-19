/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Hp
 */
public class TimeUtil {

    /**
     * @param date in yyyy-[m]m-[d]d format
     * @return the Monday of the corresponding week
     */
    public static String getMonday(String date) {
        Calendar calendar = Calendar.getInstance();
        int num[] = getTimeNumbers(date);
        calendar.set(num[0], num[1], num[2]);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//        System.out.println("Day is: " + dayOfWeek);
        int daysUntilMonday = (dayOfWeek - Calendar.MONDAY + 7) % 7;
        calendar.add(Calendar.DAY_OF_YEAR, -daysUntilMonday);
        String monString = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        return monString;
    }

    public static String getSunday(String date) {
        Calendar calendar = Calendar.getInstance();
        int num[] = getTimeNumbers(date);
        calendar.set(num[0], num[1], num[2]);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysUntilSunday = (dayOfWeek - Calendar.SUNDAY + 7) % 7 - 7;
        if (daysUntilSunday == -7) {
            daysUntilSunday = 0;
        }
        calendar.add(Calendar.DAY_OF_YEAR, -daysUntilSunday);
        String sunString = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        return sunString;
    }

    /**
     * @param Calendar date object
     * @return a string represent the corresponding date
     */
    public static String getDateString(Calendar date) {
        return date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param date in yyyy-[m]m-[d]d format
     * @return int array consisting of 3 elements representing yyyy, mm, dd
     */
    public static int[] getTimeNumbers(String date) {
        int[] nums = new int[3];
        String[] arr = date.split("[-/]");
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(arr[i]);
            if (i == 1) {
                nums[i]--;
            }
        }
        return nums;
    }

    public static ArrayList<java.sql.Date> getDates(String from) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();

        return dates;
    }

    public static java.sql.Date convertUtilToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public static void main(String[] args) {
        String date = "2023-3-12";
//        System.out.println(getMonday(date));
//        System.out.println(getSunday(date));
        System.out.println(getDateTime());
    }

}
