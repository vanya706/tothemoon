package dao;

import controller.ConnectionProvider;
import controller.PooledConnectionProvider;
import entities.Users;

import java.sql.*;
import java.util.List;

public class UsersDao {

    private ConnectionProvider connectionProvider = PooledConnectionProvider.getInstance();

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

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();

            return users.toBuilder()
                    .idUser(resultSet.getLong("idUser"))
                    .build();

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
