package com.ynz.jdbc.dao;

import com.ynz.jdbc.connection.DatabaseConnectionFactory;
import com.ynz.jdbc.dto.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerDAOTest {
    private static Connection conn;
    private static CustomerDAO customerDAO;

    @BeforeAll
    static void setUp() {
        System.out.println("before all tests, setting up db connection... ");
        conn = DatabaseConnectionFactory.getLocalPostgresConnection("hplussport", "postgres", "test");
        customerDAO = new CustomerDAO(conn);
    }

    @Test
    void create() {
        Customer customer = Customer.create();
        customer.setFirstName("Mikey");
        customer.setLastName("XXX");
        customer.setCity("CPH");
        customer.setPhone("12345678");
        customer.setState("HHH");
        customer.setEmail("xxx@gmail.com");
        customer.setZipCode("10222");
        int result = customerDAO.create(customer);
        assertEquals(1, result);
    }

    @Test
    void givenCustomerExisted_findItById() {
        Customer found = customerDAO.findById(1000L);

        assertAll(
                () -> assertNotNull(found),
                () -> assertEquals(found.getId(), 1000L),
                () -> assertThat(found.getFirstName(), is(equalTo("Victor"))),
                () -> assertThat(found.getLastName(), is(equalTo("Woods"))),
                () -> assertEquals(found.getPhone(), "(786)720-8933"),
                () -> assertEquals(found.getAddress(), "00 Bunting Terrace"),
                () -> assertEquals(found.getCity(), "Miami"),
                () -> assertEquals(found.getState(), "FL"),
                () -> assertEquals(found.getZipCode(), "33164")
        );
    }

    @Test
    void whenCustomerNotExisted_FindItById() {
        Customer found = customerDAO.findById(1L);
        assertAll(
                () -> assertNotNull(found),
                () -> assertThat(found.getId(), is(nullValue()))
        );
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
        Customer updated = Customer.create();
        updated.setId(10000L);
        updated.setFirstName("Mike");
        updated.setLastName("YYY");
        int result = customerDAO.update(updated);
        assertEquals(1, result);
    }

    @Test
    void delete() {
        Customer target = Customer.create();
        target.setId(10000L);

        customerDAO.delete(target);

        Customer found = customerDAO.findById(10000L);
        assertNull(found.getId());
    }
}