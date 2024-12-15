package com.example.school.service;

import com.example.school.DTO.AddressDTO;
import com.example.school.Model.Address;
import com.example.school.Model.Teacher;
import com.example.school.Repository.AddressRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacherId());
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }

        Address address = new Address();
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        address.setTeacher(teacher);

        addressRepository.save(address);
    }
    public void updateAddress(Integer addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Address not found");
        }

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
    }
    public void deleteAddress(Integer addressId) {
        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Address not found");
        }
        addressRepository.delete(address);
    }
    public AddressDTO getAddressByTeacherId(Integer teacherId) {
        AddressDTO addressDTO=new AddressDTO();
        addressDTO.setArea(addressRepository.findAddressByTeacher_Id(teacherId).getArea());
        addressDTO.setStreet(addressRepository.findAddressByTeacher_Id(teacherId).getStreet());
        addressDTO.setBuildingNumber(addressRepository.findAddressByTeacher_Id(teacherId).getBuildingNumber());
        return addressDTO;
    }
    public List<AddressDTO>getAll(){
        List<Address>addresses=addressRepository.findAll();
        List<AddressDTO>addressDTOS=new ArrayList<>();
        for (Address a:addresses) {
            AddressDTO addressDTO=new AddressDTO(a.getArea(),a.getStreet(),a.getBuildingNumber(),a.getTeacher().getId());
            addressDTOS.add(addressDTO);
        }
        return addressDTOS;
    }
}
