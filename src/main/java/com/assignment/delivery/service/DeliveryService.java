package com.assignment.delivery.service;

import com.assignment.delivery.domain.Delivery;

import java.math.BigDecimal;

public interface DeliveryService {

    Delivery create(Delivery delivery);

    Delivery getById(String id);

    BigDecimal calculateDeliveryCost(String id, int numberOfDeliveries, int numberOfProducts);

}
