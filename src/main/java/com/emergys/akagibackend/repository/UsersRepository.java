package com.emergys.akagibackend.repository;

import com.emergys.akagibackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

    Users findByPsswrd(String psswrd);

}
