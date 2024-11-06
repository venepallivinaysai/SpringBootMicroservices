package com.practise.employeeservice.service;

import com.practise.employeeservice.dto.APIResponseDto;
import com.practise.employeeservice.dto.DepartmentDto;
import com.practise.employeeservice.dto.EmployeeDto;
import com.practise.employeeservice.dto.OrganizationDto;
import com.practise.employeeservice.entity.Employee;
import com.practise.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private RestTemplate restTemplate;
//    private WebClient.Builder webClient;
    private APIClient apiClient;
    private APIOrganizationClient apiOrganizationClient;


    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return toDto(employeeRepository.save(toEntity(employeeDto)));
    }

    private Employee toEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }

    private EmployeeDto toDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultEmployee")
    public APIResponseDto getEmployee(String email) {
        Optional<Employee> employee = employeeRepository.findByEmailIgnoreCaseContaining(email);
        if(employee.isPresent()){
            // Get Department from department MicroService
            // By Using RestTemplate
//            ResponseEntity<DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/department/"+employee.get().getDepartmentCode(), DepartmentDto.class);
//
//            DepartmentDto department = response.getBody();

//             By using WebClient
//            DepartmentDto department= webClient.build().get()
//                    .uri("http://localhost:8080/api/department/"+employee.get().getDepartmentCode())
//                    .retrieve()
//                    .bodyToMono(DepartmentDto.class)
//                    .block();

            // By Using spring cloud open Feign
            DepartmentDto department= apiClient.getDepartmentByCode(employee.get().getDepartmentCode());

            OrganizationDto organization= apiOrganizationClient.getOrganizationByCode(employee.get().getOrganizationCode());
            return new APIResponseDto(toDto(employee.get()),department, organization);
        }
        else{
            throw new RuntimeException("No Employee Exists with the provided Details");
        }
    }

    public APIResponseDto getDefaultEmployee(String email, Exception exception){
        Optional<Employee> employee = employeeRepository.findByEmailIgnoreCaseContaining(email);
        if(employee.isPresent()){
            DepartmentDto department= new DepartmentDto(2L,"Default","Default","Default");
            OrganizationDto organization= new OrganizationDto(2L,"Default","Default","Default");
            return new APIResponseDto(toDto(employee.get()),department,organization);
        }
        else{
            throw new RuntimeException("No Employee Exists with the provided Details");
        }
    }
}
