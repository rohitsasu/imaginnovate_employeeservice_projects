package org.com.code.EmployeeService.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class EmployeeDetails {

    private String doj;
    private String employeeId;
    private String firstName;
    private String lastName;
    private Double salary;

}
