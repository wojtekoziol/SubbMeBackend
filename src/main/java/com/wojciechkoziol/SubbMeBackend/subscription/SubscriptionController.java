package com.wojciechkoziol.SubbMeBackend.subscription;

import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import com.wojciechkoziol.SubbMeBackend.auth.user.AppUserDetailsService;
import com.wojciechkoziol.SubbMeBackend.subscription.dto.SubscriptionDTO;
import com.wojciechkoziol.SubbMeBackend.subscription.dto.SubscriptionResponseDTO;
import com.wojciechkoziol.SubbMeBackend.subscription.models.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final AppUserDetailsService userDetailsService;
    private final SubscriptionService subscriptionService;

    @GetMapping
    public Set<SubscriptionResponseDTO> getSubscriptions(Principal principal) {
        AppUser user = userDetailsService.loadUserByUsername(principal.getName());
        return subscriptionService.getUserSubscriptions(user.getId()).stream()
                .map(SubscriptionResponseDTO::new).collect(Collectors.toSet());
    }

    @PostMapping
    public SubscriptionResponseDTO createSubscription(Principal principal, @RequestBody SubscriptionDTO subscription) {
        AppUser user = userDetailsService.loadUserByUsername(principal.getName());
        Subscription newSubscription = subscriptionService.createSubscription(user, subscription);
        return new SubscriptionResponseDTO(newSubscription);
    }

    @PutMapping("/{id}")
    public SubscriptionResponseDTO updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDTO subscription) {
        Subscription updated = subscriptionService.updateSubscription(id, subscription);
        return new SubscriptionResponseDTO(updated);
    }
}
