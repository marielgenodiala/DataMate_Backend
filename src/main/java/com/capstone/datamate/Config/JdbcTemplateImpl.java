package com.capstone.datamate.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTemplateImpl{
    
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbc;

    public JdbcTemplateImpl(){}
    
    public JdbcTemplateImpl(DataSource dataSource, JdbcTemplate jdbc) {
        this.dataSource = dataSource;
        this.jdbc = jdbc;
    }
    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void executeSQL(String sql) throws DataAccessException{
        jdbc.execute(sql);
    }
    
}