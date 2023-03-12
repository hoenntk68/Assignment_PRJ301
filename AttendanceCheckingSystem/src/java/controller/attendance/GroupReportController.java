/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.AttendanceDBContext;
import dal.GroupDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author Hp
 */
public class GroupReportController extends BaseRequiredAuthenticatedController {

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
            out.println("<title>Servlet GroupReportController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GroupReportController at " + request.getContextPath() + "</h1>");
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
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        AttendanceDBContext attendanceDb = new AttendanceDBContext();
        ArrayList<Attendance> records = attendanceDb.getGroupReport(groupId);
        GroupDBContext groupDb = new GroupDBContext();
        Group group = groupDb.get(groupId);
        request.setAttribute("group", group);
        request.setAttribute("records", records);
        SessionDBContext sessionDb = new SessionDBContext();
        ArrayList<Session> sessions = sessionDb.getSessionsFromGroup(groupId);
        request.setAttribute("sessions", sessions);
        StudentDBContext studentDb = new StudentDBContext();
        ArrayList<Student> students = studentDb.getStudentsFromGroup(groupId);
        request.setAttribute("students", students);
        SessionDBContext sessionDb1 = new SessionDBContext();
        int totalSessions = sessionDb1.getNumberOfSessions(groupId);
        HashMap<String, Integer> absent = new HashMap<>();
        for (Attendance a : records) {
            String studentId = a.getStudent().getId();
            if (absent.containsKey(studentId)) {
                if (a.isStatus() == false) {
                    absent.put(studentId, absent.get(studentId) + 1);
                }
            } else {
                if (a.isStatus() == false) {
                    absent.put(studentId, 1);
                } else {
                    absent.put(studentId, 0);
                }
            }
        }
        for (HashMap.Entry<String, Integer> entry : absent.entrySet()) {
            int absentCount = entry.getValue();
            int percentage = Math.round(absentCount * 100 / totalSessions + (float) 0.4);
            entry.setValue(percentage);
        }
        request.setAttribute("absent", absent);

        request.getRequestDispatcher("view/attendance/groupReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        AttendanceDBContext attendDb = new AttendanceDBContext();
        HashMap<String, Integer> absent = attendDb.getAbsenceStat(15);
        for (HashMap.Entry<String, Integer> entry : absent.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + " :\t" + value + "%");
        }
    }

}
