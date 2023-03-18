package controller.timetable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import Util.DateTimeHelper;
import Util.TimeUtil;
import controller.authentication.BaseRequiredAuthenticatedController;
import dal.GroupDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDate;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.TimeSlot;
import model.User;

/**
 *
 * @author Hp
 */
public class TimeTableController extends BaseRequiredAuthenticatedController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String date = request.getParameter("date");
        if (date.length() == 0 || date == null) {
            Calendar cal = Calendar.getInstance();
            date = TimeUtil.getDateString(cal);
        }

        String monday = TimeUtil.getMonday(date);
        String sunday = TimeUtil.getSunday(date);
        ArrayList<Date> dates = DateTimeHelper.getListDates(Date.valueOf(monday), Date.valueOf(sunday));
//        GroupDBContext groupDb = new GroupDBContext();
//        ArrayList<Group> groups = groupDb.all();
//        request.setAttribute("groups", groups);
        SessionDBContext sessionDb = new SessionDBContext();
        ArrayList<Session> sessions = sessionDb.getWeeklyTimetable(monday, user.getUsername());

        request.setAttribute("sessions", sessions);
        request.setAttribute("user", user);
        request.setAttribute("dates", dates);
        request.setAttribute("date", date);

        LocalDate now = LocalDate.now();
        Date today = Date.valueOf(now);
        request.setAttribute("today", today);

        request.getRequestDispatcher("../view/instructor/schedule/teacherTimetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    public static void main(String[] args) {
//        String date = "2023-3-2";
//        Calendar cal = Calendar.getInstance();
//        Date from = Date.valueOf(date);
//        cal.setTime(from);
//        cal.add(Calendar.DAY_OF_MONTH, 7);
//        Date to = Date.valueOf(cal.toString());
//
//        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
//        for (Date d : dates){
//            System.out.println(date);
//        }
//
//    }
}
