package com.ynz.jdbc.dao;

import com.ynz.jdbc.dto.Order;
import com.ynz.jdbc.dto.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order> {
    private static final String FIND_ORDER_BY_ID =
            "select c.first_name as customer_firstName, c.last_name as customer_lastName, c.email," +
                    "o.order_id, o.creation_date, o.total_due, o.status," +
                    "s.first_name as sale_firstName,s.last_name as sale_lastName,s.email," +
                    "oi.quantity," +
                    "p.code, p.name, p.size, p.price, p.variety, p.price " +
                    "from orders o " +
                    "join salesperson s on o.salesperson_id = s.salesperson_id " +
                    "join order_item oi on o.order_id = oi.order_id " +
                    "join product p on oi.product_id = p.product_id " +
                    "join customer c on o.customer_id = c.customer_id " +
                    "where o.order_id = ?";

    public OrderDAO(Connection con) {
        super(con);
    }

    @Override
    public int create(Order dto) {
        return 0;
    }

    @Override
    public Order findById(Long id) {

        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();

        try (PreparedStatement statement = super.conn.prepareStatement(FIND_ORDER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            long orderId = 0;
            while (rs.next()) {

                if (orderId == 0) {
                    order.setCustomerFirstName(rs.getString(1));
                    order.setCustomerLastName(rs.getString(2));
                    order.setCustomerEmail(rs.getString(3));
                    order.setOrderId(rs.getLong(4));
                    orderId = order.getId();
                    order.setCreateDateDate(rs.getDate(5));
                    order.setTotalDue(rs.getBigDecimal(6));
                    order.setStatus(rs.getString(7));
                    order.setSalespersonFirstName(rs.getString(8));
                    order.setSalespersonLastName(rs.getString(9));
                    order.setSalespersonEmail(rs.getString(10));
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(rs.getInt(11));
                orderItem.setProductCode(rs.getString(12));
                orderItem.setProductName(rs.getString(13));
                orderItem.setProductSize(rs.getInt(14));
                orderItem.setProductPrice(rs.getBigDecimal(15));
                orderItem.setProductVariety(rs.getString(16));
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public int update(Order dto) {
        return 0;
    }

    @Override
    public void delete(Order dto) {

    }
}
