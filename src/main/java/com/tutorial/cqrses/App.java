package com.tutorial.cqrses;

import java.sql.SQLException;

import com.tutorial.cqrses.patterns.cqrs.commands.CreateUserCommand;
import com.tutorial.cqrses.patterns.es.repository.EventStore;
import com.tutorial.cqrses.patterns.escqrs.aggregates.UserAggregate;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        EventStore eventStore = new EventStore();
        CreateUserCommand command = 
            new CreateUserCommand("AX003", "Haha", "Bolsan");
        UserAggregate aggregate = new UserAggregate(eventStore);
        try {
            aggregate.handleCreateUserCommandtoDB(command);
            System.out.println("Event berhasil dibuat");
        } catch (SQLException ex) {
            System.out.println("Event gagal dibuat");
            ex.printStackTrace();
        }
    }
}
