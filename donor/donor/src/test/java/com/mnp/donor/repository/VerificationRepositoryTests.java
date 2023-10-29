package com.mnp.donor.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.mnp.donor.entity.VerificationCases;

@DataJpaTest
public class VerificationRepositoryTests {

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByNumber() {
        // Arrange
        VerificationCases verificationCase = new VerificationCases();
        verificationCase.setNumber("1234567890");
        entityManager.persist(verificationCase);
        entityManager.flush();

        // Act
        Optional<VerificationCases> found = verificationRepository.findByNumber("1234567890");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("1234567890", found.get().getNumber());
        
    }

    @Test
    public void testFindByNumberNotFound() {
        // Act
        Optional<VerificationCases> notFound = verificationRepository.findByNumber("9999999999");

        // Assert
        assertFalse(notFound.isPresent());
    }
}
