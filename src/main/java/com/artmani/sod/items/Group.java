package com.artmani.sod.items;

import lombok.Getter;

import java.util.ArrayList;


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
        for (Student s :getStudents()) {
            float avrgMark = s.getAvrgMarks(subject);
            groupMarks = groupMarks + avrgMark;
            marks.add(s.getAvrgMarks(subject));
        }
        return groupMarks / marks.size();

    }

    public Float getAvrgMarksCount(String subject){
        ArrayList<Float> marks = new ArrayList<Float>();
        float groupMarks = 0f;
        for (Student s: getStudents()){
            float avrgMark = s.getAvrgMarks(subject);
            groupMarks = groupMarks + avrgMark;
            marks.add(avrgMark);
        }

        return groupMarks / marks.size();
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
