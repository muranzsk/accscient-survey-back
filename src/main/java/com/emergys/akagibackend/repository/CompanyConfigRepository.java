package com.emergys.akagibackend.repository;

import com.emergys.akagibackend.model.CompanyConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyConfigRepository extends JpaRepository<CompanyConfig, Integer> {

    CompanyConfig findByCompany(Object company);

}
