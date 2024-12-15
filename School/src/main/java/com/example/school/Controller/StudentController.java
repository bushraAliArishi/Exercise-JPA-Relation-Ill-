package com.example.school.Controller;

import com.example.school.DTO.StudentDTO;
import com.example.school.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.status(200).body("Student added successfully");
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getStudentById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(studentService.getStudentById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid StudentDTO studentDTO) {
            studentService.updateStudent(id, studentDTO);
            return ResponseEntity.status(200).body("Student updated successfully");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
            studentService.deleteStudent(id);
            return ResponseEntity.status(200).body("Student deleted successfully");

    }
}
