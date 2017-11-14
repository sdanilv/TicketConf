//package ua.my.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//public class SecurityConfig {
//
//    @Configuration
//    @ComponentScan("ua.my")
//    @EnableWebSecurity
//    @EnableGlobalMethodSecurity(prePostEnabled=true)
//    public class SecurityConfig extends WebSecurityConfigurerAdapter {
//        @Autowired
//        AuthenticationService authenticationService;
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            System.out.println("asdasd");
//            CharacterEncodingFilter filter = new CharacterEncodingFilter();
//            filter.setEncoding("UTF-8");
//            filter.setForceEncoding(true);
//            // отключена защита csrf на время тестов
//            http.csrf().disable().addFilterBefore(filter,CsrfFilter.class);
//            http.authorizeRequests().antMatchers("/account/**").hasRole("USER")
//                    .antMatchers("/user/**").hasRole("ADMIN")
//                    .and().formLogin();
//        }
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(authenticationService);
//        }
//    }
//}
