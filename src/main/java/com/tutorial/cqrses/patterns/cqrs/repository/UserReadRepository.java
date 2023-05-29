package com.tutorial.cqrses.patterns.cqrs.repository;

import java.util.Map;

import com.tutorial.cqrses.patterns.domain.UserAddress;
import com.tutorial.cqrses.patterns.domain.UserContact;

import java.util.HashMap;

public class UserReadRepository {
    private Map<String, UserAddress> userAddress = new HashMap<>();

    private Map<String, UserContact> userContact = new HashMap<>();

    public void addUserAddress(String id, UserAddress user) {
        userAddress.put(id, user);
    }

    public UserAddress getUserAddress(String id) {
        return userAddress.get(id);
    }

    public void addUserContact(String id, UserContact user) {
        userContact.put(id, user);
    }

    public UserContact getUserContact(String id) {
        return userContact.get(id);
    }
}
