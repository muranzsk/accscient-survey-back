package com.emergys.akagibackend.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "id_company")
    private Company company;

    @Column(name = "invite_link")
    private String inviteLink;

    @Column(name = "id_file")
    private Integer idFile;

    @Column(name = "vaccine_status")
    private Integer vaccineStatus;

    @Column(name = "record_date")
    private String recordDate;

    @Column(name = "card_link")
    private String cardLink;

    public Employee(String fullName, String email, Company company, String inviteLink, Integer idFile,
                    Integer vaccineStatus, String recordDate, String cardLink) {
        this.fullName = fullName;
        this.email = email;
        this.company = company;
        this.inviteLink = inviteLink;
        this.idFile = idFile;
        this.vaccineStatus = vaccineStatus;
        this.recordDate = recordDate;
        this.cardLink = cardLink;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public Integer getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(Integer vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setCardLink(String cardLink) {
        this.cardLink = cardLink;
    }

    public String getCardLink() {
        return this.cardLink;
    }

    public void currentTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        this.recordDate = formatter.format(date);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", company=" + company +
                ", inviteLink='" + inviteLink + '\'' +
                ", idFile=" + idFile +
                ", vaccineStatus=" + vaccineStatus +
                ", recordDate='" + recordDate + '\'' +
                ", cardLink='" + cardLink + '\'' +
                '}';
    }
}
