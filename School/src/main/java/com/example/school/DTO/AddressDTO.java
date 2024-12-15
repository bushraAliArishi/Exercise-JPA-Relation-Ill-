package com.example.school.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotEmpty(message = "The address area cannot be empty")
    @Size(max = 50, message = "Area cannot exceed 50 characters")
    private String area;

    @NotEmpty(message = "The address street cannot be empty")
    @Size(max = 150, message = "Street cannot exceed 150 characters")
    private String street;

    @NotNull(message = "The building number cannot be empty")
    private Integer buildingNumber;

    @NotNull(message = "The teacher ID cannot be null")
    private Integer teacherId;
}
