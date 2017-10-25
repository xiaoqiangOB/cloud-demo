package com.ahut.core.common.db.util;

import com.ahut.core.common.db.constant.DBProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by c2292 on 2017/10/25.
 */
public abstract class DBUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtils.class);

    public static DBProvider getDBType(String dbProductName){
        String lowerCase = dbProductName.toLowerCase();
        if(lowerCase.contains("oracle")){
            return DBProvider.ORACLE;
        }
        if(lowerCase.contains("mysql")){
            return DBProvider.MYSQL;
        }
        if (lowerCase.contains("db2")) {
            return DBProvider.DB2;
        }
        if(lowerCase.contains("sybase")){
            return DBProvider.SYSBASE;
        }
        if(lowerCase.contains("microsoft sql server")){
            return DBProvider.MSSQL;
        }
        if(lowerCase.contains("informix")){
            return DBProvider.INFORMIX;
        }
        if(lowerCase.contains("derby")) {
            return DBProvider.DERBY;
        }
        return DBProvider.UNKNOWN;
    }

    public static void quiteCloseSalStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Got an exception on closing sql statement : {}",e.getMessage(),e);
            }
        }
    }

    public static void quiteCloseConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Got an exception on closingg sql connection : {}",e.getMessage(),e);
            }
        }
    }

    public static boolean checkDataSourceAlive(DataSource dataSource, String checkAliveSql){
          return true;
//        Connection connection = null;
//        PreparedStatement  ps = null;
//        try {
//            connection = dataSource.getConnection();
//            ps = connection.prepareStatement(checkAliveSql);
//            ps.execute();
//        } catch (SQLException e) {
//            quiteCloseSalStatement(ps);
//            quiteCloseConnection(connection);
//            return false;
//        }
//        return true;
    }

    public static boolean checkConnectionAlive(Connection connection,String checkAliveSql){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(checkAliveSql);
            ps.execute();
        } catch (SQLException e) {
            return false;
        }finally {
            quiteCloseSalStatement(ps);
        }
        return true;
    }

    /**
     * 将空字符串转成null返回  不为null的时候直接原样返回
     *
     * @param value  待处理的空字符串
     * @return 如果字符串为空则返回null 否则原样返回
     */
    public static String convertEmptyStringToNull(String value){
        if("".equals(value)){
            return null;
        }else{
            return value;
        }
    }
}
