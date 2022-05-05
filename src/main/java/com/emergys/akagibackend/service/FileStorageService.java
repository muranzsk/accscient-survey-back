package com.emergys.akagibackend.service;

import com.emergys.akagibackend.model.FileStorage;
import com.emergys.akagibackend.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {


    @Autowired
    private FileRepository repo;

    public FileStorage store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileStorage fileStorage = new FileStorage(fileName, file.getContentType(),
                file.getBytes());
        return repo.save(fileStorage);
    }

    public FileStorage getFile(Integer id) {
        return repo.findById(id).get();
    }

    public Stream<FileStorage> getAllFiles() {
        return repo.findAll().stream();
    }
}
