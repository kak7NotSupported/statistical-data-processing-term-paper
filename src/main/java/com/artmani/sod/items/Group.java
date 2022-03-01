package com.artmani.sod.items;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class Group {

    public static ArrayList<Group> groups = new ArrayList<>();

    @Getter
    int id;
    @Getter
    ArrayList<Student> students = new ArrayList<>();


    public Group(int number) {
        id = number;
        tryToAddToList(number);
    }

    public void addStudent(Student student) {
        /**
         * Добавляет студента в группу, если его там нет
         */
        if (!students.contains(student)) {
            students.add(student);
        }

    }

    public List<String> getSubjectList() {
        return getStudents().get(0).getSubjectList();
    }

    public static Group getGroupByID(int id) {
        /**
         * Получаем объект уже созданной группы по ID (Номеру группы)
         */
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    public Float getAvrgMark(String subject) {
        var marks = new ArrayList<Float>();
        float groupMarks = 0f;
        for (Student s : getStudents()) {
            float avrgMark = s.getAvrgMark(subject);
            groupMarks = groupMarks + avrgMark;
            marks.add(s.getAvrgMark(subject));
        }
        return groupMarks / marks.size();

    }


    public Student getStudentByName(String fullname) {
        for (Student s : getStudents()) {
            if (s.getFullName().contains(fullname)){
                return s;
            }
        }
        return null;
    }


    public Float getAvrgMarksCount(String subject) {

        var marks = new ArrayList<Integer>();

        for (Student s : getStudents()) {
            marks.addAll(s.getMarks().get(subject));
        }
        return Float.valueOf(marks.size()) / Float.valueOf(getStudents().size());
    }

    private void tryToAddToList(int number) {

        for (Group group : groups) {
            if (this.getId() == group.id) {
                return;
            }
        }
        groups.add(this);
    }

}
