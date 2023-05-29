package com.tutorial.cqrses;

import com.tutorial.cqrses.patterns.crud.repositories.UserRepository;
import com.tutorial.cqrses.patterns.crud.service.UserService;

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
        UserRepository repo = new UserRepository();
        UserService userService = new UserService(repo);
        userService.createUser("AX001", "Pratyaksa", "Ocsa");
    }
}
