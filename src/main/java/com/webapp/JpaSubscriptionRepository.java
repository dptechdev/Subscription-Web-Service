package com.webapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Subscription findByServiceCode(int serviceCode);
    Subscription findBySubscriptionId(int subscriptionId);

}
