package com.ynz.jdbc.dao;

import com.ynz.jdbc.dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> {

    private static final String INSERT = "INSERT INTO Customer(first_name, last_name, email, phone, address, city, state, zipcode) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String READ_BY_ID = "SELECT customer_id, first_name, last_name, email, phone, address, city, state, zipcode from Customer where customer_id = ?";

    public CustomerDAO(Connection con) {
        super(con);
    }

    @Override
    public int create(Customer dto) {
        int result = -1;

        try (PreparedStatement stmt = this.conn.prepareStatement(INSERT)) {
            stmt.setString(1, dto.getFirstName());
            stmt.setString(2, dto.getLastName());
            stmt.setString(3, dto.getEmail());
            stmt.setString(4, dto.getPhone());
            stmt.setString(5, dto.getAddress());
            stmt.setString(6, dto.getCity());
            stmt.setString(7, dto.getState());
            stmt.setString(8, dto.getZipCode());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = Customer.create();
        try (PreparedStatement stmt = this.conn.prepareStatement(READ_BY_ID)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                customer.setId(rs.getLong("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getString("zipcode"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return customer;
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
