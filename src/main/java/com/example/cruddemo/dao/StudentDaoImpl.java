package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDAO {
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager with constructor
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class , id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery= entityManager.createQuery("FROM Student order by firstname" , Student.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> typedQuery= entityManager.createQuery("FROM Student where lastname=: theData" , Student.class);

        //set parameter
        typedQuery.setParameter("theData" , lastName);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        //retrieve student with Id
        Student student= entityManager.find(Student.class , id);

        //delete student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numsRowDeleted= entityManager.createQuery("DELETE from Student").executeUpdate();

        return numsRowDeleted;
    }
}
