package com.example.school.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "teacher name cant be empty")
    @Size(min = 3,message = "minimum size is 3  ")
    @Pattern(regexp = "^[A-Za-z]+$",message = "name should only  have letters")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @NotNull(message = "age must not be empty ")
    @Min(value = 25,message = "age cant be less than 25")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @Email(message = "you must enter valid email ")
    @NotEmpty(message = "email  cant be empty")
    @Column(columnDefinition = "varchar(50)  not null")
    private String email;

    @PositiveOrZero(message = "salary cant be a negative number")
    @Column(columnDefinition = "double default 0 ")
    private Double salary=0.0;

    @OneToOne(cascade =CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade =CascadeType.ALL,mappedBy = "teacher")
    private Set<Course> course;


}
