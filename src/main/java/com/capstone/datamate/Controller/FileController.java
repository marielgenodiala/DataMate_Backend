package com.capstone.datamate.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capstone.datamate.Entity.FileEntity;
import com.capstone.datamate.Message.ResponseFile;
// import com.capstone.datamate.Message.ResponseMessage;
import com.capstone.datamate.Service.FileService;


@CrossOrigin("http://localhost:3000/")
@RestController
public class FileController {
    @Autowired
  FileService fileService;

  @PostMapping("/upload")
  public FileEntity uploadFile(@RequestParam("file") MultipartFile file) {
    try {
       FileEntity uploadedFile = fileService.store(file);
      return uploadedFile;
    } catch (Exception e) {
      System.out.println("Upload Error");
      return null;
    }
  }

  @PutMapping("/updateFile/{id}")
  public String updateFile(@PathVariable int id, @RequestParam("file") MultipartFile file){
    try{
       FileEntity resFile =  fileService.updateFile(id, file);
       if(resFile != null){
          System.out.println("File Update:" + resFile.getFileName());
          return "Update Successful!";
        }else{
          return "Update Failed!";
        }
       }catch(Exception e){
        System.out.println("Update Error");
        return "Update Failed!";
       }
    }
     

  @GetMapping("/downloadFile/{id}")
  public ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
    FileEntity file = fileService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
        .body(file.getData());
  }

  @GetMapping("/file")
  public FileEntity getFile(@RequestParam int id) {
    FileEntity file = fileService.getFile(id);
    if(file != null){
      return file;
    }else{
      System.out.println("File not found!");
      return null;
    }
  }

//  @GetMapping("/files")
//  public ResponseEntity<List<ResponseFile>> getListFiles() {
//    List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
//      String fileDownloadUri = ServletUriComponentsBuilder
//          .fromCurrentContextPath()
//          .path("/files/")
//          .path(dbFile.getFileId()+"")
//          .toUriString();
//
//      return new ResponseFile(
//          dbFile.getFileId(),
//          dbFile.getFileName(),
//          dbFile.getFileSize(),
//          dbFile.getUploadDate(),
//          dbFile.getLatestDateModified(),
//          dbFile.isIsdeleted(),
//          fileDownloadUri
//          );
//    }).collect(Collectors.toList());
//
//    return ResponseEntity.status(HttpStatus.OK).body(files);
//  }
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
      List<ResponseFile> files = fileService.getAllFiles()
              .filter(dbFile -> !dbFile.isIsdeleted()) // Filter out deleted files
              .map(dbFile -> {
                  String fileDownloadUri = ServletUriComponentsBuilder
                          .fromCurrentContextPath()
                          .path("/files/")
                          .path(dbFile.getFileId()+"")
                          .toUriString();

                  return new ResponseFile(
                          dbFile.getFileId(),
                          dbFile.getFileName(),
                          dbFile.getFileSize(),
                          dbFile.getUploadDate(),
                          dbFile.getLatestDateModified(),
                          dbFile.isIsdeleted(),
                          fileDownloadUri
                  );
              }).collect(Collectors.toList());

      return ResponseEntity.status(HttpStatus.OK).body(files);
  }

//  @DeleteMapping("/deleteFile/{id}")
//  public String deleteFile(@PathVariable int id){
//    String res = fileService.DeleteFile(id);
//    System.out.println(res);
//    return res;
//  }
  @DeleteMapping("/deleteFile/{id}")
  public ResponseEntity<String> deleteFile(@PathVariable int id) {
      FileEntity fileEntity = fileService.getFile(id);
      if (fileEntity == null) {
          return ResponseEntity.notFound().build();
      }

      // Delete the file entity from the database
      fileService.deleteFile(id);

      return ResponseEntity.ok("File deleted successfully");
  }

  
}
