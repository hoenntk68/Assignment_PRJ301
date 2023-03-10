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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TimeTableController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimeTableController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String date = request.getParameter("date");
        if (date.length() == 0 || date == null) {
            Calendar cal = Calendar.getInstance();
            date = TimeUtil.getDateString(cal);
        }

        GroupDBContext groupDb = new GroupDBContext();
        ArrayList<Group> groups = groupDb.all();
        request.setAttribute("groups", groups);
        SessionDBContext sessionDb = new SessionDBContext();
        String monday = TimeUtil.getMonday(date);
        
        String sunday = TimeUtil.getSunday(date);
        ArrayList<Date> dates = DateTimeHelper.getListDates(Date.valueOf(monday), Date.valueOf(sunday));

        ArrayList<Session> sessions = sessionDb.getWeeklyTimetable(monday, user.getUsername());

        request.setAttribute("sessions", sessions);
        request.setAttribute("user", user);
        request.setAttribute("dates", dates);
        request.setAttribute("date", date);
        request.getRequestDispatcher("/view/schedule/weeklyTimetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        String date = "2023-3-2";
        Calendar cal = Calendar.getInstance();
        Date from = Date.valueOf(date);
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date to = Date.valueOf(cal.toString());

        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        for (Date d : dates){
            System.out.println(date);
        }

    }

}
