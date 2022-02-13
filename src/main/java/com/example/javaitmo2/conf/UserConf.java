package com.example.javaitmo2.conf;

import com.example.javaitmo2.beans.UserSessionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConf {

    @Bean(initMethod = "doMyInit", destroyMethod = "doMyDestroy")
    public UserSessionBean userConfBean() {
        return new UserSessionBean();
    }
}
