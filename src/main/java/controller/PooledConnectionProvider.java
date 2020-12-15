package controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnectionProvider implements ConnectionProvider{

    private DataSource dataSource;

    private static final PooledConnectionProvider instance = new PooledConnectionProvider();

    private PooledConnectionProvider(){

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(System.getenv("DB_JDBC_URL"));
        hikariConfig.setUsername(System.getenv("DB_USER_NAME"));
        hikariConfig.setPassword(System.getenv("DB_USER_PASSWORD"));

        hikariConfig.setMinimumIdle(Integer.parseInt(System.getenv("MINIMUM_IDLE")));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(System.getenv("MAXIMUM_POOL_SIZE")));

        dataSource = new HikariDataSource(hikariConfig);

    }

    public static PooledConnectionProvider getInstance(){
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
