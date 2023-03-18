/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import Util.DateTimeHelper;
import Util.TimeUtil;
import controller.authentication.BaseRequiredAuthenticatedController;
import dal.AttendanceDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import model.Attendance;
import model.Session;
import model.User;

/**
 *
 * @author Hp
 */
public class StudentTimetableController extends BaseRequiredAuthenticatedController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        
        String date = request.getParameter("date");
        if (date.length() == 0 || date == null) {
            Calendar cal = Calendar.getInstance();
            date = TimeUtil.getDateString(cal);
        }
//        String studentId = user.getUsername().toUpperCase();
        String monday = TimeUtil.getMonday(date);
        String sunday = TimeUtil.getSunday(date);
        ArrayList<Date> dates = DateTimeHelper.getListDates(Date.valueOf(monday), Date.valueOf(sunday));

        LocalDate now = LocalDate.now();
        Date today = Date.valueOf(now);

        AttendanceDBContext sessionDb = new AttendanceDBContext();
        ArrayList<Attendance> attendances = sessionDb.getStudentTimetable(user.getUsername(), monday);
        

        request.setAttribute("today", today);
        request.setAttribute("dates", dates);
        request.setAttribute("date", date);
        request.setAttribute("attendances", attendances);

        request.getRequestDispatcher("../view/student/studentTimetable.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
    }

}
