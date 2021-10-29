package Core.Security.Helper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface IJwtProvider {
    Map<String, Object> getClimes(String jwtToken) throws IOException, ServletException;

}
