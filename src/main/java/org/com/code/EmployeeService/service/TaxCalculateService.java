package org.com.code.EmployeeService.service;

import org.com.code.EmployeeService.model.EmployeeDetails;
import org.com.code.EmployeeService.model.TaxResponseData;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class TaxCalculateService {


    public TaxResponseData computetax(EmployeeDetails employeeDetails) throws ParseException {


        // 12
        //
        final var salary = employeeDetails.getSalary();
        final var doj = employeeDetails.getDoj();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final var dateofJoining = simpleDateFormat.parse(doj);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateofJoining);
        final var joiningYear = calendar.get(Calendar.YEAR);
        final var joiningMonth = calendar.get(Calendar.MONTH);
        Date endOfTheYear = null;
        if (joiningMonth >= 3) {
            int year = joiningYear + 1;
            endOfTheYear = simpleDateFormat.parse("31/03/" + year);
        } else {
            endOfTheYear = simpleDateFormat.parse("31/03/" + joiningYear);
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endOfTheYear);


        int yearsInBetween = calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        int monthsDiff = calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
        long ageInMonths = (yearsInBetween * 12 + monthsDiff) + 1;


        var salaryPerMonth = salary / 12;

        var ti = salaryPerMonth * ageInMonths;
        double total_tax = 0;
        double total_cess = 0;
        double tax1=0, tax2=0, tax3=0;
        if (ti > 300000) { // slab 1
            double amt = 0;
            if ((ti - 300000) > 200000) // if ti > slab 1
                amt = 200000;
            else
                amt = ti - 300000;
            tax1 = (0.1 * amt); // total tax in slab 1
            total_tax += tax1;
            System.out.println("Tax Payable for slab 300,000-500,000: " + tax1);
        }
        if (ti > 500000) { // slab 2
            double amt = 0;
            if ((ti - 500000) > 500000) // if ti > slab 2
                amt = 500000;
            else
                amt = ti - 500000;
            tax2 = 0.2 * amt; // total tax in slab 2
            total_tax += tax2;
            System.out.println("Tax Payable for slab 500,000-1,000,000: " + tax2);
        }
        if (ti > 1000000) { // slab 3
            tax3 = 0.3 * (ti - 1000000); // total tax in slab 3
            total_tax += tax3;
            System.out.println("Tax Payable for slab 1,000,000-above: " + tax3);
        }
        total_cess = 0.03 * total_tax; // all slabs have same 3% cess
        System.out.println("Total tax = "+tax1+" + "+tax2+" + "+tax3+" = "+total_tax);
        System.out.println("Total cess = 3% of income tax = " + total_cess);

        TaxResponseData taxResponseData = new TaxResponseData();
        taxResponseData.setTaxAmount(total_tax);
        taxResponseData.setCessAmount(total_cess);
        taxResponseData.setEmployeeId(employeeDetails.getEmployeeId());
        taxResponseData.setFirstName(employeeDetails.getFirstName());
        taxResponseData.setLastName(employeeDetails.getLastName());
        return taxResponseData;


    }
}
