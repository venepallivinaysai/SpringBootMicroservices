package com.practise.employeeservice.repository;

import com.practise.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmailIgnoreCaseContaining(String nameOrEmail);
}
