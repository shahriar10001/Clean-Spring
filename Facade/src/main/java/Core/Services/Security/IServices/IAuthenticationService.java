package Core.Services.Security.IServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IAuthenticationService {
    HttpServletRequest getTokenVerified(HttpServletRequest httpServletRequest) throws ServletException, IOException;
    public Object getClaims(String key);

}
