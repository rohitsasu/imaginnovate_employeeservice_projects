package org.com.code.EmployeeService.controller;

import org.com.code.EmployeeService.model.EmployeeData;
import org.com.code.EmployeeService.model.EmployeeDetails;
import org.com.code.EmployeeService.model.TaxResponseData;
import org.com.code.EmployeeService.service.TaxCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/app")
public class EmployeeController {


    @Autowired
    private TaxCalculateService taxCalculateService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveEmployeeDetails(@RequestBody @Valid EmployeeData employeeData) {

        if (employeeData == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(employeeData, HttpStatus.OK);


    }

    @PostMapping(value = "calculateTax", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaxResponseData> calculateTax(@RequestBody EmployeeDetails employeeDetails) throws ParseException {

        final var computetax = taxCalculateService.computetax(employeeDetails);
        return new ResponseEntity<>(computetax,HttpStatus.OK);


    }
}
