/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Util.DateTimeHelper;
import Util.TimeUtil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public ArrayList<Attendance> getStudentTimetable(String studentId, String monday) {
        ArrayList<Attendance> records = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getStudentTimetable ?, ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setString(2, monday);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance record = new Attendance();

                Session session = new Session();
                session.setId(rs.getInt("sessionId"));
                session.setStatus(rs.getBoolean("sessionStatus"));

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

                Student student = new Student();
                student.setId(studentId);

                record.setStudent(student);
                record.setSession(session);
                record.setStatus(rs.getBoolean("attendStatus"));
                record.setFirstTaken(rs.getInt("firstTaken"));

                records.add(record);

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
        return records;
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
                session.setStatus(rs.getBoolean("sessionStatus"));
//                session.setDate(rs.getDate("date"));
                record.setSession(session);

                Student student = new Student();
                student.setId(rs.getString("studentId"));
                student.setName(rs.getString("studentName"));
                student.setImage("studentImage");
                record.setStudent(student);
                record.setStatus(rs.getBoolean("attendStatus"));

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

    public void updateAtts(ArrayList<Attendance> attendances, int sessionId) {
        ArrayList<PreparedStatement> statements = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            //UPDATE Session Record
            String sql_update_session = "UPDATE Session SET status = 1 WHERE sessionId = ?";
            PreparedStatement updateSessionStm = connection.prepareStatement(sql_update_session);
            updateSessionStm.setInt(1, sessionId);
            updateSessionStm.executeUpdate();
            statements.add(updateSessionStm);

            String now = DateTimeHelper.now();
//PROCESS Attendace records
            for (Attendance attendance : attendances) {
                if (attendance.getFirstTaken() == 0) //INSERT
                {
                    String sqlInsertAttendance = "INSERT INTO Attend\n"
                            + "(studentId, sessionId, status, comment, recordTime, firstTaken) \n"
                            + "VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement insertStm = connection.prepareStatement(sqlInsertAttendance);
                    insertStm.setString(1, attendance.getStudent().getId());
                    insertStm.setInt(2, sessionId);
                    insertStm.setBoolean(3, attendance.isStatus());
                    insertStm.setString(4, attendance.getComment());
                    insertStm.setString(5, now);
                    insertStm.setInt(6, 1);
                    insertStm.executeUpdate();
                    statements.add(insertStm);

                } else //UPDATE
                {
                    String sqlUpdateAttendance = "UPDATE Attend SET status = ?, comment = ?, recordTime = ? WHERE studentId = ? and sessionId = ?";
                    PreparedStatement updateStm = connection.prepareStatement(sqlUpdateAttendance);
                    updateStm.setBoolean(1, attendance.isStatus());
                    updateStm.setString(2, attendance.getComment());
                    updateStm.setString(3, now);
                    updateStm.setString(4, attendance.getStudent().getId());
                    updateStm.setInt(5, sessionId);
                    updateStm.executeUpdate();
                    statements.add(updateStm);
                }
            }

            connection.commit();
        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PreparedStatement stm : statements) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Attendance> getAttendanceBySessionId(int sessionId) {
        ArrayList<Attendance> attendances = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "exec getAttendanceBySessionId ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setStatus(rs.getBoolean("status"));
                attendance.setComment(rs.getString("comment"));
                attendance.setFirstTaken(rs.getInt("firstTaken"));
                Student student = new Student();
                student.setId(rs.getString("studentId"));
                student.setName(rs.getString("studentName"));
                attendance.setStudent(student);
                attendances.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        return attendances;
    }

    public ArrayList<Attendance> getStudentReport(String studentId, int groupId) {
        ArrayList<Attendance> records = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "exec getCourseReportForStudent ?, ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setInt(2, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();

                attendance.setStatus(rs.getBoolean("attendStatus"));
                attendance.setComment(rs.getString("comment"));
                attendance.setFirstTaken(rs.getInt("firstTaken"));

                Session session = new Session();
                session.setDate(rs.getDate("date"));

                Room room = new Room();
                room.setId(rs.getString("roomId"));
                session.setRoom(room);

                Instructor instructor = new Instructor();
                instructor.setId(rs.getString("lecturerId"));
                session.setInstructor(instructor);

                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setNumber(rs.getInt("slotNumber"));
                timeSlot.setStartTime(rs.getTime("startTime"));
                timeSlot.setEndTime(rs.getTime("endTime"));
                session.setTimeslot(timeSlot);

                Group group = new Group();
                group.setId(groupId);
                group.setName(rs.getString("groupName"));
                session.setGroup(group);

                Student student = new Student();
                student.setId(studentId);
                attendance.setStudent(student);
                attendance.setSession(session);
                records.add(attendance);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        return records;

    }

    public int getAbsentStatForStudent(String studentId, int groupId) {
        int count = -1;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "exec getAbsentStatForStudent ?, ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setInt(2, groupId);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("noOfAbsent");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        return count;
    }

    public static void main(String[] args) {
//        AttendanceDBContext db = new AttendanceDBContext();
////        ArrayList<Attendance> attendances = db.getStudentReport("he170863", 15);
//        System.out.println("There are " + db.getAbsentStatForStudent("HE150057", 15));
        AttendanceDBContext attendDb = new AttendanceDBContext();
        HashMap<String, Integer> absent = attendDb.getAbsenceStat(15);
        System.out.println(absent.size());
        for (HashMap.Entry<String, Integer> entry : absent.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + " :\t" + value + "%");
        }
    }
}
