package com.artmani.sod;

import com.artmani.sod.items.Group;
import com.artmani.sod.items.Student;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    public static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Main.studentsImport();
        Main.syncGroups();

        /**
         * DEBUG
         */

        for (var s : students) {
            System.out.println(s.getLastname());
        }

        for (Group g : Group.groups) {
            System.out.println("\nGroup: #" + g.getId());
            for (Student s : g.getStudents()) {
                System.out.println("- " + s.getLastname() + " " + s.getFirstname());
            }
        }

    }

    public static void studentsImport() {
        /**
         * Импортирует всех студентов из jsonки в объекты, затем в ArrayList
         */
        String path = new File("").getAbsolutePath();
        System.out.println(path);
        path = path + "\\src\\main\\resources\\data.json";
        System.out.println(path);

        var s = new Gson().fromJson(readFileAsString(path), JsonArray.class);

        for (JsonElement student : s) {
            Student studentObj = new Gson().fromJson(student.toString(), Student.class);
            students.add(studentObj);
        }
    }

    public static void syncGroups() {
        /**
         * Синхронизирует группы после генерации студентов (Создает группы и добавляет туда студентов)
         * @see studentsImport()
         */
        for (Student student : students) {
            var group = Group.getGroupByID(student.getGroupNumber());
            Objects.requireNonNullElseGet(group, () -> new Group(student.getGroupNumber())).addStudent(student);
        }

    }

    public static String readFileAsString(String fileName) {
        /**
         * Считывает данные с файла
         * @return String с данными
         */
        String data = "";

        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return data;
        }
    }

    @Override
    public void start(Stage stage) {

        // установка надписи
        Text text = new javafx.scene.text.Text("asd");
        text.setLayoutY(80);    // установка положения надписи по оси Y
        text.setLayoutX(80);   // установка положения надписи по оси X

        javafx.scene.Group group = new javafx.scene.Group(text);

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();
    }

}
