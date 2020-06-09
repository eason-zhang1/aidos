package club.godnest.aidos.uc.modular.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import club.godnest.aidos.uc.config.Constants;
import club.godnest.aidos.uc.modular.user.model.AuthenticationToken;
import club.godnest.aidos.uc.modular.user.service.AbstractUserAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.*;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
@Log4j2
@RestController
@RequestMapping(Constants.USER_PUB_URL)
@AllArgsConstructor
public class UserPubController {

  private AbstractUserAccountService userAccountService;

  @PostMapping("/login")
  public AuthenticationToken login(String username, String pwd, HttpServletRequest request) {
	log.debug("登录用户->{}, {}", username, pwd);
	Authentication authentication = userAccountService.login(username, pwd);
	SecurityContextHolder.getContext().setAuthentication(authentication);

	final HttpSession session = request.getSession(true);
	session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

	log.debug("登录完成");
	return new AuthenticationToken(authentication.getName(),
								   authentication.getAuthorities()
												 .stream()
												 .map(GrantedAuthority::getAuthority)
												 .collect(Collectors.toSet()),
								   session.getId());
  }

  @PostMapping("/test")
  public Map<String, String> test() throws InterruptedException {
	int i = new Random().nextInt(1000);
	log.info("=============>" + i);
	TimeUnit.MILLISECONDS.sleep(i);
	log.error("after sleeping=============>" + i);
	Map<String, String> map = new HashMap<>();
	map.put("test", "test");
	return map;
  }
}
