package com.tutorial.cqrses.patterns.cqrs.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactByTypeQuery {
    private String userId;
    private String contactType;
}
