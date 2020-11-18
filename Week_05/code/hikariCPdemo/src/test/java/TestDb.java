import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.HUtils;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TestDb {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    static String insertsql="";
    static String updatesql="";
    static String delsql="";
    static String querysql="";
    @Before
    public void queryProperties() throws Exception{
        ByteSource bs = Resources.asByteSource(Resources.getResource("sql.properties"));
        InputStream in = bs.openBufferedStream();
        Properties p = new Properties();
        p.load(new InputStreamReader(in, Charset.forName("UTF-8")));
        insertsql=p.getProperty("insertsql");
        updatesql=p.getProperty("updatesql");
        delsql=p.getProperty("delsql");
        querysql=p.getProperty("findsql");
    }
    @Test
    public void printsql(){
        System.out.println("insertsql=>"+insertsql);
        System.out.println("updatesql=>"+updatesql);
        System.out.println("delsql=>"+delsql);
        System.out.println("querysql=>"+querysql);
    }

    @Test
    public void insert(){
        try {
            conn= HUtils.getConnection();
            st=conn.prepareStatement(insertsql);
            int num = st.executeUpdate();
            Assert.assertNotEquals("insert-error",0,num);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            HUtils.release(conn,st,rs);
        }

    }
    @Test
    public void update(){
        try {
            conn=HUtils.getConnection();
            st=conn.prepareStatement(updatesql);
            int num = st.executeUpdate();
            Assert.assertNotEquals("update-error",0,num);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            HUtils.release(conn,st,rs);
        }

    }
    @Test
    public void del(){
        try {
            conn=HUtils.getConnection();
            st=conn.prepareStatement(delsql);
            int num = st.executeUpdate();
            Assert.assertNotEquals("del-error",0,num);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            HUtils.release(conn,st,rs);
        }

    }
    @Test
    public void query(){
        try {
            conn=HUtils.getConnection();
            st=conn.prepareStatement(querysql);
            rs  = st.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            HUtils.release(conn,st,rs);
        }

    }
    @Test
    public void addBatch(){
        try {
            conn=HUtils.getConnection();
            st=conn.prepareStatement(insertsql);
            for(int i=1;i<10;i++){
                st.addBatch();

                if(i%2==0){
                    st.executeBatch();
                    st.clearBatch();
                }
            }
            st.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            HUtils.release(conn,st,rs);
        }
    }
}
