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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Course;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Hp
 */
public class SessionDBContext extends DBContext<Session> {

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        Session session = new Session();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getSessionInfo ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                session.setId(id);
                session.setDate(rs.getDate("date"));

                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setId(rs.getInt("slotId"));
                timeSlot.setNumber(rs.getInt("slotNumber"));
                session.setTimeslot(timeSlot);

                Room room = new Room();
                room.setId(rs.getString("roomId"));
                session.setRoom(room);

                Instructor instructor = new Instructor();
                instructor.setId(rs.getString("lecturerId"));
                session.setInstructor(instructor);

                Group group = new Group();
                Course course = new Course();
                course.setId(rs.getString("courseId"));
                group.setCourse(course);
                group.setId(rs.getInt("groupId"));
                group.setName(rs.getString("groupName"));
                session.setGroup(group);
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
        return session;
    }

    @Override
    public ArrayList<Session> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

    public ArrayList<Session> getWeeklyTimetable(String monday, String instructorId) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getWeeklySchedule ?, ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, monday);
            stm.setString(2, instructorId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("sessionId"));
                session.setName(rs.getString("sessionName"));
                session.setStatus(rs.getBoolean("status"));

                Room room = new Room();
                room.setId(rs.getString("roomId"));
                session.setRoom(room);

                Instructor instructor = new Instructor();
                instructor.setId(rs.getString("instructorId"));
                session.setInstructor(instructor);

                TimeSlot slot = new TimeSlot();
                slot.setNumber(rs.getInt("slotNumber"));
                slot.setStartTime(rs.getTime("startTime"));
                slot.setEndTime(rs.getTime("endTime"));
                session.setTimeslot(slot);

                Course course = new Course();
                course.setId(rs.getString("courseId"));

                Group group = new Group();
                group.setName(rs.getString("groupName"));
                group.setCourse(course);
                session.setGroup(group);

                Date sqlDate = rs.getDate("date");
                session.setDate(sqlDate);

                sessions.add(session);

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
        return sessions;
    }

    public int getNumberOfSessions(int groupId) {
        int noOfSessions = -1;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getSessionCount ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            if (rs.next()) {
                noOfSessions = rs.getInt("noOfSessions");
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
        return noOfSessions;
    }

    public ArrayList<Session> getSessionsFromGroup(int groupId) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getSessionsFromGroup ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("sessionId"));

                Date sqlDate = rs.getDate("date");
                session.setDate(sqlDate);

                sessions.add(session);

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
        return sessions;
    }

    
    public static void main(String[] args) {
        SessionDBContext sessionDb = new SessionDBContext();
//        ArrayList<Session> sessions = sessionDb.getWeeklyTimetable("2023-3-1", "sonnt5");
//        System.out.println("There are " + sessions.size() + " sessions.");


//        ArrayList<Student> students = sessionDb.getStudents(15);
//        System.out.println("There are " + students.size() + " students.");
//        for (Student s : students) {
//            System.out.println(s.getId() + " " + s.getName());
//        }
    }
}
