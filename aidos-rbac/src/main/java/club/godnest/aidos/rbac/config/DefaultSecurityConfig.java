package club.godnest.aidos.rbac.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 默认的权限配置
 *
 * @author H.J.Zhang
 */
@Configuration
@ConditionalOnMissingBean(AbstractSecurityConfig.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class DefaultSecurityConfig extends AbstractSecurityConfig {

}
