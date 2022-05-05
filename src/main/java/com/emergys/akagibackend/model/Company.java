package com.emergys.akagibackend.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @ManyToMany(mappedBy = "companies")
    private Set<Users> users;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company(String companyName, Set<Users> users) {
        this.companyName = companyName;
        this.users = users;
    }

    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
