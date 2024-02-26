package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);

            createMultiPleStudent(studentDAO);

            //readStudent(studentDAO);

            //readAllStudent(studentDAO);

            //readStudentByLastname(studentDAO);

            //updateStudent(studentDAO);

            //deleteStudent(studentDAO);

            //deleteAllStudent(studentDAO);

        };
    }

    private void deleteAllStudent(StudentDAO studentDAO) {
        System.out.println("Deleting all student......");
        int row=  studentDAO.deleteAll();
        System.out.println("Deleted row count "+ row);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId= 3;

        System.out.println("deleting student with id "+ studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        //retrieve student based on id
        int Id= 2;
        System.out.println("getting student id " + Id);
        Student student= studentDAO.findById(Id);

        //change first name to another
        System.out.println("update student....");
        student.setFirstname("Krishna");

        //update the student
        studentDAO.update(student);

        //display the updated student
        System.out.println(student);
    }

    private void readStudentByLastname(StudentDAO studentDAO) {
        List<Student> students= studentDAO.findByLastName("Kadam");

        for (Student student: students) {
            System.out.println(student);
        }
    }

    private void readAllStudent(StudentDAO studentDAO) {
        List<Student> students= studentDAO.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("creating student");
        Student student= new Student("Ram", "Rao", "ram.rao@zoh.com");

        //save student
        System.out.println("saving student");
        studentDAO.save(student);

        //display saved student id
        System.out.println("saved student id :" + student.getId());

        //retrieve student based on id
        System.out.println("retrieving student with id "+ student.getId());
        Student student1= studentDAO.findById(student.getId());

        //display student
        System.out.println("found the student "+ student1);
    }

    private void createMultiPleStudent(StudentDAO studentDAO) {
        //create multiple student
        System.out.println("creating 3 new student....");
        Student tmpstudent1= new Student("Rak", "Phile", "raks.phile@yahoo.com");
        Student tmpstudent2= new Student("Viru", "Sarak", "viru.sarak@gmail.com");
        Student tmpstudent3= new Student("Shri", "Nattu", "shri.nattu@gmail.com");

        //save the student objects
        System.out.println("saving student....");
        studentDAO.save(tmpstudent1);
        studentDAO.save(tmpstudent2);
        studentDAO.save(tmpstudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("creating new student....");
        Student tmpstudent= new Student("Raju", "Kadam", "raju.kadam@outlook.com");

        //save student object
        System.out.println("saving student....");
        studentDAO.save(tmpstudent);

        //display id of the saved student
        System.out.println("saved student, generated student Id "+ tmpstudent.getId());
    }
}
