/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Util.TimeUtil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
//                session.setDate(rs.getDate("date"));
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

    public HashMap<String, Integer> getAbsenceStat(int groupId) {
        HashMap<String, Integer> absent = new HashMap<>();
        SessionDBContext sessionDb = new SessionDBContext();
        int totalSessions = sessionDb.getNumberOfSessions(groupId);
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getAbsentCount ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("studentId");
                int absentCount = rs.getInt("absentCount");
                absent.put(studentId, absentCount);
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
        for (HashMap.Entry<String, Integer> entry : absent.entrySet()) {
            int absentCount = entry.getValue();
            int percentage = Math.round(absentCount * 100 / totalSessions + (float) 0.4);
            entry.setValue(percentage);
        }
        return absent;
    }

    public ArrayList<Attendance> getSessionReport(int sessionId) {
        ArrayList<Attendance> records = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getSessionReport ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance record = new Attendance();

                SessionDBContext sessionDb = new SessionDBContext();
                Session session = sessionDb.get(sessionId);
                record.setSession(session);

                Student student = new Student();
                student.setId(rs.getString("studentId"));
                student.setName(rs.getString("studentName"));
                student.setImage("studentImage");
                record.setStudent(student);

                record.setStatus(rs.getBoolean("status"));
                record.setComment(rs.getString("comment"));
                record.setRecordTime(rs.getTime("recordTime"));

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

    public void insertMany(ArrayList<Attendance> records, int sessionId) {
        PreparedStatement stm = null;
        try {
            String sql = "insert into Attend(studentId, sessionId, status, recordTime, comment) values ";

            //set parameters
            String studentId, comment, recordTime = "";
            boolean status;
            int noOfStudent = records.size();
            for (int i = 0; i < noOfStudent; i++) {
                Attendance a = records.get(i);
                studentId = a.getStudent().getId();
                comment = a.getComment();
                status = a.isStatus();
                sql += "\n";
                sql += "('" + studentId + "', " + sessionId + ", " + (status ? 1 : 0) + ", '" + TimeUtil.getDateTime() + "', '" + comment + "'),";
            }
            sql = sql.substring(0, sql.length() - 1);
            System.out.println(sql);
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        AttendanceDBContext attendDb = new AttendanceDBContext();
        ArrayList<Attendance> records = new ArrayList<>();

        Attendance a1 = new Attendance();
        Student stu1 = new Student();
        stu1.setId("HE170863");
        a1.setStudent(stu1);
        Session ses1 = new Session();
        ses1.setId(12);
        a1.setSession(ses1);
        a1.setComment("cong chua hiphop");
        a1.setStatus(true);

        Attendance a2 = new Attendance();
        Student stu2 = new Student();
        stu2.setId("HE150057");
        a2.setStudent(stu2);
        Session ses2 = new Session();
        ses2.setId(12);
        a2.setSession(ses2);
        a2.setComment("hoang tu hiphop");
        a2.setStatus(true);

        records.add(a1);
        records.add(a2);

        attendDb.insertMany(records, 12);
    }

}
