package com.assignment.delivery.service;

import com.assignment.delivery.domain.Delivery;

public interface DeliveryService {

    Delivery create(Delivery delivery);

    Delivery getById(String id);

}
