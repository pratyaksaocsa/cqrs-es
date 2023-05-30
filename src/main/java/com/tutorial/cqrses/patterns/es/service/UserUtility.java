package com.tutorial.cqrses.patterns.es.service;

import java.util.List;

import com.tutorial.cqrses.patterns.domain.Address;
import com.tutorial.cqrses.patterns.domain.Contact;
import com.tutorial.cqrses.patterns.domain.User;
import com.tutorial.cqrses.patterns.es.events.Event;
import com.tutorial.cqrses.patterns.es.events.UserAddressAddedEvent;
import com.tutorial.cqrses.patterns.es.events.UserAddressRemovedEvent;
import com.tutorial.cqrses.patterns.es.events.UserContactAddedEvent;
import com.tutorial.cqrses.patterns.es.events.UserContactRemovedEvent;
import com.tutorial.cqrses.patterns.es.events.UserCreatedEvent;
import com.tutorial.cqrses.patterns.es.repository.EventStore;

public class UserUtility {

    public static User recreateUserState(EventStore store, String userId) {
        User user = null;

        List<Event> events = store.getEvents(userId);
        for (Event event : events) {
            if (event instanceof UserCreatedEvent) {
                UserCreatedEvent e = (UserCreatedEvent) event;
                user = new User(e.getUserId(), e.getFirstName(), e.getLastName());
            }
            if (event instanceof UserAddressAddedEvent) {
                UserAddressAddedEvent e = (UserAddressAddedEvent) event;
                Address address = new Address(e.getCity(), e.getState(), e.getPostCode());
                if (user != null)
                    user.getAddresses().add(address);
            }
            if (event instanceof UserAddressRemovedEvent) {
                UserAddressRemovedEvent e = (UserAddressRemovedEvent) event;
                Address address = new Address(e.getCity(), e.getState(), e.getPostCode());
                if (user != null)
                    user.getAddresses().remove(address);
            }
            if (event instanceof UserContactAddedEvent) {
                UserContactAddedEvent e = (UserContactAddedEvent) event;
                Contact contact = new Contact(e.getContactType(), e.getContactDetails());
                if (user != null)
                    user.getContacts().add(contact);
            }
            if (event instanceof UserContactRemovedEvent) {
                UserContactRemovedEvent e = (UserContactRemovedEvent) event;
                Contact contact = new Contact(e.getContactType(), e.getContactDetails());
                if (user != null)
                    user.getContacts().remove(contact);
            }
        }

        return user;
    }
    
}
