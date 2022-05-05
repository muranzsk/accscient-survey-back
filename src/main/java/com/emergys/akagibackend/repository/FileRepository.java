package com.emergys.akagibackend.repository;

import com.emergys.akagibackend.model.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileStorage, Integer> {

}
