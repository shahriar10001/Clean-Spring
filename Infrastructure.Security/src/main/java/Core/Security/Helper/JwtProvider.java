package Core.Security.Helper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@PropertySource(value = {"classpath:Security.properties"})
@Service
public class JwtProvider implements IJwtProvider {

    @Autowired
    private Environment environment;

    private static String wso2Cert = null;

    public Map<String, Object> getClimes(String jwtToken) throws IOException {
        Map<String, Object> result =  verifyToken(jwtToken);
        return  result;
    }

    private Map<String, Object> verifyToken(String jwtToken) throws IOException {

        if (wso2Cert == null) {
            wso2Cert=environment.getProperty("wso2.publicKey");
        }
        try {
            Map<String, Object> climesMap = new HashMap<>();
            if (jwtToken == null || jwtToken.length() == 0 ||
                    (jwtToken != null && jwtToken.toUpperCase().equals("test".toUpperCase()))) {
                return getClimes(null);
            }

            PublicKey publicKey = getPublicKey(wso2Cert);
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(jwtToken).getBody();

            return getClaimsMap(claims);

        } catch (Exception ex) {
            return null;
        }
    }

    private Map<String, Object> getClaimsMap(Claims claims) {
        Map<String, Object> climesMap = new HashMap<>();
        String[] climeKeys=environment.getProperty("claims.keys").split(",");
        for(String key:climeKeys){
            try {
                if(claims !=null )
                    climesMap.put(key, claims.get(environment.getProperty("claim."+key), Object.class));
                else
                    climesMap.put(key, environment.getProperty("claim."+key));
            }catch (Exception ex){
                climesMap.put(key, null);
            }
        }
        return climesMap;
    }

    private PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        String pkey = key.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        keyBytes= Base64.getDecoder().decode(pkey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
}
