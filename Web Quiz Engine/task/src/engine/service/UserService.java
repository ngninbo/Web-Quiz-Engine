package engine.service;

import engine.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);
    void save(User user);
}
