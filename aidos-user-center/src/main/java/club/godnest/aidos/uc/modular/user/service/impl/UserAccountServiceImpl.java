package club.godnest.aidos.uc.modular.user.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import club.godnest.aidos.uc.modular.user.repo.UserAccountRepository;
import club.godnest.aidos.uc.modular.user.service.AbstractUserAccountService;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
@Service
public class UserAccountServiceImpl extends AbstractUserAccountService {

  public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
								AuthenticationManager authenticationManager) {
	super(userAccountRepository, authenticationManager);
  }
}
