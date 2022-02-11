package com.artmani.sod.items;

import lombok.Getter;

import java.util.ArrayList;


public class Group {

    public static ArrayList<Group> groups = new ArrayList<>();

    @Getter
    int id;
    ArrayList<Student> students = new ArrayList<>();

    public Group(int number){
        groups.add(this);
    }

    public static Group getById(int id){
        for (Group group: groups){
            if (group.getId() == id){
                return group;
            }
        }
        return new Group(id);
    };


    public ArrayList<Student> getStudents(){
        return students;
    };

    public void addStudent(Student student){
        students.add(student);
    }

}
