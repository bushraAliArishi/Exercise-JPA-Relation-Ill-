package com.example.school.service;

import com.example.school.DTO.TeacherDTO;
import com.example.school.Model.Teacher;
import com.example.school.Repository.AddressRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher>teachers=teacherRepository.findAll();
        List<TeacherDTO>teacherDTOS=new ArrayList<>();
        for (Teacher t : teachers) {
            TeacherDTO teacherDTO=new TeacherDTO(t.getName(),t.getAge(),t.getEmail());
            teacherDTOS.add(teacherDTO);
        }
        return teacherDTOS;
    }
    public TeacherDTO getTeacherById(Integer id) {
        TeacherDTO teacherDTO=new TeacherDTO(teacherRepository.findTeacherById(id).getName(),
                teacherRepository.findTeacherById(id).getId(),teacherRepository.findTeacherById(id).getEmail());
        return teacherDTO;
    }
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    public void updateTeacher(Integer id, TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherRepository.findTeacherById(id);
        if (existingTeacher == null) {
            throw new RuntimeException("Teacher not found");
        }
        existingTeacher.setName(teacherDTO.getName());
        existingTeacher.setAge(teacherDTO.getAge());
        existingTeacher.setEmail(teacherDTO.getEmail());
        teacherRepository.save(existingTeacher);
    }
    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.delete(teacher);
        addressRepository.delete(addressRepository.findAddressByTeacher_Id(teacher.getId()));
    }
}
