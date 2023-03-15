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

        //get students of the session
        StudentDBContext studentDb = new StudentDBContext();
        ArrayList<Student> students = studentDb.getStudentsFromSession(sessionId);
        request.setAttribute("students", students);

        request.getRequestDispatcher("../view/instructor/attendance/takeAttendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
//get session info (id)     
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));
        int noOfStudent = Integer.parseInt(request.getParameter("noOfStudent"));
        SessionDBContext sessionDb = new SessionDBContext();
        Session session = sessionDb.get(sessionId);
//        request.setAttribute("session", session);

        ArrayList<Attendance> records = new ArrayList<>();
        for (int i = 0; i < noOfStudent; i++) {
            Attendance attendance = new Attendance();
            Student student = new Student();
            student.setId(request.getParameter("student" + i));
            attendance.setStudent(student);
            attendance.setSession(session);
            attendance.setComment(request.getParameter("comment" + i));
            String statusCheck = request.getParameter("status" + i);
            if (statusCheck != null) {
                System.out.println("Status check is " + statusCheck);
                attendance.setStatus(Boolean.parseBoolean(statusCheck));
                records.add(attendance);
            }

        }
        //put them into array of Attendance objects --> pass this list into AttendanceDBContext (write function)
        AttendanceDBContext attendanceDb = new AttendanceDBContext();
        attendanceDb.insertMany(records, sessionId);
        response.sendRedirect("sessionAttendance?sessionId=" + sessionId);
    }

} 
