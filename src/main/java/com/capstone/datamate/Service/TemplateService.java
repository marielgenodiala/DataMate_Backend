package com.capstone.datamate.Service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.datamate.Entity.TemplateEntity;
import com.capstone.datamate.Repository.TemplateRepository;



@Service
public class TemplateService {
    @Autowired
    TemplateRepository tempRepo;

    public TemplateEntity store(MultipartFile template) throws IOException {
        String tempName = StringUtils.cleanPath(template.getOriginalFilename());
        TemplateEntity Template = new TemplateEntity(tempName, template.getBytes());
    
        return tempRepo.save(Template);
    }

    public TemplateEntity getTemplate(int id) {
        return tempRepo.findById(id).get();
      }
      
    public Stream<TemplateEntity> getAllTemplates() {
        return tempRepo.findAll().stream();
      }



}
