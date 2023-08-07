package com.capstone.datamate.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.datamate.Entity.FileEntity;
import com.capstone.datamate.Repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

  @Autowired
  FileRepository fileRepo;
 //creating the uploads path    
 private final Path root = Paths.get("uploads");

 //initializing of the path
  @Override
  public void init() {
    try {
      Files.createDirectories(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  //save file in db
  public FileEntity store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileEntity File = new FileEntity(fileName, file.getSize(), file.getBytes());

    return fileRepo.save(File);
  }

  //download file data
  public FileEntity getFile(int id) {
    return fileRepo.findById(id).get();
  }
  
  //retrieve a list of all file
  public Stream<FileEntity> getAllFiles() {
    return fileRepo.findAll().stream();
  }

  //retireve deleted files
  public List<FileEntity> getDeletedFiles(){
    return fileRepo.findByIsdeleted(true);
  }

  //permanently remove file
  public String DeleteFile(int id){
    String msg;
		if(fileRepo.findById(id) != null) {
          FileEntity file = fileRepo.findById(id).get();
          fileRepo.delete(file);
			    msg = "File ID number " + id + " deleted successfully!";
		}else {
			msg = "File ID number " + id + " is NOT found!";
		}
		return msg;
  }
}