package Core.Security.Services;

import Core.Security.Helper.IJwtProvider;
import Core.Services.Security.IServices.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@Service
@PropertySource(value = {"classpath:Security.properties"})
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private Environment environment;

    @Autowired
    IJwtProvider jwtProvider;

    @Autowired
    HttpServletRequest webContext;


    public HttpServletRequest getTokenVerified(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        Map<String, Object> result = jwtProvider.getClimes(getToken(httpServletRequest));
        if (result != null) {
            httpServletRequest.setAttribute("climes", result);
            return httpServletRequest;
        } else {
            return null;
        }
    }

    public Object getClaims(String key){
        Map<String, Object> climes = (Map<String, Object>) webContext.getAttribute("climes");
        return climes.get(key);
    }



    private String getToken(HttpServletRequest httpServletRequest) {
        String host=httpServletRequest.getHeader(environment.getProperty("host.header"));
        if (isValidHost(host)) {
            return "TEST";
        } else {
            String headerValue = "";
            Enumeration<String> headers = httpServletRequest.getHeaders(environment.getProperty("wso2.token.header"));
            while (headers.hasMoreElements()) {
                headerValue = headers.nextElement();
            }
            return headerValue;
        }
    }

    private boolean isValidHost(String host) {
        String[] validHosts = environment.getProperty("host.valid.ip").split(",");
        return Arrays.stream(validHosts).anyMatch(c -> host.toUpperCase()
                .contains(c.trim().toUpperCase()));

    }

}
