package Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbHelper {
    private Connection con;
    private Statement stmt;

    public DbHelper(){
        con = new ConnectionFactory().getConnection() ;

    }

    public void executarSQL(String sql){
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Sucesso");
    }


    public ResultSet querySql(String sql){
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            return rs;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
        return null;
    }
}
