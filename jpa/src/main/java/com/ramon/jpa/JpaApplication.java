package com.ramon.jpa;

import com.ramon.jpa.app.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

	@Autowired
	IUploadFileService iUploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	//Con CommandLineRunner podemos implementar el metodo run para hacer cosas antes de que la app inicie
	@Override
	public void run(String... args) throws Exception {
		iUploadFileService.deleteAll();
		iUploadFileService.init();
	}
}