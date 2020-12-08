package com.ynz.jdbc.dao;

import com.ynz.jdbc.connection.DatabaseConnectionFactory;
import com.ynz.jdbc.dto.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderDAOTest {

    private static OrderDAO orderDAO;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        conn = DatabaseConnectionFactory.getLocalPostgresConnection("hplussport", "postgres", "test");
        orderDAO = new OrderDAO(conn);
    }

    @Test
    void findById() {
        Order order = orderDAO.findById(1148L);
        assertAll(
                () -> assertNotNull(order),
                () -> assertEquals(order.getId(), 1148L),
                () -> assertThat(order.getOrderItems(), hasSize(3))
        );
    }

    @Test
    void findOrdersByCustomerId() {
        List<Order> orders = orderDAO.getOrderByOrderId(387L);
        assertAll(
                () -> assertThat(orders, hasSize(1)),
                () -> assertNotNull(orders.get(0)),
                () -> assertThat(orders.get(0).getOrderItems(), hasSize(3))
        );
    }
}