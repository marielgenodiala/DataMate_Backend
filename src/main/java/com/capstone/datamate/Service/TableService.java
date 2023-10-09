package com.capstone.datamate.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.datamate.Entity.TableEntity;
import com.capstone.datamate.Repository.TableRepository;

@Service
public class TableService {
    @Autowired
    TableRepository trepo;

    public TableEntity postTable(TableEntity tbl){
       return trepo.save(tbl);    
    }

    public TableEntity getTable(String tblName){
        return trepo.findByTableName(tblName);
    }

    public List<TableEntity> getTablesByDB(int dbId){
        return trepo.findByDatabaseDatabaseId(dbId);
    }



}
