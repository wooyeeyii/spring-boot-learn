package com.chang.multi.datasource.config;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class BeetlSqlConfig {

    @Bean(name = "primaryBeetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getPrimaryBeetlSqlScannerConfigurer() {
        return getBeetlSqlScannerConfigurer("com.chang.multi.datasource.mapper.primary", "primarySqlManagerFactoryBean");
    }

    @Bean(name = "primarySqlManagerFactoryBean")
    public SqlManagerFactoryBean getTaskSqlManagerFactoryBean(@Qualifier("primaryDataSource") DataSource dataSource) {
        return getSqlManagerFactoryBean(dataSource, "/sql/primary");
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.multi.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondarySqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getSecondaryBeetlSqlScannerConfigurer() {
        String basePackage = "com.chang.multi.datasource.mapper.secondary";
        return getBeetlSqlScannerConfigurer(basePackage, "secondarySqlManagerFactoryBean");
    }

    @Bean(name = "secondarySqlManagerFactoryBean")
    public SqlManagerFactoryBean getSystemSqlManagerFactoryBean(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return getSqlManagerFactoryBean(dataSource, "/sql/secondary");
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.multi.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer(String basePackage, String beanName) {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage(basePackage);
        conf.setDaoSuffix("Mapper");
        conf.setSqlManagerFactoryBeanName(beanName);
        return conf;
    }

    private SqlManagerFactoryBean getSqlManagerFactoryBean(DataSource dataSource, String sqlRoot) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();

        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        Properties extProperties = new Properties();
        extProperties.put("FN.isEmpty", "org.beetl.ext.fn.EmptyExpressionFunction");
        extProperties.put("FN.isNotEmpty", "org.beetl.ext.fn.IsNotEmptyExpressionFunction");
        // beetlsql默认是开发模式，因此修改md的sql文件，不需要重启。此模式会每次sql调用都会检测md文件是否变化
        // 改为使用产品模式
        extProperties.put("PRODUCT_MODE", "true");
        factory.setExtProperties(extProperties);
        factory.setNc(new DefaultNameConversion());
        factory.setSqlLoader(new ClasspathLoader(sqlRoot));
        return factory;
    }

}
