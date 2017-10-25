package com.ahut.core.common.db.dao;

import com.ahut.core.common.db.page.PageParam;

import java.util.List;
import java.util.Map;
/**
 * Created by c2292 on 2017/10/24.
 */
public interface BaseDao<T> {
    /**
     * 根据实体对象新增记录
     *
     * @param entity 数据库实体对象
     * @return 影响数据库记录条数
     */
    int insert(T entity);

    /**
     * 批量保存对象
     *
     * @param list 数据库实体对象集合
     * @return 影响的数据库记录条数
     */
    int insert(List<T> list);

    /**
     * 根据主键更新实体对应的记录
     *
     * @param entity 数据库实体对象
     * @return 影响的数据库记录条数
     */
    int updateByPrimaryKey(T entity);


    /**
     * 根据主键批量更新对象列表
     *
     * @param list 待更新的对象集合
     * @return 影响的数据库记录条数
     */
    int updataByPrimaryKey(List<T> list);

    /**
     * 局部更新实体
     *
     * @param entity 数据库实体对象
     * @return  影响的数据库记录条数
     */
    int scopeUpdateByPK(T entity);

    /**
     * 根据条件更新指定字段
     *
     * @param changes 待更新的字段
     * @param condition 记录匹配条件
     * @return  影响的数据库记录条数
     */
    int updateBy(Map<String,Object> changes,Map<String,Object> condition);

    /**
     * 根据条件更新制定字段
     *
     * @param changes 待更新的对象 里面包含了待更新的字段
     * @param condition 记录匹配条件
     * @return 影响行数
     */
    int updateBy(T changes,Map<String,Object> condition);

    /**
     * 根据主键删除记录
     *
     * @param entity 数据库实体对象
     * @return 影响的记录条数
     */
    int deleteByPrimaryKey(T entity);

    /**
     * 批量根据主键删除对象
     *
     * @param entity 数据库实体对象集合
     * @return影响的数据库记录条数
     */
    int deleteByPPrimaryKey(List<T> entity);

    /**
     * 根据字段删除
     *
     * @param paramMap 条件参数
     * @return 影响的数据库记录条数
     */
    int deleteBY(Map<String,Object> paramMap);

//    /**
//     * 分页查询
//     *
//     * @param pageParam 分页参数
//     * @param paramMap 业务条件查询参数
//     * @return 分页数据
//     */
//    PageBean listPage(PageParam pageParam,Map<String,Object> paramMap);


    /**
     * 根据条件查询多条记录
     *
     * @param paramMap 查询条件字段
     * @return 匹配的数据
     */
      List<T> listBy(Map<String,Object> paramMap);

    /**
     * 根据条件查询单挑记录
     *
     * @param paramMap 查询条件字段
     * @return 匹配的记录 若记录不存在则返回null
     */
      T getBy(Map<String,Object> paramMap);

    /**
     * 梭镖获取数据
     *
     * @param paramMap 查询条件字段
     * @return 匹配的记录 若不存在 则返回null
     */
    T getForUpdate(Map<String,Object> paramMap);


    /**
     * 锁表获取数据
     * @param paramMap  查询条件字段
     * @return 匹配的记录 如无记录 则返回size为0的list
     */
    List<T> getForUpdata(Map<String,Object> paramMap);

    /**
     * 获取序列 不管是普通的执行场景  还是批量的执行场景都可以使用
     *
     * @param seqName 序列名
     * @return 序列值对应的字符串
     */
    String setSeqNextValue(String seqName);


    /**
     * 用于分页查询
     *
     * @param pageParam 分页参数
     * @param paramMap 查询条件
     * @return
     */
    public List<T> listPage(PageParam pageParam,Map<String,Object> paramMap);
}
