package com.tutorial.cqrses.patterns.domain;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import lombok.Data;

@Data
public class UserAddress {
    private Map<String, Set<Address>> addressByRegion = new HashMap<>();
}
