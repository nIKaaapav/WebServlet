package servise;

import dao.JdbsUserDao;
import entyty.User;

import java.sql.Connection;
import java.util.Optional;

public class UserService {
    private final JdbsUserDao loginDaoSQL;

    public UserService(Connection connection) {
        this.loginDaoSQL = new JdbsUserDao(connection);
    }

    public boolean getUserByEmail(String email, String password){
        Optional<User> userByEmail = loginDaoSQL.getUserByEmail(email, password);

        return userByEmail.isPresent();
    }

    public void setUser(String email, String password, String name){
        loginDaoSQL.setUser(email, password, name);
    }

}
