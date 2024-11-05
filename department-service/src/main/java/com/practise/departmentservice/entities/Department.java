package com.practise.departmentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "departmentName", unique = true, nullable = false)
    private String departmentName;
    private String departmentDescription;
    @Column(name = "departmentCode", unique = true , nullable = false)
    private String departmentCode;
}
