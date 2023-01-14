package zc.backend.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Component
@CrossOrigin("*")
public class EmailConfig {
    @Value("${spring.mail.host}")
    private  String host ;
    @Value("${spring.mail.port}")
    private  int  port  ;
    @Value("${spring.mail.username}")
    private  String username ;
    @Value("${spring.mail.password}")
    private  String  password;
}
