package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public Connection getConnection(){
        try {

            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:teste2.db");

        } catch (Exception e) {
            throw new RuntimeException(e);        }
    }
}
