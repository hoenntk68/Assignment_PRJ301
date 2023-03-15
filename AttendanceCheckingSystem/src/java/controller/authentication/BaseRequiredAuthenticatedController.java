/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author sonnt
 */
public abstract class BaseRequiredAuthenticatedController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

//    private boolean isAuthorized(HttpServletRequest request){
//        String url = request.getServletPath();
//        if (isAuthenticated(request)){
////            ArrayList<String> urls = new UserDBContext().getPossibleUrl(url);
//            String path = request.getRequestURI();
//            /**
//             * get url from user join role join feature
//             * iterate through the records
//             * if a record match the url --> return true
//             */
//         
//        }
//        return false;
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
//            String servletPath = request.getServletPath();
//            User user = (User) request.getSession().getAttribute("user");
//            String userId = user.getUsername();
//            System.out.println("URL is " + servletPath);
//            System.out.println("Username is: " + userId);
            boolean isAuthorized = new UserDBContext().isAuthorized(request);
            if (isAuthorized) {
//                response.getWriter().print("<h1>Authorization successful!<h1>");
                doGet(request, response, (User) request.getSession().getAttribute("user"));
            } else {
                response.getWriter().print("<h1>Authorization failed!<h1>");
            }
        } else {
//            response.getWriter().println("access denied!");
            response.sendRedirect("../login");
        }
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            //do business
            doPost(request, response, (User) request.getSession().getAttribute("user"));
        } else {
//            response.getWriter().println("access denied!");
            response.sendRedirect("login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
