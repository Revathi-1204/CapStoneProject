
package com.mnp.donor.repository;

import com.mnp.donor.entity.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SubscriberRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void testFindById() {
        Request subscriber = new Request();
        subscriber.setName("Revathi");
        entityManager.persist(subscriber);

        // Retrieve the subscriber by its ID using the repository
        Optional<Request> foundSubscriber = requestRepository.findById(subscriber.getId());

        // Assert that the retrieved subscriber matches the one we saved
        assertTrue(foundSubscriber.isPresent());
        assertEquals(subscriber.getName(), foundSubscriber.get().getName());
    }

    @Test
    public void testDeleteById() {
        // Create a test subscriber and save it to the database
        Request subscriber = new Request();
        subscriber.setName("Revathi");
        entityManager.persist(subscriber);

        // Delete the subscriber by its ID using the repository
        requestRepository.deleteById(subscriber.getId());

        // Try to retrieve the subscriber after deletion
        Optional<Request> deletedSubscriber = requestRepository.findById(subscriber.getId());

        // Assert that the subscriber is deleted (should be empty)
        assertFalse(deletedSubscriber.isPresent());
    }

    @Test
    public void testFindByNumber() {
        // Create a test subscriber and save it to the database
        Request subscriber = new Request();
        subscriber.setNumber("9123456789");
        entityManager.persist(subscriber);

        // Retrieve the subscriber by its number using the repository
        Optional<Request> foundSubscriber = requestRepository.findByNumber("9123456789");

        // Assert that the retrieved subscriber matches the one we saved
        assertTrue(foundSubscriber.isPresent());
        assertEquals(subscriber.getNumber(), foundSubscriber.get().getNumber());
    }
}
