package com.wojciechkoziol.SubbMeBackend.subscription.dto;

import com.wojciechkoziol.SubbMeBackend.subscription.models.Subscription;
import lombok.Getter;

@Getter
public class SubscriptionResponseDTO extends SubscriptionDTO{

    private final Long id;

    public SubscriptionResponseDTO(Subscription subscription) {
        super(subscription.getName(), subscription.getType(), subscription.getPrice(), subscription.getCurrencyCode(),
                subscription.getDateStartedAsInterval(), subscription.getDateEndingAsInterval(), subscription.getWebsiteURL(), subscription.getReminderDays());
        this.id = subscription.getId();
    }
}
