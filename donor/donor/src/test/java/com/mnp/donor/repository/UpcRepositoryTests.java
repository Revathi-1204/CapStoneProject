package com.mnp.donor.repository;

import com.mnp.donor.entity.DonorSubscriber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UpcRepositoryTests {

    @Autowired
    private DonorSubscriberRepository upcRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByNumber_ExistingNumber() {
        // Arrange
        DonorSubscriber upcGen = new DonorSubscriber();
        upcGen.setNumber("1234567890");
        upcRepository.save(upcGen);
        // entityManager.persist(upcGen);
        // entityManager.flush();

        // Act
        Optional<DonorSubscriber> foundUpcGen = upcRepository.findByNumber("1234567890");

        // Assert
        assertTrue(foundUpcGen.isPresent());
        assertEquals("1234567890", foundUpcGen.get().getNumber());
    }

    @Test
    public void testFindByNumber_NonExistingNumber() {
        // Act
        Optional<DonorSubscriber> foundUpcGen = upcRepository.findByNumber("9876543210");

        // Assert
        assertFalse(foundUpcGen.isPresent());
    }

    @Test
    public void testFindByUpc_ExistingUpc() {
        // Arrange
        DonorSubscriber upcGen = new DonorSubscriber();
        upcGen.setUpc("UPC123");
        entityManager.persist(upcGen);
        entityManager.flush();

        // Act
        Optional<DonorSubscriber> foundUpcGen = upcRepository.findByUpc("UPC123");

        // Assert
        assertTrue(foundUpcGen.isPresent());
        assertEquals("UPC123", foundUpcGen.get().getUpc());
    }

    @Test
    public void testFindByUpc_NonExistingUpc() {
        // Act
        Optional<DonorSubscriber> foundUpcGen = upcRepository.findByUpc("NonExistentUPC");

        // Assert
        assertFalse(foundUpcGen.isPresent());
    }
}
