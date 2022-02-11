package com.artmani.sod;

import com.artmani.sod.items.Group;
import com.artmani.sod.items.Student;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Main.studentsImport();
        Main.generateGroups();

//        for (var s: students) {
//            System.out.println();
//        }

    }

    public static void generateGroups() {
        for (var student: students){
            Group.getById(student.getGroup().getId()).addStudent(student);
        }
    }

    public static void studentsImport() {
        String path = new File("").getAbsolutePath();
        path = path + "\\src\\main\\resources\\data.json";


        var s = new Gson().fromJson(readFileAsString(path), List.class);

        for (Object student : s) {
            students.add(new Gson().fromJson(student.toString(), Student.class));
        }

    }

    public static String readFileAsString(String fileName) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return data;
        }
    }
//        new Gson().fromJson()
}
