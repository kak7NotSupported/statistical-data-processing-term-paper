package com.artmani.sod.items;

import java.util.ArrayList;
import java.util.HashMap;
import com.artmani.sod.subject.MathSubject;
import lombok.Getter;
import lombok.Setter;

public class Student {

    @Getter
    int id;

    @Setter
    @Getter
    Group group;
    @Getter
    String firstName;
    @Getter
    String lastName;
    @Getter
    int course;
    @Getter
    HashMap<String, ArrayList<Integer>> marks = new HashMap<>();

    public Student(int id, Group group, String firstName, String lastName, int course, HashMap<String, ArrayList<Integer>> marks) {

    }
}
