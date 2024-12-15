package com.example.school.Repository;

import com.example.school.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findStudentById(Integer id);
    List<Student>findAll();

    List<Student>findStudentsByMajor(String major);
}
