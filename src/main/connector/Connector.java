package main.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class Connector {

    private static int count;
    private static String user;
    private static String passWord;
    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mortgage_Calculator"
                , user
                , passWord);
        return c;
    }

    public static void setUser(String user) {
        Connector.user = user;
    }

    public static void setPassWord(String passWord) {
        Connector.passWord = passWord;
    }
}
