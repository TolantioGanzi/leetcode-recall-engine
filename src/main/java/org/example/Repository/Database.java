package org.example.Repository;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
public class Database {
    // Creating ONE H2 database source
    private static final JdbcDataSource dataSource = new JdbcDataSource();

    // why is this static here?
    static {
        dataSource.setURL("jdbc:h2:tcp://localhost/~/problems_test");
        dataSource.setUser("sa");
        dataSource.setPassword("1234");
    }

    // Returned configured database
    public static DataSource getDataSource() {
        return dataSource;
    }
}
