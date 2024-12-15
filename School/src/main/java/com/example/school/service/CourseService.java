package com.example.school.service;

import com.example.school.ApiResponse.ApiException;
import com.example.school.DTO.CourseDTO;
import com.example.school.DTO.StudentDTO;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Model.Teacher;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.StudentRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<CourseDTO> getAll(){
        List<CourseDTO>courseDTOS=new ArrayList<>();
        for (Course c : courseRepository.findAll()) {
            List<StudentDTO>studentDTOS=studentService.convertListOfStudents(c.getStudentSet());
            CourseDTO courseDTO=new CourseDTO(c.getName(),c.getTeacher().getName(),c.getTeacher().getId(),studentDTOS);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;

    }
    public CourseDTO getById(Integer id){
        Course course=courseRepository.findCourseById(id);
        CourseDTO courseDTO=new CourseDTO(course.getName(),course.getTeacher().getName(),course.getTeacher().getId(),studentService.convertListOfStudents(course.getStudentSet()));
        return courseDTO;
    }
    public List<StudentDTO> getStudentByCourseId(Integer id){
        Course course=courseRepository.findCourseById(id);
        List<StudentDTO>studentDTOS=new ArrayList<>();
        for (Student s :course.getStudentSet() ) {
            StudentDTO studentDTO=new StudentDTO(s.getName(),s.getMajor(),s.getAge());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
    public void addCourse (Course course){
        courseRepository.save(course);
    }
    public void assignCourseToTeacher(Integer teacher_id,Integer course_id){
        Teacher teacher=teacherRepository.findTeacherById(teacher_id);
        Course course =courseRepository.findCourseById(course_id);

        if (teacher==null || course==null){

            throw new ApiException("cant assign");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }
    public void updateCourse(Integer id, CourseDTO courseDTO){
        Course updatedCourse =courseRepository.findCourseById(id);
        if (updatedCourse==null){
            throw new ApiException(" not found ");
        }
        updatedCourse.setTeacher(teacherRepository.findTeacherById(courseDTO.getTeacher_id()));
        updatedCourse.setName(courseDTO.getName());
        courseRepository.save(updatedCourse);
    }
    public void deleteCourse(Integer id){
        Course updatedCourse =courseRepository.findCourseById(id);
        if (updatedCourse==null){
            throw new ApiException(" not found ");
        }

        courseRepository.delete(updatedCourse);
    }
    public void assignCourseToStudent(Integer studentId, Integer courseId) {
        Student student = null;
        Course course = null;

        for (Student s : studentRepository.findAll()) {
            if (s.getId()==(studentId)) {
                student = s;
                break;
            }
        }
        if (student == null) {
            throw new ApiException("Can't assign - Student not found");
        }

        for (Course c : courseRepository.findAll()) {
            if (c.getId()==(courseId)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            throw new ApiException("Can't assign - Course not found");
        }

        if (course.getStudentSet() == null) {
            throw new ApiException("Student set not initialized");
        }

        course.getStudentSet().add(student);

        if (student.getCourseSet() == null) {
            throw new ApiException("Course set not initialized");
        }

        student.getCourseSet().add(course);

        courseRepository.save(course);
        studentRepository.save(student);
    }


}
