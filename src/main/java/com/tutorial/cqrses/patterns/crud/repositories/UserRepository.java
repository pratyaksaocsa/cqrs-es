package com.tutorial.cqrses.patterns.crud.repositories;
import java.util.Map;

import com.tutorial.cqrses.patterns.domain.User;

import java.util.HashMap;

public class UserRepository {
    private Map<String, User> store = new HashMap<>();

    public void addUser(String userId, User user) {
        store.put(userId, user);
    }

    public User getUser(String userId) {
        return store.get(userId);
    }
}