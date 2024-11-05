package org.com.code.EmployeeService.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TaxResponseData {

    private String employeeId;
    private String firstName;
    private String lastName;
    private Double yearySalary;
    private Double taxAmount;
    private Double cessAmount;
}
