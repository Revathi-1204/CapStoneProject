package com.mnp.donor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnp.donor.entity.VerificationCases;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationCases, Integer>{
    Optional<VerificationCases> findByNumber(String number);
}
