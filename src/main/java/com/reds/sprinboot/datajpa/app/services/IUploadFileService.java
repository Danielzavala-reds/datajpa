package com.reds.sprinboot.datajpa.app.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.support.MultipartFilter;

public interface IUploadFileService {
    
    public Resource load(String filename);

    public String copy(MultipartFilter file);

    public boolean delete(String filename);

}
