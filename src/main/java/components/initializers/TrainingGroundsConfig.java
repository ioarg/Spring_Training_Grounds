package components.initializers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import components.initializers.ViewResolution.ViewResolverFactory;
import components.initializers.ViewResolution.ViewResolverType;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan({"components", "model/common", "model/customer_db"})
@PropertySource({"classpath:/sql/connection.properties"})
public class TrainingGroundsConfig implements WebMvcConfigurer {
    private static final String RESOURCE_BASE = "classpath:/static_resources/";
    private static final Logger console = LoggerFactory.getLogger(TrainingGroundsConfig.class);
    @Autowired
    private Environment env;

    /************************************
     * Locale Resolution
     *************************************/
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    /************************************
     * View Resolution
    *************************************/
    @Bean
    public ViewResolver getViewResolver(){
        return ViewResolverFactory.getViewResolver(ViewResolverType.JSP);
    }

    /************************************
     * Interceptors
     *************************************/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        //What is the name of the request parameter that specifies the locale?
        //It will be retrieved as a GET request parameter
        lci.setParamName("lang");
        return lci;
    }

    //Add any interceptors here
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

    /************************************
     * Default View Controllers
     *************************************/
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }

    /************************************
     * Resource Handlers
     *************************************/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/styles/**").addResourceLocations(RESOURCE_BASE + "styles/");
        registry.addResourceHandler("/scripts/**").addResourceLocations(RESOURCE_BASE + "scripts/");
        registry.addResourceHandler("/images/**").addResourceLocations(RESOURCE_BASE + "images/");
    }

    /************************************
     * Database Setup - Hibernate
     *************************************/
    @Bean
    public DataSource dataSourceProject1(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            //Basic Connection Info
            dataSource.setDriverClass(env.getProperty("jdbc_driver"));
            dataSource.setJdbcUrl(env.getProperty("jdbc_url"));
            dataSource.setUser(env.getProperty("db_user"));
            dataSource.setPassword(env.getProperty("db_password"));
            //Pool info
            dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("initial_pool_size")));
            dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("min_pool_size")));
            dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("max_pool_size")));
            dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("max_idle_time")));
        }catch (Exception e){
            console.error(e.getMessage());
        }
        return dataSource;
    }

    private Properties generateHibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return  properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSourceProject1());
        factory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        factory.setHibernateProperties(generateHibernateProperties());
        return factory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
}
