package com.assignment.delivery.controller;

import com.assignment.delivery.domain.Delivery;
import com.assignment.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ResponseEntity<Delivery> create(@Valid @RequestBody Delivery delivery) {
        return new ResponseEntity<>(deliveryService.create(delivery), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public
    @ResponseBody
    ResponseEntity<Delivery> getById(@PathVariable("id") String id) {
        Delivery delivery = deliveryService.getById(id);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

}
