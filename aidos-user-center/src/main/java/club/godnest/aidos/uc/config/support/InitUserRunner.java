package club.godnest.aidos.uc.config.support;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import club.godnest.aidos.uc.modular.user.entity.RoleDO;
import club.godnest.aidos.uc.modular.user.entity.UserAccountDO;
import club.godnest.aidos.uc.modular.user.repo.RoleRepository;
import club.godnest.aidos.uc.modular.user.repo.UserAccountRepository;

/**
 * @author H.J.Zhang
 */
@Component
public class InitUserRunner implements CommandLineRunner {

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserAccountRepository userAccountRepository;
  @Autowired
  private RoleRepository roleRepository;

  private final static String INIT_USER = "init_user";

  @Override
  public void run(String... args) throws Exception {
    if(ArrayUtils.isEmpty(args)) {
      return;
	}
    if(ArrayUtils.contains(args, INIT_USER)) {
	  saveInitData();
	}
  }

  @Transactional
  public void saveInitData() {
	List<RoleDO> roles = roleRepository.saveAll(Arrays.asList(new RoleDO("ADMIN", "管理员"),
															  new RoleDO("USER", "用户")));

	UserAccountDO user = new UserAccountDO();
	user.setEmail("739663407@qq.com");
	user.setName("eason zhang");
	user.setUsername("hjzhang");
	user.setPassword(passwordEncoder.encode("123456"));
	user.setRoles(new HashSet<>(roles));
	userAccountRepository.save(user);
  }
}
