package club.godnest.aidos.uc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import club.godnest.aidos.rbac.config.AbstractSecurityConfig;
import club.godnest.aidos.rbac.config.DefaultSecurityConfig;
import club.godnest.aidos.uc.modular.user.service.impl.UserAccountServiceImpl;

/**
 * @author H.J.Zhang
 */
@Configuration
@EnableWebSecurity
@AutoConfigureBefore(DefaultSecurityConfig.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class UserSecurityConfig extends AbstractSecurityConfig {

  @Autowired
  private UserAccountServiceImpl userAccountService;

  @Override
  public UserDetailsService userDetailsService() {
	return username -> userAccountService.loadUserByUsername(username);
  }
}
