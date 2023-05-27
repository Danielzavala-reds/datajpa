package com.reds.sprinboot.datajpa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reds.sprinboot.datajpa.app.services.IUploadFileService;

@SpringBootApplication
public class DatajpaApplication implements CommandLineRunner{

	@Autowired
	IUploadFileService iUploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(DatajpaApplication.class, args);
	}

	/* Mètodo para inicializar de forma automatica la ruta en donde se guardaran los archivos, crea el directorio uploads */
	@Override
	public void run(String... args) throws Exception {
	
		iUploadFileService.deleteAll();

		iUploadFileService.init();
	}

}
