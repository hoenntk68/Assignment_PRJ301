/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.Student;

/**
 *
 * @author Hp
 */
public class StudentDBContext extends DBContext<Student> {
    
    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getNumberOfStudents(int groupId){
        int noOfStudents = -1; 
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getStudentCount ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            if (rs.next()) {
                noOfStudents = rs.getInt("noOfStudents");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return noOfStudents;
    }
    
    public ArrayList<Student> getStudentsFromGroup(int groupId){
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getStudentsFromGroup ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("studentId"));
                student.setName(rs.getString("studentName"));
                student.setImage(rs.getString("studentImage"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }
    
    public static void main(String[] args) {
        StudentDBContext studentDb = new StudentDBContext();
        ArrayList<Student> students = studentDb.getStudentsFromGroup(15);
        System.out.println("There are " + students.size() + " students");
    }
    
}
