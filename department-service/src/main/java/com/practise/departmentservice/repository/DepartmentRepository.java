package com.practise.departmentservice.repository;

import com.practise.departmentservice.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Department findByDepartmentCodeIgnoreCase(String departmentCode);

    Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);
}
