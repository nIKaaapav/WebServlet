package dao.mappers;

import entyty.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static User mapRows(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");

       return User.builder()
               .name(name)
               .id(id)
               .email(email)
               .password(password)
               .build();
    }
}
