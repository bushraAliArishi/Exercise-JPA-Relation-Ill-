package com.example.school.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "student name cant be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;
    @NotNull(message = "student age cant be empty")
    @Min(value = 18, message ="student cant enter when age under 18" )
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty(message = "student major cant be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String major;
    @ManyToMany
    @JsonIgnore
    private Set<Course>courseSet;

}
