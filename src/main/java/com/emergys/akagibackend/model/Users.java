package com.emergys.akagibackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "psswrd")
    private String psswrd;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "user_company",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_company")}
    )
    private Set<Company> companies;

    public Users() {
    }

    public Users(String fullName, String psswrd, String email) {
        this.fullName = fullName;
        this.psswrd = psswrd;
        this.email = email;
    }

    public Users(String fullName, String psswrd, String email, Set<Company> companies) {
        this.fullName = fullName;
        this.psswrd = psswrd;
        this.email = email;
        this.companies = companies;
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

    public String getPsswrd() {
        return psswrd;
    }

    public void setPsswrd(String psswrd) {
        this.psswrd = psswrd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", psswrd='" + psswrd + '\'' +
                ", email='" + email + '\'' +
                ", companies=" + companies +
                '}';
    }
}
