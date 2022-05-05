package com.emergys.akagibackend.mail;

import com.emergys.akagibackend.model.Company;
import com.emergys.akagibackend.model.Employee;

public class MailEmployee {

    private String fullName;
    private String emailAddress;
    private String invitationLink;
    private Company company;

    public MailEmployee(Employee employee) {
        this.emailAddress = employee.getEmail();
        this.fullName = employee.getFullName();
        this.invitationLink = employee.getInviteLink();
        this.company = employee.getCompany();
    }

    public MailEmployee() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getInvitationLink() {
        return invitationLink;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "MailEmployee{" +
                "fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", invitationLink='" + invitationLink + '\'' +
                ", company=" + company +
                '}';
    }
}
