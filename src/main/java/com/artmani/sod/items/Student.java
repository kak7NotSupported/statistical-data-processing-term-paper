package com.artmani.sod.items;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    int groupID;

    @Getter
    int id;

    @Getter
    int groupNumber;

    @Getter
    String firstname;
    @Getter
    String lastname;
    @Getter
    int course;
    @Getter
    HashMap<String, ArrayList<Integer>> marks = new HashMap<>();

    @Getter
    Group group;


    public Student(int id, int groupNumber, String firstName, String lastName, int course, HashMap<String, ArrayList<Integer>> marks) {

    }

    public Integer getMarksCount(String subject){
        return marks.get(subject).size();
    }


    public Float getAvrgMarks(String subject) {

        float mark = 0;
        for (Integer a: marks.get(subject)){
            mark = mark + a;
        }
        return mark / getMarksCount(subject);

    }

    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }


}
