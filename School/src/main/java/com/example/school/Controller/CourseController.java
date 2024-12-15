package com.example.school.Controller;

import com.example.school.ApiResponse.ApiResponse;
import com.example.school.DTO.CourseDTO;
import com.example.school.Model.Course;
import com.example.school.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("course added"));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllCourse(){
        return ResponseEntity.status(200).body(courseService.getAll());

    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getCourseById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(courseService.getById(id));

    }
    @GetMapping("/get-student-id/{id}")
    public ResponseEntity getStudentByCourseById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(courseService.getStudentByCourseId(id));

    }
    @PutMapping("/assign/teacher/{teacher_id}/{course_id}")
    public ResponseEntity assignTeacher(@PathVariable Integer teacher_id,@PathVariable Integer course_id){
        courseService.assignCourseToTeacher(teacher_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("course assigned"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBranch(@PathVariable Integer id, @RequestBody @Valid CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.status(200).body("updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body("deleted");
    }
    @PutMapping("/assign/student/{course_id}/{student_id}")
    public ResponseEntity assignStudent(Integer course_id, Integer student_id) {
        courseService.assignCourseToStudent(student_id, course_id);
        return ResponseEntity.status(200).body("course assigned");
    }



}
