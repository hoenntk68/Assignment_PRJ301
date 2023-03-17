/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.AttendanceDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author Hp
 */
public class AttendanceTakingController extends BaseRequiredAuthenticatedController {

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
            out.println("<title>Servlet AttendanceTakingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceTakingController at " + request.getContextPath() + "</h1>");
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
        //get session id   
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));
        //get session info
        SessionDBContext sessionDb = new SessionDBContext();
        Session session = sessionDb.get(sessionId);
        request.setAttribute("session", session);

        //check editable
        LocalDate today = LocalDate.now();
        boolean isEditable = session.getDate().compareTo(Date.valueOf(today)) == 0;
        request.setAttribute("isEditable", isEditable);

        //get students of the session
        AttendanceDBContext db = new AttendanceDBContext();
        ArrayList<Attendance> attendances = db.getAttendanceBySessionId(sessionId);
        request.setAttribute("attendances", attendances);

        request.getRequestDispatcher("../view/instructor/attendance/takeAttendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        //get session info (id)     
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));
//        int noOfStudent = Integer.parseInt(request.getParameter("noOfStudent"));
        SessionDBContext sessionDb = new SessionDBContext();
        Session session = sessionDb.get(sessionId);
        //request.setAttribute("session", session);

        String[] studentIds = request.getParameterValues("studentId");

        ArrayList<Attendance> records = new ArrayList<>();
        for (String studentId : studentIds) {
            Attendance attendance = new Attendance();
            Student student = new Student();
            student.setId(studentId);
            attendance.setStudent(student);
            attendance.setSession(session);
            attendance.setComment(request.getParameter("comment" + studentId));
            attendance.setStatus(request.getParameter("status" + studentId).equals("true"));
            int firstTaken = Integer.parseInt(request.getParameter("firstTaken" + studentId));
            attendance.setFirstTaken(firstTaken);
//            String statusCheck = request.getParameter("status" + studentId);
//            if (statusCheck != null) {
//                System.out.println("Status check is " + statusCheck);
//                attendance.setStatus(Boolean.parseBoolean(statusCheck));
//                records.add(attendance);
//            }

            records.add(attendance);
        }
        AttendanceDBContext attendanceDb = new AttendanceDBContext();
        attendanceDb.updateAtts(records, sessionId);
        response.sendRedirect("weeklyTimetable?date=");
    }

}
