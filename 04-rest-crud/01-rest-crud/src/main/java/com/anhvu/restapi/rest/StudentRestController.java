package com.anhvu.restapi.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.restapi.entity.Student;
import com.anhvu.restapi.exception.NotFoundException;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        renderStudent(theStudents);
    }

    public void renderStudent(List<Student> theStudents) {
        Random randome = new Random();
        String[] nameArr = { "Anh", "Thu", "Tu", "Thao" };
        for (int i = 1; i <= 100; i++) {
            int rdIdx = randome.nextInt(nameArr.length);
            theStudents.add(
                    new Student(nameArr[rdIdx] + i, nameArr[rdIdx], nameArr[rdIdx] + i + "@gmail.com"));
        }
    }

    // define enpont for /students - return a list students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        // check the studentId again list size

        if (studentId >= theStudents.size() || studentId < 0) {
            throw new NotFoundException("Student id not found " + studentId);
        }
        return theStudents.get(studentId);
    }
}
