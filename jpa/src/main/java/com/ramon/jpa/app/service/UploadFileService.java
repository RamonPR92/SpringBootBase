package com.ramon.jpa.app.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileService implements IUploadFileService {

    private final String UPLOADS = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        Path pathFoto = Paths.get(UPLOADS).resolve(fileName).toAbsolutePath();
        Resource recurso = null;

        recurso = new UrlResource(pathFoto.toUri());
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error no se puede cargar la imagen " + pathFoto.toString());
        }

        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFileName = null;
        //Genera un identificador unico con letras y numeros y concatena el nombre de la foto
        uniqueFileName = UUID.randomUUID().toString().concat("_").concat(file.getOriginalFilename());
        //root path es respecto a nivel del proyecto
        Path rootPath = Paths.get(UPLOADS).resolve(uniqueFileName);
        //absolute path es desde el disco
        Path rootAbsolutePath = rootPath.toAbsolutePath();
        Files.copy(file.getInputStream(), rootAbsolutePath);
        return uniqueFileName;
    }

    @Override
    public void delete(String fileName) throws IOException {
        Path fotoRuta = Paths.get(UPLOADS).resolve(fileName).toAbsolutePath();
        Files.delete(fotoRuta);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS).toFile());
    }

    @Override
    public void init() throws IOException{
        Files.createDirectories(Paths.get(UPLOADS));
    }
}
