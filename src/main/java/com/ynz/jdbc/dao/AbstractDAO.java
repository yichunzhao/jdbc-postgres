package com.ynz.jdbc.dao;

import com.ynz.jdbc.dto.Dto;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T extends Dto> {
    protected Connection conn;

    public AbstractDAO(Connection con) {
        super();
        this.conn = con;
    }

    public abstract T create(T dto);

    public abstract T findById(Long id);

    public abstract List<T> findAll();

    public abstract T update(T dto);

    public abstract void delete(T dto);

}
