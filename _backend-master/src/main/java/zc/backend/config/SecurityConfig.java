

package zc.backend.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import zc.backend.filter.CostumAuthorizationFilter;
import zc.backend.filter.CustomAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin("*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  final UserDetailsService userDetailsService  ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder) ;
    }




  /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter authenticationFilter=new CustomAuthenticationFilter(authenticationManagerBean());
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user").hasRole("PUBLISHER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user").hasAuthority("PUBLISHER");
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new CostumAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(authenticationFilter);
    }
*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(  "/api/feedback","/api/user/role/test ","/api/user/addtoUser","/api/event/eventing/*","/api/event/all","/api/event/ratings/*","/api/user/all");

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return  super.authenticationManagerBean();

    }


}