package com.reds.sprinboot.datajpa.app.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final static Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    private final static String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathPhoto = getPath(filename);
        log.info("pathPhoto: " + pathPhoto);

        Resource resource = null;
        resource = new UrlResource(pathPhoto.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen" + pathPhoto.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException{
      String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
      Path rootPath = getPath(uniqueFileName); /*
                                                                          * .resolve se encarga de concatenar al path
                                                                          * "uploads/nombreArchivo.jpg"
                                                                          */

      //Path absolutePath = rootPath.toAbsolutePath();
      log.info("rootPath: " + rootPath); /* Path relativo al proyecto */
    //log.info("AbsolutePath: " + absolutePath); /* Path absoluta - desde la raiz */

     
        // byte[] bytes = photo.getBytes();
        // Path pathComplete = Paths.get(rootPath + "/" + photo.getOriginalFilename());
        // Files.write(pathComplete, bytes);

        /* Alternativa a files.write que simplifica codigo */
        Files.copy(file.getInputStream(), rootPath);

        return uniqueFileName;
    }

    @Override
    public boolean delete(String filename) {
        Path rooPath = getPath(filename);
        File file = rooPath.toFile();

        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }

        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

}
