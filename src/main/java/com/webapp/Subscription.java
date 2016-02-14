package com.webapp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer subscriptionId;

    @Column(name="subscriber_name")
    @NotNull
    private String user;

    @Column(name="service_code")
    @NotNull
    private Integer serviceCode;

    public Subscription() {}

    public Subscription(String user, Integer serviceCode) {
        this.user = user;
        this.serviceCode = serviceCode;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("subscriptionId=").append(subscriptionId);
        sb.append(", user='").append(user).append('\'');
        sb.append(", serviceCode=").append(serviceCode);
        sb.append('}');
        return sb.toString();
    }
}
