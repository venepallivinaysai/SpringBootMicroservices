package com.practise.employeeservice.controller;

import com.practise.employeeservice.dto.APIResponseDto;
import com.practise.employeeservice.dto.EmployeeDto;
import com.practise.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("{email}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("email") String email){
        return ResponseEntity.ok(employeeService.getEmployee(email));
    }


}
