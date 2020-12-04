package com.ynz.jdbc.dao;

import com.ynz.jdbc.dto.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> {

    public CustomerDAO(Connection con) {
        super(con);
    }

    @Override
    public Customer create(Customer dto) {
        return null;
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer update(Customer dto) {
        return null;
    }

    @Override
    public void delete(Customer dto) {

    }
}
