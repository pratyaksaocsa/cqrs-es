package com.tutorial.cqrses.patterns.domain;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import lombok.Data;

@Data
public class UserContact {
    private Map<String, Set<Contact>> contactByType = new HashMap<>();
}
