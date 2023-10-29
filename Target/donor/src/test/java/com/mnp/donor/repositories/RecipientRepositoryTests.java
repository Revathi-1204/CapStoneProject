package com.mnp.donor.repositories;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.mnp.donor.entity.RecipientSubscriber;
import com.mnp.donor.repository.RecipientSubscriberRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@DataJpaTest
public class RecipientRepositoryTests {

    @Autowired
    private RecipientSubscriberRepository recipientSubscriberRepository;

    @BeforeEach
    public void setUp() {
        // Clear any existing data in the repository before each test
        recipientSubscriberRepository.deleteAll();
    }

    @Test
    public void testFindByNumber() {
        // Create a sample RecipientSubscriber
        RecipientSubscriber subscriber = new RecipientSubscriber();
        subscriber.setNumber("1234567890");
        recipientSubscriberRepository.save(subscriber);

        // Test the findByNumber method
        Optional<RecipientSubscriber> foundSubscriber = recipientSubscriberRepository.findByNumber("1234567890");
        assertTrue(foundSubscriber.isPresent());
        assertEquals("1234567890", foundSubscriber.get().getNumber());
        
    }

    @Test
    public void testFindByNumberWhenNumberNotExists() {
        // Test the findByNumber method when the number doesn't exist
        Optional<RecipientSubscriber> notFoundSubscriber = recipientSubscriberRepository.findByNumber("9876543210");
        assertTrue(notFoundSubscriber.isEmpty());
    }
}
