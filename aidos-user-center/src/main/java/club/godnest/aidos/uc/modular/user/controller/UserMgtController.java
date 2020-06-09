package club.godnest.aidos.uc.modular.user.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import club.godnest.aidos.uc.config.Constants;
import club.godnest.aidos.uc.modular.user.model.AuthenticationToken;
import club.godnest.aidos.uc.modular.user.service.AbstractUserAccountService;
import lombok.AllArgsConstructor;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
@RestController
@RequestMapping(Constants.USER_MGT_URL)
@AllArgsConstructor
public class UserMgtController {

  private AbstractUserAccountService userAccountService;

  @GetMapping("/me")
  public AuthenticationToken me(@AuthenticationPrincipal User user,
								@RequestHeader(name = "X-Auth-Token") String xAuthToken) {

	return new AuthenticationToken(user.getUsername(),
								   user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()),
								   xAuthToken);
  }
}
