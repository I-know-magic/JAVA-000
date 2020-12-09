import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.JdbcUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class TestOrder {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    static String insertsql="";
    static String updatesql="";
    static String delsql="";
    static String querysql="";
    static int orderNumJ=10000;
    static int orderNumI=100;

    static int userNumJ=10000;
    static int userNumI=100;

    static int prodNumJ=10000;
    static int prodNumI=100;
    static long startTime;
    static long endTime;

//    static int OrderNumJ=10000;
//    static int OrderNumI=100;
//    static int INum=100;
//    @Before
//    public void queryProperties() throws Exception{
//        ByteSource bs = Resources.asByteSource(Resources.getResource("db.properties"));
//        InputStream in = bs.openBufferedStream();
//        Properties p = new Properties();
//        p.load(new InputStreamReader(in, Charset.forName("UTF-8")));
//        insertsql=p.getProperty("insertsql");
//        updatesql=p.getProperty("updatesql");
//        delsql=p.getProperty("delsql");
//        querysql=p.getProperty("findsql");
//    }
//    @Before
//    public void beforeInsert(){
//        startTime=DateUtil.currentSeconds();
//    }
//    @After
//    public void afterInsert(){
//        endTime=DateUtil.currentSeconds();
//        System.out.println("耗时："+(endTime-startTime)+"毫秒");
//    }
//    @Test
//    public void query(){
//        try {
//            conn=JdbcUtils.getConnection();
//            st=conn.prepareStatement(querysql);
//            rs  = st.executeQuery();
//            if(rs.next()){
//                System.out.println(rs.getString("id"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.release(conn,st,rs);
//        }
//
//    }
    @Test
    public void queryAll() {
        try {
            String sql = "select * from t_order ";
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("order_number"));
            }
        }catch (Exception e){

        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
    @Test
    public void insert(){
        try {
            conn=JdbcUtils.getConnection();
            StringBuffer sql=new StringBuffer("INSERT INTO `t_order` (  `prod_name`, `user_id`, `order_number`, `total`, `status`, `is_deleted`) VALUES ( 'prod-1', '3', 'order-2e85813d-fe66-4100-8978-b1009-3333', '1.00','0', '0') ");

            st=conn.prepareStatement(sql.toString());
            int num = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }

    }

    @Test
    public void update(){
        try {
            String sql="update t_order set prod_name='p-123' where shop_id=1";
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }

    }
    @Test
    public void delete(){
        try {
            String sql="delete from  t_order  where user_id=3";
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }

    }
}
