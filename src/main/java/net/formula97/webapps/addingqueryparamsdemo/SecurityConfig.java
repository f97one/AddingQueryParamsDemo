package net.formula97.webapps.addingqueryparamsdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.formula97.webapps.addingqueryparamsdemo.handlers.AuthFailure;
import net.formula97.webapps.addingqueryparamsdemo.handlers.AuthSuccess;
import net.formula97.webapps.addingqueryparamsdemo.handlers.LogoutPostProcess;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("hoge").password("HOGE").roles("USER");
    }

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/js/**");
    }

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**", "/login**").permitAll()
            .anyRequest().authenticated();

        // Specifies Custom Login/Logout Handlers instead of Spring default implementation
        http.formLogin()
            .loginProcessingUrl("/login")
            .loginPage("/login")
            .successHandler(new AuthSuccess())
            .failureHandler(new AuthFailure())
            .permitAll();
        
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .addLogoutHandler(new LogoutPostProcess())
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    
}
