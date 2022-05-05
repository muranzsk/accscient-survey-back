package com.emergys.akagibackend.vo;

import com.emergys.akagibackend.model.Company;
import com.emergys.akagibackend.model.Employee;
import com.emergys.akagibackend.model.Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DashboardVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idUser;
    private String userAddress;
    private List<ReducedEmployeeVO> employeeList;
    private Integer totalYes;
    private Integer totalNo;
    private Integer totalNotYet;

    public DashboardVO(Users user, List<Employee> fullEmployeeList) {
        this.idUser = user.getId();
        this.userAddress = user.getEmail();
        this.employeeList = this.transformEmployeeList(fullEmployeeList);
        this.getTotal(fullEmployeeList);
    }

    public List<ReducedEmployeeVO> transformEmployeeList(List<Employee> fullEmployeeList) {
        List<ReducedEmployeeVO> result = new ArrayList<>();
        fullEmployeeList.forEach(employee -> result.add(new ReducedEmployeeVO(employee)));
        return result;
    }

    public void getTotal(List<Employee> employeeList) {
        this.totalYes = 0;
        this.totalNo = 0;
        this.totalNotYet = 0;
        for(int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getVaccineStatus() == 0) {
                this.totalNotYet++;
            } else if(employeeList.get(i).getVaccineStatus() > 0) {
                this.totalYes++;
            } else {
                this.totalNo++;
            }
        }
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public List<ReducedEmployeeVO> getEmployeeList() {
        return employeeList;
    }

    public Integer getTotalYes() {
        return totalYes;
    }

    public Integer getTotalNo() {
        return totalNo;
    }

    public Integer getTotalNotYet() {
        return totalNotYet;
    }
}
