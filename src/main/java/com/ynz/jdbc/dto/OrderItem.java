package com.ynz.jdbc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    private int quantity;
    private String productCode;
    private String productName;
    private int productSize;
    private String productVariety;
    private BigDecimal productPrice;

    @Override
    public String toString() {
        return "OrderItem{" +
                "quantity=" + quantity +
                ", productCode='" + productCode + '\'' +
                ", productionName='" + productName + '\'' +
                ", productSize=" + productSize +
                ", productVariety='" + productVariety + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
