package com.mnp.donor.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnp.donor.entity.DonorSubscriber;


@Repository
public interface DonorSubscriberRepository extends JpaRepository<DonorSubscriber,Integer>{
    Optional<DonorSubscriber> findByNumber(String number);
    Optional<DonorSubscriber> findByUpc(String upc);
    void deleteByNumber(String number);

    // @Query(value="select * from user_port;",nativeQuery = true)
    // String userport;

       
}
