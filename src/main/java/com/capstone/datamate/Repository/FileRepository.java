package com.capstone.datamate.Repository;

import java.util.List;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.datamate.Entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Integer>{
    public List<FileEntity> findByIsdeleted (boolean isdeleted);
    // public List<FileEntity> findAllByDeletedAndCreatorUserid (boolean isdeleted, int userId);
}
