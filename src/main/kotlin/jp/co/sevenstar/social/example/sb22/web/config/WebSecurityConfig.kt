package jp.co.sevenstar.social.example.sb22.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests()
      .antMatchers("/", "/home").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .permitAll()
      .and()
      .logout()
      .deleteCookies("JSESSIONID")
      .permitAll()
  }

  @Bean
  public override fun userDetailsService(): UserDetailsService {
    val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    val user = User.withUsername("user")
      .password(encoder.encode("password"))
      .roles("USER")
      .build()
    return InMemoryUserDetailsManager(user)
  }
}
