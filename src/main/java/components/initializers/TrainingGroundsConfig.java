package components.initializers;

import components.initializers.ViewResolution.ViewResolverFactory;
import components.initializers.ViewResolution.ViewResolverType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan({"components", "model/common"})
public class TrainingGroundsConfig implements WebMvcConfigurer {
    private static final String RESOURCE_BASE = "classpath:/static_resources/";

    /************************************
     * View Resolution
    *************************************/
    @Bean
    public ViewResolver getViewResolver(){
        return ViewResolverFactory.getViewResolver(ViewResolverType.JSP);
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
