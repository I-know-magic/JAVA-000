package utils;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import property.JdbcProperties;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = JdbcProperties.driver;
    private static String url = JdbcProperties.url;
    private static String username = JdbcProperties.username;
    private static String password = JdbcProperties.password;

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, username,password);
    }

    public static void release(Connection conn, Statement st, ResultSet rs){

        if(rs!=null){
            try{
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;

        }
        if(st!=null){
            try{
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        if(conn!=null){
            try{
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
