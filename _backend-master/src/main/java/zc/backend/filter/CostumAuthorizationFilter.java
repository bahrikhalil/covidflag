package zc.backend.filter;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@CrossOrigin("*")
public class CostumAuthorizationFilter extends OncePerRequestFilter {



    @Value("${jwt.secret}")
    private   String  mySecret;
    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login") ||request.getServletPath().equals("/api/token/refrech") || request.getServletPath().equals("/authenticate") ){
            filterChain.doFilter(request,response);
        } else {
            String authorizationHeader =request.getHeader(AUTHORIZATION);
            if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length() );
                    Algorithm algorithm = Algorithm.HMAC256("testmytest".getBytes());
                    JWTVerifier  verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT=verifier.verify(token);
                    String username=decodedJWT.getSubject();
                    String roles= decodedJWT.getClaim("role").asString();
                    System.out.println(roles);
                    Collection<SimpleGrantedAuthority> authorities =new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("PUBLISHER"));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request,response);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    log.error(" Error logging in : {}",exception.getMessage());
                    response.setHeader("error",exception.getMessage());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    Map<String,String> error =new HashMap<>();
                    error.put("error_message",exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }else  {
                filterChain.doFilter(request,response);
            }
        }
    }


}