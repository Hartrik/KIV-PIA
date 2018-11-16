package cz.hartrik.pia.config

import cz.hartrik.pia.dto.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * @author Patrik Harag
 * @version 2018-11-16
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder()
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                    .antMatchers("/service/*").hasAuthority(User.ROLE_ADMIN)
                    .antMatchers("/ib/*").hasAuthority(User.ROLE_CUSTOMER)
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/user/login")
                    .successForwardUrl("/")
                    .failureForwardUrl("/")
                    .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                    .and()
    }

}
