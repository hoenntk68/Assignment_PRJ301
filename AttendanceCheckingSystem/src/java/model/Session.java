/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Hp
 */
public class Session {

    private int id;
    private String name;
    private Group group;
    private Instructor instructor;
    private Date date;
    private TimeSlot timeslot;
    private Room room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDayOfWeek() {
        String s = date.toString();
        String nums[] = s.split("-");
        int[] ints = new int[3];
        for (int i = 0; i < nums.length; i++) {
            ints[i] = Integer.parseInt(nums[i]);
        }
        Calendar cal = Calendar.getInstance();
        cal.set(ints[0], ints[1] - 1, ints[2]);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static void main(String[] args) {
        Session s = new Session();
        s.setDate(Date.valueOf("2023-03-02"));
        System.out.println(s.getDayOfWeek());

    }

}
