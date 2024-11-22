package com.wojciechkoziol.SubbMeBackend.subscription.models;

import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import com.wojciechkoziol.SubbMeBackend.subscription.dto.SubscriptionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscriptions")
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;
    private Double price;
    private String currencyCode;
    @Column(name = "date_started_as_interval")
    private Double dateStartedAsInterval;
    @Column(name = "date_ending_as_interval")
    private Double dateEndingAsInterval;
    @Column(name = "website_url")
    private String websiteURL;
    @ManyToOne(optional = false)
    private AppUser user;

    public Subscription(String name, SubscriptionType type, Double price, String currencyCode, Double dateStartedAsInterval, Double dateEndingAsInterval, String websiteURL, AppUser user) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.currencyCode = currencyCode;
        this.dateStartedAsInterval = dateStartedAsInterval;
        this.dateEndingAsInterval = dateEndingAsInterval;
        this.websiteURL = websiteURL;
        this.user = user;
    }

    public Subscription(SubscriptionDTO subscriptionDTO) {
        this.name = subscriptionDTO.getName();
        this.type = subscriptionDTO.getType();
        this.price = subscriptionDTO.getPrice();
        this.currencyCode = subscriptionDTO.getCurrencyCode();
        this.dateStartedAsInterval = subscriptionDTO.getDateStartedAsInterval();
        this.dateEndingAsInterval = subscriptionDTO.getDateEndingAsInterval();
        this.websiteURL = subscriptionDTO.getWebsiteURL();
    }
}
