package components.initializers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class TrainingGroundsApp implements WebApplicationInitializer {
    private static Logger console = LoggerFactory.getLogger(TrainingGroundsApp.class);
    @Override
    public void onStartup(ServletContext servlet_context){
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(TrainingGroundsConfig.class);

        DispatcherServlet dispatcher = new DispatcherServlet(appContext);
        ServletRegistration.Dynamic dispatcherConf = servlet_context.addServlet("Dispatcher Servlet", dispatcher);
        dispatcherConf.setLoadOnStartup(1);
        dispatcherConf.addMapping("/");
        dispatcherConf.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        console.info("Application Started..");
    }
}
