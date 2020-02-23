package com.assignment.delivery.service.impl;

import com.assignment.delivery.domain.Delivery;
import com.assignment.delivery.exception.DeliveryNotFoundException;
import com.assignment.delivery.repository.DeliveryRepository;
import com.assignment.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Delivery create(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getById(String id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (!delivery.isPresent()) {
            throw new DeliveryNotFoundException(id);
        }
        return delivery.get();
    }
}
