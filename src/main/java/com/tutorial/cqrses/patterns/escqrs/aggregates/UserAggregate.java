package com.tutorial.cqrses.patterns.escqrs.aggregates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tutorial.cqrses.patterns.cqrs.commands.CreateUserCommand;
import com.tutorial.cqrses.patterns.cqrs.commands.UpdateUserCommand;
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
import com.tutorial.cqrses.patterns.es.service.UserUtility;

public class UserAggregate {

    private EventStore writeRepository;

    public UserAggregate(EventStore repository) {
        this.writeRepository = repository;
    }

    public List<Event> handleCreateUserCommand(CreateUserCommand command) {
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName());
        writeRepository.addEvent(command.getUserId(), event);
        return Arrays.asList(event);
    }

    public void handleCreateUserCommandtoDB(CreateUserCommand command) throws SQLException {
        UserCreatedEvent event = 
            new UserCreatedEvent(command.getUserId(),
            command.getFirstName(), 
            command.getLastName());
        writeRepository.addEventToDB(command.getUserId(), event);
    }

    public List<Event> handleUpdateUserCommand(UpdateUserCommand command) {
        User user = UserUtility.recreateUserState(writeRepository, command.getUserId());
        List<Event> events = new ArrayList<>();

        List<Contact> contactsToRemove = user.getContacts()
            .stream()
            .filter(c -> !command.getContacts().contains(c))
            .collect(Collectors.toList());
        for (Contact contact : contactsToRemove) {
            UserContactRemovedEvent contactRemovedEvent = new UserContactRemovedEvent(contact.getType(), contact.getDetail());
            events.add(contactRemovedEvent);
            writeRepository.addEvent(command.getUserId(), contactRemovedEvent);
        }

        List<Contact> contactsToAdd = command.getContacts()
            .stream()
            .filter(c -> !user.getContacts().contains(c))
            .collect(Collectors.toList());
        for (Contact contact : contactsToAdd) {
            UserContactAddedEvent contactAddedEvent = new UserContactAddedEvent(contact.getType(), contact.getDetail());
            events.add(contactAddedEvent);
            writeRepository.addEvent(command.getUserId(), contactAddedEvent);
        }

        List<Address> addressesToRemove = user.getAddresses()
            .stream()
            .filter(a -> !command.getAddresses().contains(a))
            .collect(Collectors.toList());
        for (Address address : addressesToRemove) {
            UserAddressRemovedEvent addressRemovedEvent = new UserAddressRemovedEvent(address.getCity(), address.getState(), address.getPostcode());
            events.add(addressRemovedEvent);
            writeRepository.addEvent(command.getUserId(), addressRemovedEvent);
        }

        List<Address> addressesToAdd = command.getAddresses()
            .stream()
            .filter(a -> !user.getAddresses().contains(a))
            .collect(Collectors.toList());
        for (Address address : addressesToAdd) {
            UserAddressAddedEvent addressAddedEvent = new UserAddressAddedEvent(address.getCity(), address.getState(), address.getPostcode());
            events.add(addressAddedEvent);
            writeRepository.addEvent(command.getUserId(), addressAddedEvent);
        }

        return events;
    }

}
