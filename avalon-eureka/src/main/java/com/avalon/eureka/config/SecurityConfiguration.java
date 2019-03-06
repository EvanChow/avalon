//package com.avalon.eureka.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    /**
//     * 配置忽略安全管理的路径，一般为资源文件例如css，js,IMG等
//     *
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/webjars/**", "/resources/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);  //注意！注意！注意！这个必须注释或者删除掉否则以下配置回受到默认您spring security规则影响
//        http
//                .authorizeRequests()
//                .antMatchers("/account/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/account/login.html")//自定义登录页面的地址
//                .loginProcessingUrl("/account/login")//自定义登录表单提交地址（默认:/login）
//                .passwordParameter("pwd")//自定义登录用密码的表单名称（默认password）
//                .usernameParameter("username")//自定义登录用户名的表单名称（默认username）
//                .defaultSuccessUrl("/admin")//自定义登录成功后跳转的页面
//                .failureForwardUrl("/account/login.html?error")//自定义登录失败跳转的页面
//                .and()
//                .logout()
//                .invalidateHttpSession(true)//登出时候清除sessionion
//                .clearAuthentication(true)//登出时候清除认证信息
//                .logoutUrl("/account/logout")//登出表单的地址
//                .logoutSuccessUrl("/account/login.html")//登出成功后跳转页面
//                .and()
////                    .csrf().disable()//配置是否启用csrf,默认启用
//                .cors().disable().headers().frameOptions().sameOrigin();//解决iframe无法访问
//    }
//}
