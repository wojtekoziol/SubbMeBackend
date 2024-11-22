package com.wojciechkoziol.SubbMeBackend.subscription.dto;

import com.wojciechkoziol.SubbMeBackend.subscription.models.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubscriptionDTO {
    private String name;
    private SubscriptionType type;
    private Double price;
    private String currencyCode;
    private Double dateStartedAsInterval;
    private Double dateEndingAsInterval;
    private String websiteURL;
}
