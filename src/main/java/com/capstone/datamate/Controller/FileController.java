package com.capstone.datamate.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capstone.datamate.Entity.FileEntity;
import com.capstone.datamate.Message.ResponseFile;
import com.capstone.datamate.Message.ResponseMessage;
import com.capstone.datamate.Service.FileService;


@CrossOrigin("http://localhost:3000")
@RestController
public class FileController {
    @Autowired
  FileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      fileService.store(file);

      message = file.getOriginalFilename() + " successfully uploaded!" ;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = file.getOriginalFilename() + " could not be uploaded!" + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable int id) {
    FileEntity file = fileService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
        .body(file.getData());
  }

  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
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
}
