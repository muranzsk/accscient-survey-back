package com.emergys.akagibackend.vo;

import com.emergys.akagibackend.model.Employee;

public class ReducedEmployeeVO {

    private String name;
    private String email;
    private String companyName;
    private String status;
    private String cardLink;
    private String recordDate;

    public ReducedEmployeeVO(Employee employee) {

        this.name = employee.getFullName();
        this.email = employee.getEmail();
        this.companyName = employee.getCompany().getCompanyName();
        if(employee.getVaccineStatus() == 0) {
            this.status = "Pending";
        } else if(employee.getVaccineStatus() > 0) {
            this.status = "Yes";
        } else {
            this.status = "No";
        }
        this.cardLink = employee.getCardLink();
        this.recordDate = employee.getRecordDate();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStatus() {
        return status;
    }

    public String getCardLink() {
        return cardLink;
    }

    public String getRecordDate() {
        return recordDate;
    }
}
