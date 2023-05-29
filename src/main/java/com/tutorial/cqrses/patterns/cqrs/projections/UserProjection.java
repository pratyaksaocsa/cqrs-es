package com.tutorial.cqrses.patterns.cqrs.projections;

import java.util.Set;

import com.tutorial.cqrses.patterns.cqrs.queries.AddressByRegionQuery;
import com.tutorial.cqrses.patterns.cqrs.queries.ContactByTypeQuery;
import com.tutorial.cqrses.patterns.cqrs.repository.UserReadRepository;
import com.tutorial.cqrses.patterns.domain.Address;
import com.tutorial.cqrses.patterns.domain.Contact;
import com.tutorial.cqrses.patterns.domain.UserAddress;
import com.tutorial.cqrses.patterns.domain.UserContact;

public class UserProjection {
    private UserReadRepository repository;

    public UserProjection(UserReadRepository repository) {
        this.repository = repository;
    }

    public Set<Contact> handle(ContactByTypeQuery query) throws Exception {
        UserContact userContact = repository.getUserContact(query.getUserId());
        if (userContact == null)
            throw new Exception("User does not exist.");
        return userContact.getContactByType()
            .get(query.getContactType());
    }

    public Set<Address> handle(AddressByRegionQuery query) throws Exception {
        UserAddress userAddress = repository.getUserAddress(query.getUserId());
        if (userAddress == null)
            throw new Exception("User does not exist.");
        return userAddress.getAddressByRegion()
            .get(query.getState());
    }
}
