package com.emergys.akagibackend.vo;

import org.springframework.web.multipart.MultipartFile;

public class UpdateRecordVO {

    private Integer idEmployee;
    private Integer idFile;
    private Integer status;

    public UpdateRecordVO() {
    }

    public UpdateRecordVO(Integer idEmployee, Integer status, Integer idFile) {
        this.idEmployee = idEmployee;
        this.status = status;
        this.idFile = idFile;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }
    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdFile() {
        return idFile;
    }
    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    @Override
    public String toString() {
        return "UpdateRecordVO{" +
                "idEmployee=" + idEmployee +
                ", idFile=" + idFile +
                ", status=" + status +
                '}';
    }
}
