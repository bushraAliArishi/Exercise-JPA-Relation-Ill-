package com.example.school.Repository;

import com.example.school.Model.Address;
import com.example.school.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    List<Teacher> findAll();
    List<Teacher>findTeachersByAddress(Address address);
    Teacher findTeacherById(Integer id);

}
