package com.mnp.donor.dto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ResponsedtoTests {

    @Test
    public void testGetStatus() {
        // Arrange
        Responsedto response = new Responsedto();
        response.setStatus("Success");

        // Act
        String status = response.getStatus();

        // Assert
        assertEquals("Success", status);
    }

    @Test
    public void testSetStatus() {
        // Arrange
        Responsedto response = new Responsedto();

        // Act
        response.setStatus("Error");

        // Assert
        assertEquals("Error", response.getStatus());
    }

    @Test
    public void testGetReason() {
        // Arrange
        Responsedto response = new Responsedto();
        response.setReason("Validation failed");

        // Act
        String reason = response.getReason();

        // Assert
        assertEquals("Validation failed", reason);
    }

    @Test
    public void testSetReason() {
        // Arrange
        Responsedto response = new Responsedto();

        // Act
        response.setReason("Internal Server Error");

        // Assert
        assertEquals("Internal Server Error", response.getReason());
    }
}
