package ua.my.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Configuration
@ComponentScan("ua.my")
@EnableTransactionManagement
@EnableWebMvc


public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
//
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
            (DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        Properties jpaProp = new Properties();
        jpaProp.put("useUnicode","true");
        jpaProp.put("charSet", "UTF-8");
        jpaProp.put("hibernate.hbm2ddl.auto", "create-drop");
//        jpaProp.put("hibernate.hbm2ddl.auto", "validate");

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaProperties(jpaProp);
        entityManagerFactory.setPackagesToScan("ua.my");


        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        return adapter;
    }



// @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/ConferenceTicketSale?verifyServerCertificate=false&useSSL=true&characterEncoding=UTF-8");
//        ds.setUsername("root");
//        ds.setPassword("root");
//        return ds;
//    }

    @Bean
    public DataSource dataSource()throws URISyntaxException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        URI dbUri = new URI("mysql://bafb1dae591344:a8398252@us-cdbr-iron-east-05.cleardb.net/" +
                "heroku_3a360bf682fe2fe?reconnect=true&characterEncoding=UTF-8");

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath()+"?characterEncoding=UTF-8";
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(dbUrl);
    ds.setUsername(username);
    ds.setPassword(password);
        return ds;
    }

//@Bean
//public BasicDataSource dataSource() throws URISyntaxException {
//    URI dbUri = new URI("mysql://bafb1dae591344:a8398252@us-cdbr-iron-east-05.cleardb.net/heroku_3a360bf682fe2fe?reconnect=true");
//
//    String username = dbUri.getUserInfo().split(":")[0];
//    String password = dbUri.getUserInfo().split(":")[1];
//    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//
//    BasicDataSource basicDataSource = new BasicDataSource();
//    basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//    basicDataSource.setUrl(dbUrl);
//    basicDataSource.setUsername(username);
//    basicDataSource.setPassword(password);
//    return basicDataSource;
//}

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setPrefix("/dynamic/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }


}
