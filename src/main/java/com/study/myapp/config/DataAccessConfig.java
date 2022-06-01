package com.study.myapp.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan(value="com.study.myapp.mapper")
public class DataAccessConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.study.myapp.model");
        sessionFactory.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")
        );
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml")
        );
        return sessionFactory.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        //SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다.
        //쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
