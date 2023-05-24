package com.capstone.datamate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.datamate.Entity.TemplateEntity;
import com.capstone.datamate.Message.ResponseMessage;
import com.capstone.datamate.Service.TemplateService;

@CrossOrigin("http://localhost:3000")
@RestController
public class TemplateController {
  @Autowired
  TemplateService tempServ;

  @PostMapping("/downloadTemplate/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
       tempServ.store(file);
        String message = file.getOriginalFilename() + " successfully uploaded!" ;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
        String message = file.getOriginalFilename() + " could not be uploaded!" + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/downloadTemplate/{id}")
  public ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
    TemplateEntity temp = tempServ.getTemplate(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + temp.getTemplateName() + "\"")
        .body(temp.getData());
  }

        //get list of templates
    //   @GetMapping("/files")
    //   public ResponseEntity<List<ResponseFile>> getListFiles() {
    //     List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
    //       String fileDownloadUri = ServletUriComponentsBuilder
    //           .fromCurrentContextPath()
    //           .path("/files/")
    //           .path(dbFile.getFileId()+"")
    //           .toUriString();
    
    //       return new ResponseFile(
    //           dbFile.getFileId(),
    //           dbFile.getFileName(),
    //           dbFile.getFileSize(),
    //           dbFile.getUploadDate(),
    //           dbFile.getLatestDateModified(),
    //           dbFile.isIsdeleted(),
    //           fileDownloadUri
    //           );
    //     }).collect(Collectors.toList());
    
    //     return ResponseEntity.status(HttpStatus.OK).body(files);
    //   }
}
