package com.practise.employeeservice.service;

import com.practise.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/department/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);

}
