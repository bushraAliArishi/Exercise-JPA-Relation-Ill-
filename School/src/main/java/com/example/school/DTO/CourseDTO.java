package com.example.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String name;
    private String teacher_Name;
    private Integer teacher_id;
    private List<StudentDTO>studentDTOS;

}
