package com.example;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
//参考源码 java-api.cn.md
public class XaExampleApplication {

    public static void main(String[] args) throws IOException, SQLException {
        DataSource dataSource = getShardingDatasource();
        TransactionTypeHolder.set(TransactionType.XA); // 支持 TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
        Connection conn = dataSource.getConnection();
        String sql="INSERT INTO t_order (user_id, order_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) { // 使用 ShardingSphereDataSource
            conn.setAutoCommit(false);
            ps.setObject(1, 1000);
            ps.setObject(2, 1000);
            ps.executeUpdate();
            conn.commit();
        }
        try (PreparedStatement ps = conn.prepareStatement(sql)) { // 使用 ShardingSphereDataSource
            conn.setAutoCommit(false);
            ps.setObject(1, 2000);
            ps.setObject(2, 2000);
            ps.executeUpdate();
            conn.commit();
        }


    }

// 参考源码
    static private DataSource getShardingDatasource() throws IOException, SQLException {
        String fileName = "/META-INF/sharding-databases-tables.yaml";
        return YamlShardingSphereDataSourceFactory.createDataSource(getFile(fileName));
    }
    private static File getFile(final String fileName) {
        String path = XaExampleApplication.class.getResource(fileName).getPath();
        try {
            path = URLDecoder.decode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new File(path);
    }

}
