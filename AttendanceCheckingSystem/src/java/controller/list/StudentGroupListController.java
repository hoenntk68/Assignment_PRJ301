/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.list;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.GroupDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Group;
import model.User;

/**
 *
 * @author Hp
 */
public class StudentGroupListController extends BaseRequiredAuthenticatedController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String studentId = user.getUsername();
        GroupDBContext groupDb = new GroupDBContext();
        ArrayList<Group> groups = groupDb.getGroupsForStudent(studentId);
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("../view/student/group/groupList.jsp").forward(request, response);
                

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
    }

}
