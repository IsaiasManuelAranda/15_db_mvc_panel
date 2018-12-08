package db;

import java.sql.*;

public class Mysql {
    private String db = "agenda_mvc";
    private String url = "jdbc:mysql://localhost/" + db;
    private String user = "user_mvc";
    private String pass = "pass_mvc.2018";
    Connection con = null;
    
    public Mysql(){
        try{
            con = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            System.out.println(e);         
        }
    }
    public Connection getConnection(){
        return con;
    }
    public void desconectar(){
        con = null;
    }
}