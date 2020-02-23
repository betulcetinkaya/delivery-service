package com.assignment.delivery.repository;

import com.assignment.delivery.DeliveryConstants;
import com.assignment.delivery.RepositoryBaseTest;
import com.assignment.delivery.domain.Delivery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DeliveryRepositoryTest extends RepositoryBaseTest {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Before
    public void onSetup() throws Exception {
        this.objectMapper = new ObjectMapper();
        if (!mongoTemplate.collectionExists(DeliveryConstants.DELIVERY_DOCUMENT_NAME))
        mongoTestDataLoader.loadTestDataIntoDb(DeliveryConstants.DELIVERY_DOCUMENT_NAME);

        System.out.println("");
    }

    @Test
    public void shouldFindById() {
        Optional<Delivery> delivery = deliveryRepository.findById("1");
        Assert.assertTrue(delivery.isPresent());
    }

    @After
    public void clearDb() {
        try {
            mongoTemplate.dropCollection(DeliveryConstants.DELIVERY_DOCUMENT_NAME);
        } catch (Exception e) {
            fail("error on After", e);
        }
    }

}
