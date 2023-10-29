package com.mnp.donor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mnp.donor.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

    Optional<Request> findById(int id);
    void deleteByNumber(String number);
    Optional<Request> findByNumber(String number);
    List<Request> findByStatus(String status);
}
