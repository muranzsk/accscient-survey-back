package com.emergys.akagibackend.repository;

import com.emergys.akagibackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByCompany(Object company);

    Employee findByInviteLink(String inviteLink);

    List<Employee> findByVaccineStatus(Integer vaccineStatus);

    List<Employee> findByEmail(String email);

}
