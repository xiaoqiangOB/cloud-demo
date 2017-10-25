package com.ahut.core.common.db.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by c2292 on 2017/10/20.
 */
@Configuration//@Configuration注解该类，等价 与XML中配置beans

@EnableTransactionManagement//spring Boot 使用事务，首先使用注解 @EnableTransactionManagement 开启事务支持后，然后在访问数据库的Service方法上添加注解 @Transactional 便可
public class MybaitsConfig implements TransactionManagementConfigurer{
    @Value("${mybatis.mapper-locations}")
    String mapperLocations;

    @Value("${mybatis.config-location}")
    String configLocation;

    @Value("${mybatis.type-aliases-package}")
    String typeAliasPackage;

    @Autowired
    DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    @Primary//@Primary的意思是在众多相同的bean中，优先使用用@Primary注解的bean.而@Qualifier这个注解则指定某个bean有没有资格进行注入
    public SqlSessionFactory sqlSessionFactory(){
        System.out.println("ConfigLocation:" + configLocation);
        System.out.println("MapperLocations:" + mapperLocations);
        System.out.println("TypeAliasPackage:" + typeAliasPackage);

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(typeAliasPackage);

        Resource[] resources = new Resource[0];

        try {
            resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bean.setMapperLocations(resources);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setConfigLocation(resolver.getResource(configLocation));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sessionTemplate")
    @Primary
    public SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Override
    @Bean(name = "annotationDrivenTransactionManager")
    @Primary
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
//    /*
//    MapperScannerConfig它可以将接口转换为Spring容器中的Bean
//     */
//    @Bean(name = "mapperScannerConfigurer")
//    @Primary
//    public MapperScannerConfigurer mapperScannerConfigurer(SqlSessionTemplate sqlSessionTemplate){
//       MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//       mapperScannerConfigurer.setBasePackage(basePackage);
//       mapperScannerConfigurer.setSqlSessionTemplateBeanName("sessionTemple");
//       return mapperScannerConfigurer;
//    }
}
