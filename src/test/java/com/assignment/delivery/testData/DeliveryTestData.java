package com.assignment.delivery.testData;

import com.assignment.delivery.domain.Delivery;

import java.math.BigDecimal;

public class DeliveryTestData {

    public static Delivery getDelivery() {
        Delivery delivery = new Delivery();
        delivery.setId("DELIVERY-001");
        delivery.setCostPerDelivery(new BigDecimal(3));
        delivery.setCostPerProduct(new BigDecimal(2));
        delivery.setFixedCost(new BigDecimal(5));
        return delivery;
    }
}
