package com.mnp.donor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnp.donor.entity.RecipientSubscriber;

@Repository
public interface RecipientSubscriberRepository extends JpaRepository<RecipientSubscriber,Integer>{
    Optional<RecipientSubscriber> findByNumber(String number);
 
}
