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
    @Before
    public void beforeInsert(){
        startTime=DateUtil.currentSeconds();
    }
    @After
    public void afterInsert(){
        endTime=DateUtil.currentSeconds();
        System.out.println("耗时："+(endTime-startTime)+"毫秒");
    }
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
    public void addBatchUser(){
        try {
            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            st=conn.prepareStatement("");
            StringBuffer sql=new StringBuffer("INSERT INTO `user` (`id`, `login_name`, `login_pass`, `pay_password`, `nick_name`, `user_type`, `bind_mobile`,  `state`, `name`,  `create_at`, `create_by`, `last_update_at`, `last_update_by`, `is_deleted`) VALUES ");
            for(int i=1;i<userNumI;i++){
                sql.append("(");
                sql.append(i+",");
                sql.append("'lvpeng-"+i+"',");
                String pass=DigestUtil.md5Hex("lvpeng-pass-"+i);
                String payPass=DigestUtil.md5Hex("lvpeng-paypass-"+i);
                sql.append("'"+pass+"',");
                sql.append("'"+payPass+"',");
                sql.append("'lvpeng-nick-"+i+"',");
                sql.append("2,");
                if(i>9){
                    sql.append("'131892817"+i+"',");
                }else {
                    sql.append("'1311234567"+i+"',");
                }
                sql.append("1,");
                sql.append("'lvpeng-"+i+"',");

                sql.append("'"+DateUtil.now() +"',");
                sql.append("'lvpeng"+i+"',");
                sql.append("'"+DateUtil.now() +"',");
                sql.append("'lvpeng"+i+"',");
                sql.append("0");

                if(i==userNumI-1){
                    sql.append(")");
                }else {
                    sql.append("),");
                }

            }
            st.addBatch(sql.toString());
            st.executeBatch();
            st.clearBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
    @Test
    public void addBatchProd(){
        try {
            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            st=conn.prepareStatement("");
            StringBuffer sql=new StringBuffer("INSERT INTO `prod` (`id`, `prod_name`, `shop_id`, `ori_price`, `price`, `brief`, `attribute_list`, `content`,  `status`, `category_id`, `sold_num`, `total_stocks`, `delivery_mode`, `delivery_template_id`) VALUES ");
            for(int i=1;i<prodNumI;i++){
                sql.append("(");
                sql.append(i+",");
                sql.append("'prod-"+i+"',");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append("'商品描述-"+i+"',");
                sql.append(null+",");
                sql.append("'详细描述-"+i+"',");
                sql.append("1,");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+"");

                if(i==prodNumI-1){
                    sql.append(")");
                }else {
                    sql.append("),");
                }
            }
            st.addBatch(sql.toString());
            st.executeBatch();
            st.clearBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
    @Test
    public void addBatchSku(){
        try {
            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            st=conn.prepareStatement("");
            StringBuffer sql=new StringBuffer("INSERT INTO `sku` (`id`, `prod_id`,  `ori_price`, `price`, `stocks`, `actual_stocks`, `update_time`, `rec_time`, `party_code`, `model_id`,  `sku_name`, `prod_name`, `create_at`, `create_by`, `last_update_at`, `last_update_by`, `is_deleted`) VALUES ");
            for(int i=1;i<prodNumI;i++){
                sql.append("(");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append(i+",");
                sql.append("'"+DateUtil.now() +"',");
                sql.append("'"+DateUtil.now() +"',");

                sql.append(i+",");
                sql.append(i+",");
                sql.append("'sku-"+i+"',");
                sql.append("'prod-"+i+"',");

                sql.append("'"+DateUtil.now() +"',");
                sql.append("'lvpeng"+i+"',");
                sql.append("'"+DateUtil.now() +"',");
                sql.append("'lvpeng"+i+"',");
                sql.append("0");


                if(i==prodNumI-1){
                    sql.append(")");
                }else {
                    sql.append("),");
                }
            }
            st.addBatch(sql.toString());
            st.executeBatch();
            st.clearBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
    @Test
    public void addBatchOrder(){
        try {
            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            st=conn.prepareStatement("");
            StringBuffer sql=new StringBuffer("");

            for(int i=1;i<=orderNumI;i++){
                sql.append("INSERT INTO `order` (`shop_id`, `prod_name`, `user_id`, `order_number`, `total`, `actual_total`, `reduce_amount`, `pay_type`, `remarks`, `status`, `dvy_type`, `dvy_id`, `dvy_flow_id`, `freight_amount`, `order_addr_id`, `product_nums`, `pay_time`, `dvy_time`, `finally_time`, `cancel_time`, `is_payed`, `delete_status`, `refund_sts`, `close_type`, `create_at`, `create_by`, `last_update_at`, `last_update_by`, `is_deleted`) VALUES ");
                for (int j = 1; j <= orderNumJ; j++) {

                    sql.append("(");
//                    sql.append(j*i+",");
                    sql.append(j+",");
                    sql.append("'prod-"+j+"',");
                    sql.append(i+",");
                    sql.append("'order-"+ IdUtil.randomUUID()+"-"+j+"',");

                    sql.append(j+",");
                    sql.append(j+",");
                    sql.append(j+",");
                    sql.append("2,");
                    sql.append("'备注-"+j+"',");
//                    1:待付款 2:待发货 3:待收货 4:待评价 5:取消'
                    if(j%3==1){
                        sql.append("1,");
                    }else if(j%3==2){
                        sql.append("2,");
                    }else if(j%3==3){
                        sql.append("3,");
                    }else if(j%3==4){
                        sql.append("4,");
                    }else {
                        sql.append("5,");

                    }
                    sql.append("'配送类型-"+j+"',");
                    sql.append("1,");
                    sql.append("'wl-"+(DateUtil.currentSeconds()+j+i)+"',");
                    sql.append("10,");
                    sql.append(j+",");
                    sql.append("100,");

                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("1,");
                    sql.append("0,");
                    sql.append("0,");
                    sql.append("0,");



                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'lvpeng"+i+"',");
                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'lvpeng"+i+"',");
                    sql.append("0");

                    if(j==orderNumJ){
                        sql.append(")");
                    }else {
                        sql.append("),");
                    }

                }
//                System.out.println(sql.toString());
                st.addBatch(sql.toString());
                st.executeBatch();
                st.clearBatch();
                conn.commit();
                sql=new StringBuffer();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
    @Test
    public void addBatchOrderItem(){
        try {
            conn= JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            st=conn.prepareStatement("");
            StringBuffer sql=new StringBuffer("");
            for(int i=1;i<=orderNumI;i++){
                sql.append("INSERT INTO `order_item` (`shop_id`, `order_number`, `prod_id`, `sku_id`, `prod_count`, `prod_name`, `sku_name`,  `price`, `user_id`, `product_total_amount`, `reduce_amount`, `actual_total`, `rec_time`, `comm_sts`, `create_at`, `create_by`, `last_update_at`, `last_update_by`, `is_deleted`) VALUES ");
                for (int j = 1; j <= orderNumJ; j++) {

                    sql.append("(");
                    sql.append(i+",");
                    sql.append("'order-"+ IdUtil.randomUUID()+"-"+j+"',");

                    sql.append(i+",");
                    sql.append(i+",");
                    sql.append(j+",");
                    sql.append("'prod-"+i+"',");
                    sql.append("'sku-"+i+"',");

                    sql.append(j+",");
                    sql.append(i+",");
                    sql.append(i+",");
                    sql.append(i+",");
                    sql.append(i+",");

                    sql.append("'"+DateUtil.now() +"',");

                    sql.append(j+",");


                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'lvpeng"+i+"',");
                    sql.append("'"+DateUtil.now() +"',");
                    sql.append("'lvpeng"+i+"',");
                    sql.append("0");

                    if(j==orderNumJ){
                        sql.append(")");
                    }else {
                        sql.append("),");
                    }

                }
//                System.out.println(sql.toString());
                st.addBatch(sql.toString());
                st.executeBatch();
                st.clearBatch();
                conn.commit();
                sql=new StringBuffer();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
