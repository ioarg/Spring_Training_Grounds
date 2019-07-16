package components.initializers;

import components.initializers.ViewResolution.ViewResolverFactory;
import components.initializers.ViewResolution.ViewResolverType;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@EnableWebMvc
@Configuration
@ComponentScan({"components", "model/common"})
public class TrainingGroundsConfig implements WebMvcConfigurer {
    private static final String RESOURCE_BASE = "classpath:/static_resources/";

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
}
