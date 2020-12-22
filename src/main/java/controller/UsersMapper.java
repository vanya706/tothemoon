package controller;

import entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersMapper {

    static public List<Users> mapList(ResultSet resultSet){

        try(resultSet){
            List<Users> usersList = new ArrayList<>();
            while (resultSet.next()) {

                Users users = Users.builder()
                        .idUser(resultSet.getLong(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .login(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();

                usersList.add(users);
            }

            return usersList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
