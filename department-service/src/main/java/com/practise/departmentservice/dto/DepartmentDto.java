package com.practise.departmentservice.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private long id;
    @NotEmpty
    private String departmentName;
    @NotEmpty
    private String departmentDescription;
    @NotEmpty
    private String departmentCode;
}
