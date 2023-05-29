package com.tutorial.cqrses.patterns.cqrs.repository;
import java.util.Map;

import com.tutorial.cqrses.patterns.domain.User;

import java.util.HashMap;

public class UserWriteRepository {
    private Map<String, User> store = new HashMap<>();
    
    public void addUser(String id, User user) {
        store.put(id, user);
    }

    public User getUser(String id) {
        return store.get(id);
    }
}