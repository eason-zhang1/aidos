package club.godnest.aidos.uc.modular.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

import club.godnest.aidos.common.exception.BusinessException;
import club.godnest.aidos.uc.modular.user.entity.UserAccountDO;
import club.godnest.aidos.uc.modular.user.repo.UserAccountRepository;
import lombok.AllArgsConstructor;

/**
 * @author H.J.Zhang
 */
@AllArgsConstructor
public abstract class AbstractUserAccountService implements UserDetailsService {

  private UserAccountRepository userAccountRepository;
  private AuthenticationManager authenticationManager;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	UserAccountDO ua = userAccountRepository.findByUsernameAndActiveTrue(username)
											.orElseThrow(() -> new BusinessException("用户不存在"));

	Set<GrantedAuthority> authorities = new HashSet<>();
	ua.getRoles().forEach(roleDO -> authorities.add(new SimpleGrantedAuthority("ROLE_" + roleDO.getName())));
	ua.setAuthorities(authorities);
	return User.builder().username(username).password(ua.getPassword()).authorities(authorities).build();
  }

  public Authentication login(String username, String pwd) {
	return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pwd));
  }
}
