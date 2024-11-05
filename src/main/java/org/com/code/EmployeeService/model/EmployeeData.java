package org.com.code.EmployeeService.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployeeData {


    @NotEmpty(message = "Employee ID cannot be empty")
    private String employeeId;
    @NotEmpty(message = "Employee First Name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Employee Last cannot be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email format is wrong" )
    private String email;
    @NotEmpty(message = "PhoneNumbers cannot be empty")
    private List<Integer> phoneNumbers;
    @NotNull(message = "Date Of joining cannot be empty")
    private LocalDateTime doj;
    @NotNull(message = "Salary cannot be empty")
    private Double salary;

}
