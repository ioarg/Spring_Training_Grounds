package components.initializers.ViewResolution;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public final class ViewResolverFactory {
    private static final String VIEWS_PREFIX = "WEB-INF/Views/";
    private static final String JSP_SUFFIX = ".jsp";
    private static final String THYME_SUFFIX = ".html";

    public static ViewResolver getViewResolver(ViewResolverType type){
        ViewResolver resolver = null;
        switch(type){
            case JSP :
                resolver = getJSPResolver();
                break;
            case THYME :
                //to be updated
            default : break;
        }
        return resolver;
    }

    private static InternalResourceViewResolver getJSPResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(VIEWS_PREFIX);
        resolver.setSuffix(JSP_SUFFIX);
        return resolver;
    }
}
