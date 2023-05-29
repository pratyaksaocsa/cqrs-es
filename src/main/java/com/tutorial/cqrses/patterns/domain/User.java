package com.tutorial.cqrses.patterns.domain;

import java.util.Set;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    @NonNull
    private String userid;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Set<Contact> contacts;
    private Set<Address> addresses;
    
}