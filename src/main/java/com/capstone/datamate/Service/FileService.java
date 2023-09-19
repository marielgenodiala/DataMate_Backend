package com.capstone.datamate.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

// import java.nio.file.Path;
// import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.datamate.Entity.FileEntity;

public interface FileService {
        public void init();
      
//        public FileEntity store(FileEntity fileEntity) throws IOException;
//        public FileEntity store(FileEntity fileEntity);
        public FileEntity store(MultipartFile file) throws IOException;

        public FileEntity updateFile(int id, MultipartFile file) throws IOException;
        public FileEntity getFile(int id);
        public Stream<FileEntity> getAllFiles();
        public List<FileEntity> getDeletedFiles();
        public String DeleteFile(int id);
        public void deleteFile(int id);

}
