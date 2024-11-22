package com.wojciechkoziol.SubbMeBackend.subscription;

import com.wojciechkoziol.SubbMeBackend.subscription.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Set<Subscription> findByUserId(Long userId);
}
