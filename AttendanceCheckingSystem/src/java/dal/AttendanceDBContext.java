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
import model.Attendance;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Hp
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> getGroupReport(int groupId) {
        ArrayList<Attendance> records = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getGroupReport ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance record = new Attendance();
                Session session = new Session();
                session.setId(rs.getInt("sessionId"));
                record.setSession(session);
                
                Student student = new Student();
                student.setId(rs.getString("studentId"));
                student.setName(rs.getString("studentName"));
                student.setImage("studentImage");
                record.setStudent(student);
                record.setStatus(rs.getBoolean("status"));
                records.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return records;
    }

}
