package com.practise.departmentservice.controllers;

import com.practise.departmentservice.dto.DepartmentDto;
import com.practise.departmentservice.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping()
    public ResponseEntity<DepartmentDto> saveDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        DepartmentDto response= departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String departmentCode){
        DepartmentDto response= departmentService.getDepartmentByCode(departmentCode);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto response = departmentService.updateDepartment(departmentDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{departmentName}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String departmentName){
        departmentService.deleteDepartmentByName(departmentName);
        return ResponseEntity.ok("Department Deleted Successfully with name:-  "+ departmentName);
    }

}
