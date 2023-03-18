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
//        System.out.println("User is: " + request.getSession().getAttribute("user"));
//        System.out.println("User1 is: " + request.getSession().getAttribute("user1"));
        return request.getSession().getAttribute("user1") != null;
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
//        User user = (User) request.getSession().getAttribute("user");
//        User user1 = (User) request.getSession().getAttribute("user1");
        if (isAuthenticated(request)) {
            boolean isAuthorized = new UserDBContext().isAuthorized(request);
            if (isAuthorized) {
                doGet(request, response, (User) request.getSession().getAttribute("user1"));
            } else {
                request.getRequestDispatcher("../view/authentication/accessDenied.jsp").forward(request, response);
            }
        } else {
            System.out.println("not ok");
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
            response.sendRedirect("login");
        }
    }



}
