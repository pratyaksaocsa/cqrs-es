package com.tutorial.cqrses.patterns.cqrs.commands;
import java.util.Set;

import com.tutorial.cqrses.patterns.domain.Address;
import com.tutorial.cqrses.patterns.domain.Contact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserCommand {
    private String userId;
    private Set<Address> addresses;
    private Set<Contact> contacts;
}
