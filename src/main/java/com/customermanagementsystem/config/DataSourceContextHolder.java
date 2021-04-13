package com.customermanagementsystem.config;

import com.customermanagementsystem.utils.DataSourceEnum;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> contextHolder = new ThreadLocal<>();

    public static void setCurrentDb(DataSourceEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DataSourceEnum getCurrentDb() {
        return contextHolder.get();
    }
}
