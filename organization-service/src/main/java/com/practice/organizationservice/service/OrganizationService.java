package com.practice.organizationservice.service;

import com.practice.organizationservice.dtos.OrganizationDto;
import com.practice.organizationservice.entities.Organization;
import com.practice.organizationservice.repositories.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Organization getEntity(OrganizationDto organizationDto){
        return modelMapper.map(organizationDto,Organization.class);
    }

    private OrganizationDto getDto(Organization organization){
        return modelMapper.map(organization,OrganizationDto.class);
    }


    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Optional<Organization> organizationOptional= organizationRepository.findByOrganizationCode(organizationDto.getOrganizationCode());
        if (organizationOptional.isEmpty()) {
            return getDto(organizationRepository.save(getEntity(organizationDto)));
        }
        throw new RuntimeException("Organization Already Exists");
    }

    public OrganizationDto getOrganizationByCode(String code) {
        Optional<Organization> organizationOptional= organizationRepository.findByOrganizationCode(code);
        if (organizationOptional.isPresent()) {
            return getDto(organizationOptional.get());
        }
        throw new RuntimeException("Organization Doesn't Exists with Code:- "+ code);
    }
}
