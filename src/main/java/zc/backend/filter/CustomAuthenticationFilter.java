package zc.backend.filter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@PropertySource(value = {"classpath:application.properties"})
@CrossOrigin("*")
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.secret}")
    private   String  mySecret;


    private final AuthenticationManager authenticationManager ;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }



    @Override

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username= request.getParameter("username");
        String password  =request.getParameter("password");
        log.info("Username is: {}",username );
        log.info("Password is : {}" ,password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(username,password) ;
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override


    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication ) throws IOException, ServletException {
        User user =(User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("testmytest".getBytes());
        String acces_token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String access = Jwts.builder().setSubject(user.getUsername())
                .setIssuer(request.getRequestURL().
                        toString()).setExpiration(new Date(System.currentTimeMillis()+ 10*60*1000))
                .signWith(SignatureAlgorithm.HS256,"testmytest").compact();
       String refrech_token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 20*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
       // response.setHeader("acces_token","refrech_token");
        Map<String,String> tokens =new HashMap<>();
        tokens.put("acces_token",acces_token);
        tokens.put("refrech_token",refrech_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),acces_token);
    }


}