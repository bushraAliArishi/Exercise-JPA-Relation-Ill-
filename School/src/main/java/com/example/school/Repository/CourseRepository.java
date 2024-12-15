package com.example.school.Repository;

import com.example.school.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.studentSet WHERE c.id = :id")
    Course findCourseByIdWithStudents(Integer id);

    Course findCourseById(Integer id);
    Course findCourseByName(String name);
}
