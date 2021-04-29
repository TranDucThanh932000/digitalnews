package context;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBContext {

    InitialContext initial;
    Context context;
    // tìm hiểu , biến Static là gì?
    private static String dbname, serverName, portNumber, image, username, password;
    private static boolean isConnect = true;

    
    public DBContext() {

    }
    //get information from context.xml
    public void setValue() throws Exception {
        try {
            if (isConnect) {
                initial = new InitialContext();
                Object obj = initial.lookup("java:comp/env");
                context = (Context) obj;
                serverName = context.lookup("serverName").toString();
                dbname = context.lookup("dbName").toString();
                portNumber = context.lookup("portNumber").toString();
                username = context.lookup("username").toString();
                password = context.lookup("password").toString();
                image = context.lookup("imageFolder").toString();
                isConnect = false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //get Connection
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbname;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, username, password);
    }

    //close Connection of ResultSet , PreparedStatement, Connection
    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    //get pathImage
    public String getImagePath() throws Exception {
        return image;
    }
}
