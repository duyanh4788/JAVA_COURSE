package com.anhvu.database;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.anhvu.database.entity.Student;
import com.anhvu.database.dao.StudentDAO;

@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// deleteAll(studentDAO);
			// deleteStudentById(studentDAO);
			// updateStudent(studentDAO);
			// findByEmail(studentDAO);
			// getListStudents(studentDAO);
			// findStudentById(studentDAO);
			createMultipleStudent(studentDAO);

		};
	}

	private void findStudentById(StudentDAO studentDAO) {
		Student myStudent = studentDAO.findById(10);
		System.out.println("Student: " + myStudent);
	}

	private void findByEmail(StudentDAO studentDAO) {
		List<Student> listStudents = studentDAO.findByEmail("anh01@example.com");
		for (Student item : listStudents) {
			System.out.println("Student: " + item);
		}
	}

	private void getListStudents(StudentDAO studentDAO) {
		List<Student> listStudents = studentDAO.findAll();
		for (Student item : listStudents) {
			System.out.println(item);

		}

	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create multiple students object
		System.out.println("Create the new multiple Object......");
		int numberLeng = 100;
		String[] arrName = { "Vu", "Tu", "Thu", "Tuan", "Thao" };
		int length = arrName.length;
		Random random = new Random();
		for (int i = 1; i <= numberLeng; i++) {
			int randomIdx = random.nextInt(length);
			Student temStudent = new Student(arrName[randomIdx] + i, "Vu", arrName[randomIdx] + i + "@example.com");
			createStudent(studentDAO, temStudent);
			System.out.println("save successfully the new ID: " + temStudent.getId());
		}
	}

	private void createStudent(StudentDAO studentDAO, Student theStudent) {
		studentDAO.save(theStudent);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id primary key
		int theId = 10;
		System.out.println(theId);
		Student theStudent = studentDAO.findById(theId);
		System.out.println(theStudent);

		// change first name to "data"
		theStudent.setFirstName("Tu");

		// update the student
		studentDAO.update(theStudent);

		// display the updated student
		System.out.println(theStudent);
	}

	private void deleteStudentById(StudentDAO studentDAO) {
		int theId = 2;
		studentDAO.delete(theId);
		System.out.println("Success");
	}

	private void deleteAll(StudentDAO studentDAO) {
		int numRowDel = studentDAO.deleteAll();
		System.out.println("Success: " + numRowDel);
	}

}
