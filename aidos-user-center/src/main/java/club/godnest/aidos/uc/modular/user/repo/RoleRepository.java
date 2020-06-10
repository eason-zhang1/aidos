package club.godnest.aidos.uc.modular.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import club.godnest.aidos.uc.modular.user.entity.RoleDO;

/**
 * @author H.J.Zhang
 */
public interface RoleRepository extends JpaRepository<RoleDO, Long>, JpaSpecificationExecutor<RoleDO> {

}
