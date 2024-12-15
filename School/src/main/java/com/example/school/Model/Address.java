package com.example.school.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private Integer id;
    @NotEmpty(message = "the address area cant be empty")
    @Size(max = 50,message = "area cant be more than 50")
    @Column(columnDefinition = "varchar(50) not null")
    private String area;
    @NotEmpty(message = "the address street cant be empty")
    @Size(max = 50,message = "area cant be more than 150")
    @Column(columnDefinition = "varchar(150) not null")
    private String street;
    @NotNull(message = "the building Number cant be empty")
    @Column(columnDefinition = "int not null")
    private Integer buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}
