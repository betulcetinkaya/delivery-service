package com.assignment.delivery.service;

import com.assignment.delivery.ServiceBaseTest;
import com.assignment.delivery.domain.Delivery;
import com.assignment.delivery.exception.DeliveryNotFoundException;
import com.assignment.delivery.repository.DeliveryRepository;
import com.assignment.delivery.service.impl.DeliveryServiceImpl;
import com.assignment.delivery.testData.DeliveryTestData;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliveryServiceTest extends ServiceBaseTest {

    @MockBean
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryServiceImpl deliveryService;

    @Test
    public void testCreate_SendValidDelivery_ReturnDelivery() {
        Delivery delivery = DeliveryTestData.getDelivery();
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        Delivery created = deliveryService.create(delivery);

        verify(deliveryRepository).save(any(Delivery.class));
        Assert.assertEquals(delivery.getId(), created.getId());
        Assert.assertEquals(delivery.getCostPerDelivery(), created.getCostPerDelivery());
        Assert.assertEquals(delivery.getCostPerProduct(), created.getCostPerProduct());
        Assert.assertEquals(delivery.getFixedCost(), created.getFixedCost());
    }

    @Test
    public void testGetById_RecordNotFound_ThrowException() {
        String id = "DELIVERY-001";
        when(deliveryRepository.findById(anyString())).thenReturn(Optional.empty());
        thrown.expect(DeliveryNotFoundException.class);
        thrown.expectMessage(id);

        deliveryService.getById(id);

        verify(deliveryRepository).findById(anyString());
    }

    @Test
    public void testGetBy_SendExistingId_ReturnDelivery() {
        Delivery delivery = DeliveryTestData.getDelivery();
        when(deliveryRepository.findById(anyString())).thenReturn(Optional.of(delivery));

        Delivery found = deliveryService.getById(delivery.getId());

        verify(deliveryRepository).findById(anyString());
        Assert.assertEquals(delivery.getId(), found.getId());
        Assert.assertEquals(delivery.getCostPerDelivery(), found.getCostPerDelivery());
        Assert.assertEquals(delivery.getCostPerProduct(), found.getCostPerProduct());
        Assert.assertEquals(delivery.getFixedCost(), found.getFixedCost());
    }

    @Test
    public void testCalculateDeliveryCost_ReturnDeliveryCost() {
        Delivery delivery = DeliveryTestData.getDelivery();
        when(deliveryRepository.findById(anyString())).thenReturn(Optional.of(delivery));

        BigDecimal deliveryCost = deliveryService.calculateDeliveryCost(delivery.getId(), 3, 6);

        verify(deliveryRepository).findById(anyString());
        Assert.assertEquals(new BigDecimal(26), deliveryCost);

    }



}
