package com.wojciechkoziol.SubbMeBackend.subscription;

import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import com.wojciechkoziol.SubbMeBackend.auth.user.UserRepository;
import com.wojciechkoziol.SubbMeBackend.subscription.dto.SubscriptionDTO;
import com.wojciechkoziol.SubbMeBackend.subscription.models.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Set<Subscription> getUserSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription createSubscription(AppUser user, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription(subscriptionDTO);
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }
}
