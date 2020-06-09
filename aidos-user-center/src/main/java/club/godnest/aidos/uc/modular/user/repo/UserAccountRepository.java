package club.godnest.aidos.uc.modular.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

import club.godnest.aidos.uc.modular.user.entity.UserAccountDO;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
public interface UserAccountRepository extends JpaRepository<UserAccountDO, Long>, JpaSpecificationExecutor<UserAccountDO> {

  Optional<UserAccountDO> findByUsernameAndActiveTrue(String username);
}
