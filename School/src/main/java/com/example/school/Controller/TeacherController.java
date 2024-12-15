package com.example.school.Controller;

import com.example.school.DTO.TeacherDTO;
import com.example.school.Model.Teacher;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/get-all")
    public ResponseEntity getAllTeachers() {
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getTeacherById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(teacherService.getTeacherById(id));
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body("Teacher added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO teacherDTO) {
        teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.status(200).body("Teacher updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body("Teacher deleted successfully");
    }
}
