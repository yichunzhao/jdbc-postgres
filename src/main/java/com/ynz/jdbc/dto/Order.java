package com.ynz.jdbc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order implements Dto {
    @Getter(AccessLevel.NONE)
    private Long orderId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private Date createDateDate;
    private BigDecimal totalDue;
    private String status;
    private String salespersonFirstName;
    private String salespersonLastName;
    private String salespersonEmail;
    private List<OrderItem> orderItems;

    @Override
    public Long getId() {
        return this.orderId;
    }
}
