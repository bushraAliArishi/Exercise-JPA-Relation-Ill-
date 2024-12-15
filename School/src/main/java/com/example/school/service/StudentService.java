package com.example.school.service;

import com.example.school.ApiResponse.ApiException;
import com.example.school.DTO.StudentDTO;
import com.example.school.Model.Student;
import com.example.school.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            StudentDTO studentDTO = new StudentDTO(student.getName(), student.getMajor(),student.getAge());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
    public List<StudentDTO> convertListOfStudents(Set<Student> students) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getName(), student.getMajor(),student.getAge());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("not found");
        }
        return new StudentDTO(student.getName(), student.getMajor(),student.getAge());
    }
    public StudentDTO getStudentByCourseId(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("not found");
        }
        return new StudentDTO(student.getName(), student.getMajor(),student.getAge());
    }
    public void addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setMajor(studentDTO.getMajor());
        student.setAge(studentDTO.getAge());
        studentRepository.save(student);
    }
    public void updateStudent(Integer id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findStudentById(id);
        if (existingStudent == null) {
            throw new ApiException("Student not found");
        }
        existingStudent.setName(studentDTO.getName());
        existingStudent.setMajor(studentDTO.getMajor());
        studentRepository.save(existingStudent);
    }
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        studentRepository.delete(student);
    }
    public void changeMajor(Integer student_id,String major){
       Student student= studentRepository.findStudentById(student_id);
        if (student==null){
            throw new ApiException("student not found");
        }
        student.getCourseSet().remove(student.getCourseSet());
        student.setMajor(major);
        studentRepository.save(student);

    }
}
