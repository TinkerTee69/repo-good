package com.example;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing user accounts.
 *
 * <p>Provides operations for creating, retrieving, updating and
 * deleting users. All operations validate input before persisting.</p>
 */
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new user account.
     *
     * @param username the desired username, must be unique and non-empty
     * @param email    the user's email address, must be a valid format
     * @return the created {@link User} with a generated ID
     * @throws IllegalArgumentException if username or email is blank
     * @throws DuplicateUserException   if the username already exists
     */
    public User createUser(String username, String email) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }
        if (repository.existsByUsername(username)) {
            throw new DuplicateUserException("Username already taken: " + username);
        }
        User user = new User(username, email);
        return repository.save(user);
    }

    /**
     * Finds a user by their unique ID.
     *
     * @param id the user ID
     * @return an {@link Optional} containing the user, or empty if not found
     */
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Returns all registered users.
     *
     * @return list of all users, may be empty
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @throws UserNotFoundException if no user with the given ID exists
     */
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User not found: " + id);
        }
        repository.deleteById(id);
    }
}
