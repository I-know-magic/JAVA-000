package com.example.readwriteseparationv1.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Slf4j
public class MultiDataSource  extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    /**
     * 设置dataSourceKey的值
     * @param dataSource
     */
    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    /**
     * 清除dataSourceKey的值
     */
    public static void toDefault() {
        dataSourceKey.remove();
    }
    /**
     * 返回当前dataSourceKey的值
     */
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("determineCurrentLookupKey->"+dataSourceKey.get());
        return dataSourceKey.get();
    }

}
