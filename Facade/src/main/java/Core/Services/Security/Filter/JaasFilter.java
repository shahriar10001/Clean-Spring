package Core.Services.Security.Filter;
import Core.Services.Security.IServices.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component("JaasFilter")
@PropertySource(value = {"classpath:Security.properties"})
public class JaasFilter implements Filter {



    @Autowired
    private Environment environment;

    @Autowired
    private IAuthenticationService authenticationService;

    public void init(FilterConfig filterConfig) throws ServletException {
       //TODO log --- init
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= authenticationService.getTokenVerified((HttpServletRequest) servletRequest);
        if(request==null){
            getUnauthorized((HttpServletResponse) servletResponse);
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }

    public void destroy() {
     //TODO log --- destroy
    }

    private void getUnauthorized(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("UNAUTHORIZED", environment.getProperty("UNAUTHORIZED"));
        httpServletResponse.getWriter().write(environment.getProperty("UNAUTHORIZED"));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
