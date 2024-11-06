package com.practise.employeeservice.service;

import com.practise.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface APIOrganizationClient {

    @GetMapping("api/organization/{code}")
    OrganizationDto getOrganizationByCode(@PathVariable String code);

}
