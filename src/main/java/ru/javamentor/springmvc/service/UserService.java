package ru.javamentor.springmvc.service;


import javax.validation.Valid;
import ru.javamentor.springmvc.model.User;
import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(@Valid User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserByLogin(String login);

    User getUserById(long id);
}
