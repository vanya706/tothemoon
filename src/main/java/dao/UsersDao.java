package dao;

import controller.ConnectionProvider;
import controller.PooledConnectionProvider;
import controller.UsersMapper;
import entities.Users;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersDao {

    private UsersMapper usersMapper = new UsersMapper();

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


            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            return users.toBuilder()
                    .idUser(resultSet.getLong(1))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }



    public Optional<Users> findByLogin(String login){
        try(Connection connection = connectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT idUser,firstName,lastName,login,password FROM tothemoon.users WHERE login=?"
            )
        ){

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            return usersMapper.mapList(resultSet).stream()
                    .findFirst();

            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Users findById(Long id){
        return null;
    }

    public List<Users> findAll(){
        return null;
    }

}
