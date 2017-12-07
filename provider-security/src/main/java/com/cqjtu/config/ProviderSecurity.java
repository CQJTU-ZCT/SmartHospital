package com.cqjtu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author zjhfyq
 * @Desc 为所有的provider提供被访问的安全认证,
 * 也就是consumer访问provider的时候，需要进行身份认证
 * @date 2017/12/5.
 */

@Configuration
@EnableWebSecurity
public class ProviderSecurity  extends WebSecurityConfigurerAdapter{


    @Autowired
    public  void configGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.inMemoryAuthentication()
                .withUser("test").password("test").roles("test")
                .and()
                .withUser("userConsumer").password("userConsumer").roles("userConsumer");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表示所有的访问服务必须经过认证后才能进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        //所有的rest服务尽可能把session设置成无状态的，以提升服务器性能
        //有状态的session会给服务器内存带来压力，严重可能导致OOM
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
