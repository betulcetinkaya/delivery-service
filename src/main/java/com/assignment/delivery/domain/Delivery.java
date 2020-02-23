package com.assignment.delivery.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document
public class Delivery {
    @Id
    private String id;

    @NotNull(message = "{NotNull.Delivery.costPerDelivery}")
    private BigDecimal costPerDelivery;

    @NotNull(message = "{NotNull.Delivery.costPerProduct}")
    private BigDecimal costPerProduct;

    @NotNull(message = "{NotNull.Delivery.fixedCost}")
    private BigDecimal fixedCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(BigDecimal costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public BigDecimal getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(BigDecimal costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public BigDecimal getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(BigDecimal fixedCost) {
        this.fixedCost = fixedCost;
    }
}
