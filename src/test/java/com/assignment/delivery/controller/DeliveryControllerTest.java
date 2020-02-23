package com.assignment.delivery.controller;

import com.assignment.delivery.ControllerBaseTest;
import com.assignment.delivery.domain.Delivery;
import com.assignment.delivery.service.DeliveryService;
import com.assignment.delivery.testData.DeliveryTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(DeliveryController.class)
public class DeliveryControllerTest extends ControllerBaseTest {

    @MockBean
    private DeliveryService deliveryService;

    @BeforeClass
    public static void init() {
        baseAddress = "/deliveries";
    }

    @Test
    public void testCreateDelivery_SendValidRequest_ReturnCreated() throws Exception {
        Delivery delivery = DeliveryTestData.getDelivery();
        when(deliveryService.create(any(Delivery.class))).thenReturn(delivery);

        ResultActions perform = mockMvc.perform(post(baseAddress)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(delivery)));


        perform.andExpect(status().isCreated());
    }

    @Test
    public void testGetDelivery_SendId_GetADelivery() throws Exception {
        Delivery delivery = DeliveryTestData.getDelivery();
        when(deliveryService.getById(delivery.getId())).thenReturn(delivery);

        ResultActions resultActions = mockMvc.perform(get(baseAddress + "/" + delivery.getId())
                .contentType(mediaType));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(delivery.getId())));

        verify(deliveryService).getById(anyString());
    }

    @Test
    public void testGetDeliveryCost_SendId_GetDeliveryCost() throws Exception {
        String deliveryId = "DELIVERY-001";
        when(deliveryService.calculateDeliveryCost(anyString(), anyInt(), anyInt())).thenReturn(new BigDecimal(15));

        ResultActions resultActions = mockMvc.perform(get(baseAddress + "?id=" + deliveryId
                + "&numberOfDeliveries=3&numberOfProducts=10")
                .contentType(mediaType));

        resultActions.andExpect(status().isOk());

        verify(deliveryService).calculateDeliveryCost(anyString(), anyInt(), anyInt());
    }

}
