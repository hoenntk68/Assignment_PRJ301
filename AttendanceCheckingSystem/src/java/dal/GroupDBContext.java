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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
