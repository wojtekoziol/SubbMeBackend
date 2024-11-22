package com.wojciechkoziol.SubbMeBackend.subscription;

import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import com.wojciechkoziol.SubbMeBackend.auth.user.UserRepository;
import com.wojciechkoziol.SubbMeBackend.subscription.dto.SubscriptionDTO;
import com.wojciechkoziol.SubbMeBackend.subscription.dto.SubscriptionResponseDTO;
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

    public Subscription updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Subscription not found"));

        subscription.setName(subscriptionDTO.getName());
        subscription.setType(subscriptionDTO.getType());
        subscription.setPrice(subscriptionDTO.getPrice());
        subscription.setCurrencyCode(subscriptionDTO.getCurrencyCode());
        subscription.setDateStartedAsInterval(subscriptionDTO.getDateStartedAsInterval());
        subscription.setDateEndingAsInterval(subscriptionDTO.getDateEndingAsInterval());
        subscription.setWebsiteURL(subscriptionDTO.getWebsiteURL());

        return subscriptionRepository.save(subscription);
    }
}
