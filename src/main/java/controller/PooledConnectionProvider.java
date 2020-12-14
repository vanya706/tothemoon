package controller;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnectionProvider implements ConnectionProvider{

    private DataSource dataSource;

    private PooledConnectionProvider(){

        //TODO HikariConnection

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
