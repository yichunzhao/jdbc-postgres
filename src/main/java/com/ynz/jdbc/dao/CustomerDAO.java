package com.ynz.jdbc.dao;

import com.ynz.jdbc.dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> {

    private static final String INSERT = "INSERT INTO Customer(first_name, last_name, email, phone, address, city, state, zipcode) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String UPDATE_BY_ID = "UPDATE Customer SET first_name = ?, last_name = ?, email =?, phone = ?, " +
            "address= ?, city=?, state=?, zipcode=? WHERE customer_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM Customer where customer_id = ?";

    private static final String READ_BY_ID = "SELECT customer_id, first_name, last_name, email, phone, address, city, state, zipcode from Customer where customer_id = ?";

    private static final String READ_ALL_LIMIT = "SELECT customer_id, first_name, last_name, email, phone, address, city, " +
            "state, zipcode from Customer order by last_name, first_name limit ?";

    private static final String READ_ALL_LIMIT_PAGED = "SELECT customer_id, first_name, last_name, email, phone, address, city, " +
            "state, zipcode from Customer order by last_name, first_name limit ? offset ?";

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

    public List<Customer> findAllSortedLimit(int limit) {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = this.conn.prepareStatement(READ_ALL_LIMIT)) {
            stmt.setInt(1, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getLong("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getString("zipcode"));

                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return customers;
    }

    public List<Customer> findAllSortedAndPaged(int pageSize, int pageNum) {
        if (pageSize < 0 || pageNum < 0)
            throw new IllegalArgumentException("pageSize and pageNum must be positive number");
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = this.conn.prepareStatement(READ_ALL_LIMIT_PAGED)) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, pageNum);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getLong("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getString("zipcode"));

                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public int update(Customer dto) {
        int result = -1;
        try (PreparedStatement stmt = this.conn.prepareStatement(UPDATE_BY_ID)) {
            stmt.setString(1, dto.getFirstName());
            stmt.setString(2, dto.getLastName());
            stmt.setString(3, dto.getEmail());
            stmt.setString(4, dto.getPhone());
            stmt.setString(5, dto.getAddress());
            stmt.setString(6, dto.getCity());
            stmt.setString(7, dto.getState());
            stmt.setString(8, dto.getZipCode());
            stmt.setLong(9, dto.getId());
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void delete(Customer dto) {

        try (PreparedStatement stmt = this.conn.prepareStatement(DELETE_BY_ID)) {
            stmt.setLong(1, dto.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
