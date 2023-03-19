/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Instructor;

/**
 *
 * @author Hp
 */
public class GroupDBContext extends DBContext<Group> {

    @Override
    public void insert(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getGroup ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Group group = new Group();
                group.setId(id);
                group.setName(rs.getString("groupName"));
                Course course = new Course();
                course.setName(rs.getString("courseId"));
                group.setCourse(course);
                Instructor instructor = new Instructor();
                instructor.setId(rs.getString("instructorId"));
                return group;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Group> all() {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select \n"
                + "g.groupId, g.groupName, g.courseId, c.courseName, g.instructorId, i.instructorName\n"
                + "from [Group] g\n"
                + "join Course c on g.courseId = c.courseId\n"
                + "join Instructor i on g.instructorId = i.instructorId";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                Course course = new Course();
                Instructor instructor = new Instructor();
                group.setId(rs.getInt("groupId"));
                group.setName(rs.getString("groupName"));
                course.setId(rs.getString("courseId"));
                course.setName(rs.getString("courseName"));
                instructor.setId(rs.getString("instructorId"));
                instructor.setName(rs.getString("instructorName"));
                group.setCourse(course);
                group.setInstructor(instructor);
                groups.add(group);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }

    public ArrayList<Group> getGroupsForInstructor(String instructorId) {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getGroupFromInstructorId ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, instructorId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                Course course = new Course();
                Instructor instructor = new Instructor();
                group.setId(rs.getInt("groupId"));
                group.setName(rs.getString("groupName"));
                course.setId(rs.getString("courseId"));
                course.setName(rs.getString("courseName"));
//                instructor.setId(rs.getString("instructorId"));
//                instructor.setName(rs.getString("instructorName"));
                group.setCourse(course);
                group.setInstructor(instructor);
                groups.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }

    public ArrayList<Group> getGroupsForStudent(String studentId) {
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "exec getGroupFromStudentId ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                Course course = new Course();
                Instructor instructor = new Instructor();
                group.setId(rs.getInt("groupId"));
                group.setName(rs.getString("groupName"));
                course.setId(rs.getString("courseId"));
                course.setName(rs.getString("courseName"));
                group.setCourse(course);
                groups.add(group);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(GroupDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;
    }

    public static void main(String[] args) {
        GroupDBContext db = new GroupDBContext();
        ArrayList<Group> groups = db.getGroupsForInstructor("sonnt5");
        System.out.println(groups.size() + " found");
    }
}
