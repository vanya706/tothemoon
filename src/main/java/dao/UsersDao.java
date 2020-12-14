package dao;

import controller.ConnectionProvider;
import entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsersDao {

    private ConnectionProvider connectionProvider = null;

    public Users create(Users users){

        try(Connection connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tothemoon.users(firstName,lastName,login, password)" +
                    "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            )
        ){

            statement.setString(1, users.getFirstName());
            statement.setString(2, users.getLastName());
            statement.setString(3, users.getLogin());
            statement.setString(4, users.getPassword());

            statement.executeUpdate();

            return null;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }



    public Users findByLogin(String login){
        return null;
    }

    public Users findById(Long id){
        return null;
    }

    public List<Users> findAll(){
        return null;
    }

}
