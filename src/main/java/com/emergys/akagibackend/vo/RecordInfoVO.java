package com.emergys.akagibackend.vo;

import com.emergys.akagibackend.model.Employee;

public class RecordInfoVO {

    private Integer idEmployee;
    private Integer idCompany;
    private String company;
    private String name;
    private String emailAddress;

    public RecordInfoVO(Employee employee) {
        this.idEmployee = employee.getId();
        this.idCompany = employee.getCompany().getId();
        this.company = employee.getCompany().getCompanyName();
        this.name = employee.getFullName();
        this.emailAddress = employee.getEmail();
    }

    public RecordInfoVO() {
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
