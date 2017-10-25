package com.ahut.core.common.db.dao;

import com.ahut.core.common.db.constant.DBErrCode;
import com.ahut.core.common.db.constant.DBProvider;
import com.ahut.core.common.db.exception.DBException;
import com.ahut.core.common.db.page.PageParam;
import com.ahut.core.common.db.util.DBUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by c2292 on 2017/10/24.
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T>{
    public static final String SQL_INSERT = "insert";
    public static final String SQL_UPDATE_BY_PK = "updateByPrimaryKey";
    public static final String SQL_SCOPE_UPDATE_BY_PK = "scopeUpdateByPK";
    public static final String SQL_DELETE_BY_PK = "deleteByPrimaryKey";
    public static final String SQL_DELETE = "deleteBy";
    public static final String SQL_UPDATE = "updateBy";
    public static final String SQL_LIST_PAGE = "listPage";
    public static final String SQL_LIST_BY = "listBy";
    public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam";//根据当前分页参数进行统计
    public static final String SQL_LIST_FOR_UPDATE = "listForUpdate";
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);
    private static volatile DBProvider DB_PROVIDER = null;

    @Autowired
    @Qualifier("sqlSessionFactory")
    protected SqlSessionFactory sqlSessionFactory;

    /**
     * 注入SqlSessionTemplate 实例(要求再spring中进行SqlSessionTemplate的配置).</br>
     * 可以调用sessionTemplate完成数据库操作
     */
    @Autowired
    @Qualifier("sessionTemplate")
    private SqlSessionTemplate sessionTemplate;

    protected SqlSessionTemplate getSessionTemplate(){
        return sessionTemplate;
    }

    /**
     * 获取普通的slq执行会话
     *
     * @return sql执行会话
     */
    public SqlSession getSqlSession(){
        return sessionTemplate;
    }
    @Override
    public int insert(T entity) {
        return insert(entity,sessionTemplate);

    }

    @Override
    public int insert(List<T> list) {
        return insert(list,sessionTemplate);
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        return updateByPrimaryKey(entity,SQL_UPDATE_BY_PK,sessionTemplate);
    }

    @Override
    public int updataByPrimaryKey(List<T> list) {
        return updateByPrimaryKey(list,SQL_UPDATE_BY_PK,sessionTemplate);
    }

    @Override
    public int scopeUpdateByPK(T entity) {
        return updateByPrimaryKey(entity,SQL_SCOPE_UPDATE_BY_PK,sessionTemplate);
    }

    @Override
    public int updateBy(Map<String, Object> changes, Map<String, Object> condition) {
        return updateBy(changes,condition,SQL_UPDATE,sessionTemplate);
    }

    @Override
    public int updateBy(T changes, Map<String, Object> condition) {
        return updateBy(changes,condition,SQL_UPDATE,sessionTemplate);
    }

    @Override
    public int deleteByPrimaryKey(T entity) {
        return deleteByPrimaryKey(entity,SQL_DELETE_BY_PK,sessionTemplate);
    }

    @Override
    public int deleteByPPrimaryKey(List<T> entity) {
        return deleteByPrimaryKey(entity,SQL_DELETE_BY_PK,sessionTemplate);
    }

    @Override
    public int deleteBY(Map<String, Object> paramMap) {
        return deleteBy(paramMap,SQL_DELETE,sessionTemplate);
    }

    @Override
    public List<T> listBy(Map<String, Object> paramMap) {
        return listBy(paramMap,SQL_LIST_BY,sessionTemplate);
    }

    @Override
    public T getBy(Map<String, Object> paramMap) {
        return getBy(paramMap,SQL_LIST_BY,sessionTemplate);
    }

    @Override
    public T getForUpdate(Map<String, Object> paramMap) {
        return getBy(paramMap,SQL_LIST_FOR_UPDATE,sessionTemplate);
    }

    @Override
    public List<T> getForUpdata(Map<String, Object> paramMap) {
        return listBy(paramMap,SQL_LIST_BY,sessionTemplate);
    }

    @Override
    public String setSeqNextValue(String seqName) {
        boolean isClosedConn = false;
        //获取当前线程的连接
        Connection connection = this.sessionTemplate.getConnection();
        //获取mybatis的SQLRunner类
        SqlRunner sqlRunner = null;
        try {
            if(connection.isClosed()){
                connection = sqlSessionFactory.openSession().getConnection();
                isClosedConn = true;
            }
            //要执行的SQL
            String sql = "";
            DBProvider dbProvider;
            if(DB_PROVIDER == null){
                dbProvider = DBUtils.getDBType(connection.getMetaData().getDatabaseProductName());
                DB_PROVIDER = dbProvider;
            }else{
                dbProvider = DB_PROVIDER;
            }
            //数据库驱动类
            //不同的数据库，连接sql语句
            if(DB_PROVIDER.DB2.equals(dbProvider)){
                sql = " VALUES " + seqName.toUpperCase() + ".NEXTVAL";
            }else if(DB_PROVIDER.ORACLE.equals(dbProvider)){
                sql = "SELECT " + seqName.toUpperCase() +".NEXTVAL FROM DUAL";
            }else if(DB_PROVIDER.MYSQL.equals(dbProvider)){
                sql = "SELECT FUN_SEQ('" + seqName.toUpperCase() + "')";
            }else{
                throw new DBException(DBErrCode.DB002.code(),"数据库 " + dbProvider.name() + "不支持序列的获取");
            }

            sqlRunner = new SqlRunner(connection);
            Object[] args = {};
            //执行SQL语句
            Map<String,Object> params = sqlRunner.selectOne(sql,args);
            for(Object o:params.values()){
                return o.toString();
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(DBErrCode.DB002.code(),String.format("获取序列出现错误!序列名称:{%s}",seqName));
        }finally {
            if (isClosedConn) {
                sqlRunner.closeConnection();
            }
        }
    }

    @Override
    public List<T> listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return null;
    }
    /***************************************************dao内部处理***********************/
    protected  int insert(T t,SqlSessionTemplate sqlSessionTemplate){
        if(t == null){
            throw new DBException(DBErrCode.DB005.code(),"数据库插入失败，插入对象为空");
        }
        try {
            return sqlSessionTemplate.insert(getStatement(SQL_INSERT),t);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB005.code(),DBErrCode.DB005.desc(),e);
        }
    }

    protected int insert(List<T> list,SqlSessionTemplate sqlSessionTemplate){
        if(list == null || list.size()<=0){
            throw new DBException(DBErrCode.DB005.code(),"数据库插入失败，插入对象为空");
        }
        //下层有处理异常 这里不再重新捕获
        int result = 0;
        for(int i=0;i<list.size();i++){
            this.insert(list.get(i),sqlSessionTemplate);
            result += 1;
        }
        return result;
    }

    protected  int updateByPrimaryKey(T t,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(t == null){
            throw new DBException(DBErrCode.DB004.code(),"数据库更新失败，更新集合为空");
        }
        try {
            return sqlSessionTemplate.update(getStatement(sqlId),t);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB004.code(),DBErrCode.DB004.desc(),e);
        }
    }

    protected  int updateByPrimaryKey(List<T> list,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(list == null || list.size() <= 0){
            throw new DBException(DBErrCode.DB004.code(),"数据库更新失败,更新集合为空");
        }
        //下层有处理异常 这里不再重新捕获
        int result = 0;
        for(int i=0;i<list.size();i++){
            this.updateByPrimaryKey(list.get(i),sqlId,sqlSessionTemplate);
            result += 1;
        }
        return result;
    }

    protected int updateBy(Map<String,Object> changes,Map<String,Object> condition,String sql,SqlSessionTemplate sqlSessionTemplate){
        if(changes == null || condition == null){
            throw new DBException(DBErrCode.DB004.code(),"数据库更新失败，更新字段或者条件为空");
        }
        HashMap<String,Map<String,Object>> updateParam = new HashMap<>();
        updateParam.put("v",changes);
        updateParam.put("c",condition);
        try {
            return sqlSessionTemplate.update(getStatement(sql),updateParam);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB004.code(),DBErrCode.DB004.desc(),e);
        }
    }

    protected int updateBy(T changes,Map<String,Object> condition,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(changes == null || condition ==null){
            throw new DBException(DBErrCode.DB004.code(),"数据库更新失败，更新对象或者更新条件为空");
        }
        HashMap<String,Object> updataParam = new HashMap<>();
        updataParam.put("v",changes);
        updataParam.put("c",condition);
        try {
            return sqlSessionTemplate.update(getStatement(sqlId),updataParam);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB004.code(),DBErrCode.DB004.desc(),e);
        }
    }

    protected  int deleteByPrimaryKey(T t,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(t == null){
            throw new DBException(DBErrCode.DB003.code(),"数据库删除失败,更新对象为空");
        }
        try {
            return sqlSessionTemplate.delete(getStatement(sqlId),t);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB003.code(),DBErrCode.DB003.desc(),e);
        }
    }

    protected int deleteByPrimaryKey(List<T> list,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(list == null || list.size() <= 0){
            throw new DBException(DBErrCode.DB003.code(),"数据库删除失败,更新集合为空");
        }
        int result = 9;
        for(int i=0;i<list.size();i++){
            this.deleteByPrimaryKey(list.get(i),sqlId,sqlSessionTemplate);
            result += 1;
        }
        return result;
    }

    protected int deleteBy(Map<String,Object> paramMap,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(paramMap == null || paramMap.isEmpty()){
            throw new DBException(DBErrCode.DB003.code(),"数据库删除失败，条件字段为空");
        }
        try {
            return sqlSessionTemplate.delete(getStatement(sqlId),paramMap);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB003.code(),DBErrCode.DB003.desc(),e);
        }
    }

    protected List<T> listBy(Map<String,Object> paramMap,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(paramMap == null){
            paramMap = new HashMap<>();
        }
        try {
            return sqlSessionTemplate.selectList(getStatement(sqlId),paramMap);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB002.code(),DBErrCode.DB002.desc(),e);
        }
    }

    protected T getBy(Map<String,Object> paramMap,String sqlId,SqlSessionTemplate sqlSessionTemplate){
        if(paramMap == null || paramMap.isEmpty()){
            //Todo
            return null;
        }
        try {
            return sqlSessionTemplate.selectOne(getStatement(sqlId),paramMap);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB002.code(),DBErrCode.DB002.desc(),e);
        }
    }

    public List<T> listPage(PageParam pageParam,String sqlId,Map<String,Object> paramMap,SqlSessionTemplate sqlSessionTemplate){
        int pageIndex = pageParam.getPageNum();
        int pageSize = pageParam.getNumPerPage();

        if(pageIndex <= 0){
            pageIndex = 1;
        }

        if(pageSize <= 0){
            pageSize = 10;
        }

        PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
        try {
            return sqlSessionTemplate.selectList(getStatement(sqlId),paramMap,pageBounds);
        } catch (Exception e) {
            throw new DBException(DBErrCode.DB002.code(),DBErrCode.DB002.desc(),e);
        }
    }


    protected String getStatement(String sqlId) {

        String name = this.getClass().getName();

        StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

        return sb.toString();
    }
}
