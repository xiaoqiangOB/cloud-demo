package com.ahut.core.common.db.page;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by c2292 on 2017/10/24.
 */
public class BasePage implements Serializable{

    private static final long serialVersionUID = -942587026864925060L;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    protected List<?> getPageList(Class<?> mapperClass,String sqlId,Object sqlParameter,int pageIndex,int pageSize) throws Exception{
        SqlSession session = null;
        try {
            session = SqlSessionUtils.getSqlSession(sqlSessionFactory);
            if(pageIndex <= 0){
                pageIndex = 1;
            }
            if(pageSize <= 0){
                pageSize = 10;
            }
            PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
            return session.selectList(mapperClass.getName() + "." + sqlId,sqlParameter,pageBounds);
        } finally {
            session.close();
        }
    }
}
