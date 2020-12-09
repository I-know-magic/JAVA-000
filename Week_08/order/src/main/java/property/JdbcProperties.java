package property;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.Properties;


public class JdbcProperties {
    public static String driver = null;
    public static String url = null;
    public static String username = null;
    public static String password = null;
    static{
        try{
            ByteSource bs = Resources.asByteSource(Resources.getResource("db.properties"));
            InputStream in = bs.openBufferedStream();
            Properties prop = new Properties();
            prop.load(in);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
