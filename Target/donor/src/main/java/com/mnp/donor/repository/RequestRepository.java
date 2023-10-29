package com.mnp.donor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mnp.donor.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

    Optional<Request> findById(Long id);
    void deleteByNumber(String number);
    Optional<Request> findByNumber(String number);
    Optional<Request> findByStatus(String status);
}
