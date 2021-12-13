package dao;

import entyty.User;

import java.util.Optional;

public interface UserDao<M> {
    Optional<M> getUserByEmail(String email, String password);
    void setUser(String email, String password, String name);
}
