package club.godnest.aidos.uc.config.support;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import club.godnest.aidos.uc.modular.user.entity.UserAccountDO;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
	return Optional.ofNullable(SecurityContextHolder.getContext())
				   .map(SecurityContext::getAuthentication)
				   .filter(Authentication::isAuthenticated)
				   .map(Authentication::getPrincipal)
				   .map(UserAccountDO.class::cast).map(UserAccountDO::getId);
  }
}
