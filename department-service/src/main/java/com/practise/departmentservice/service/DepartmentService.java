package com.practise.departmentservice.service;

import com.practise.departmentservice.dto.DepartmentDto;
import com.practise.departmentservice.entities.Department;
import com.practise.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public DepartmentDto saveDepartment(DepartmentDto departmentDto){
        return toDto(departmentRepository.save(toEntity(departmentDto)));
    }

    private Department toEntity(DepartmentDto departmentDto){
        return modelMapper.map(departmentDto,Department.class);
    }

    private DepartmentDto toDto(Department department){
        return modelMapper.map(department,DepartmentDto.class);
    }

    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department= departmentRepository.findByDepartmentCodeIgnoreCase(departmentCode);
        return toDto(department);
    }

    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Optional<Department> department= departmentRepository.findByDepartmentNameIgnoreCase(departmentDto.getDepartmentName());
        if (department.isPresent()) {
            Department updatedDepartment = department.get();
            updatedDepartment.setDepartmentName(departmentDto.getDepartmentName());
            updatedDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
            updatedDepartment.setDepartmentCode(departmentDto.getDepartmentCode());

            return toDto(departmentRepository.save(updatedDepartment));
        }
        return toDto(departmentRepository.save(toEntity(departmentDto)));
    }


    public void deleteDepartmentByName(String departmentName) {
        Optional<Department> department= departmentRepository.findByDepartmentNameIgnoreCase(departmentName);

        if(department.isPresent()){
            departmentRepository.delete(department.get());
        }
        else {
            throw new RuntimeException("Department Not Found with Name :- "+ departmentName);
        }
    }
}
