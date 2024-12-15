package com.example.school.Controller;

import com.example.school.DTO.AddressDTO;
import com.example.school.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;



    @PostMapping("/add")
    public ResponseEntity addAddress(@RequestBody @Valid AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body("Address added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAddress(@PathVariable Integer id, @RequestBody @Valid AddressDTO addressDTO) {
        addressService.updateAddress(id, addressDTO);
        return ResponseEntity.status(200).body("Address updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body("Address deleted successfully");
    }

    @GetMapping("/get-by-teacher-id/{teacherId}")
    public ResponseEntity getAddressByTeacherId(@PathVariable Integer teacherId) {
        return ResponseEntity.status(200).body(addressService.getAddressByTeacherId(teacherId));
    }
    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(addressService.getAll());
    }
}
