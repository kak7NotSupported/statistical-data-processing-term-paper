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
        if (!this.students.contains(student)) {
            this.students.add(student);
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

    private void tryToAddToList(int number) {

        for (Group group : groups) {
            if (this.getId() == group.id) {
                return;
            }
        }
        groups.add(this);
    }

}
