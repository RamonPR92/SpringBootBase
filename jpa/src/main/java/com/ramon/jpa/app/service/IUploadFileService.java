package com.ramon.jpa.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {

    Resource load(String fileName) throws MalformedURLException;
    String copy(MultipartFile file) throws IOException;
    void delete(String fileName) throws IOException;
    void deleteAll();
    void init() throws IOException;
}
