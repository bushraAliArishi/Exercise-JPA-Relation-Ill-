package com.example.school.Repository;

import com.example.school.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address>findAll();
    Address findAddressByTeacher_Id(Integer teacherId);
    Address findAddressById(Integer id);
}
