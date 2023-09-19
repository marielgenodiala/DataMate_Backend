package com.capstone.datamate.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_file")
public class FileEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;
    
    @Column
    private String fileName;

    @Column
    private float fileSize;

    @Column
    private LocalDateTime uploadDate = java.time.LocalDateTime.now();

    @Column
    private LocalDateTime latestDateModified = java.time.LocalDateTime.now();

    @Column
    private boolean isdeleted;
    @Column
    private String thumbnailUrl;

    @Column(length=1000000)
    @Lob
    private byte[] data;

    


    // @ManyToOne(targetEntity = UserEntity.class, cascade=CascadeType.MERGE)
    // @JoinColumn(name = "userid")
    // UserEntity uploader;

    public FileEntity(){}
    
    public FileEntity(int fileId, String fileName, float fileSize, LocalDateTime uploadDate,
            LocalDateTime latestDateModified, boolean isdeleted, byte[] data) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.uploadDate = uploadDate;
        this.latestDateModified = latestDateModified;
        this.isdeleted = isdeleted;
        this.data = data;
    }

    public FileEntity(String fileName, float fileSize, byte[] data) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.data = data;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    public LocalDateTime getLatestDateModified() {
        return latestDateModified;
    }

    public void setLatestDateModified(LocalDateTime latestDateModified) {
        this.latestDateModified = latestDateModified;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    

}
