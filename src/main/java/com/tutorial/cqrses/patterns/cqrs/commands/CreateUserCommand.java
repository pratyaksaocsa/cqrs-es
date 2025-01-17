package com.tutorial.cqrses.patterns.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCommand {
    private String userId;
    private String firstName;
    private String lastName;
}
