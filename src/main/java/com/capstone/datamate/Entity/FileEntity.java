package com.capstone.datamate.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Date uploadDate;

    @Column
    private Date latestDateModified;

    @Column
    private boolean isDeleted;

    // @ManyToOne(targetEntity = UserEntity.class, cascade=CascadeType.MERGE)
    // @JoinColumn(name = "userid")
    // UserEntity uploader;

    public FileEntity(){}

    public FileEntity(int fileId, String fileName, Date uploadDate, Date latestDateModified, boolean isDeleted) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.latestDateModified = latestDateModified;
        this.isDeleted = isDeleted;
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getLatestDateModified() {
        return latestDateModified;
    }

    public void setLatestDateModified(Date latestDateModified) {
        this.latestDateModified = latestDateModified;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
