package club.godnest.aidos.cms.modular.content.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import club.godnest.aidos.cms.modular.content.entity.ContentDO;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
public interface ContentRepository extends JpaRepository<ContentDO, Long>, JpaSpecificationExecutor<ContentDO> {

  List<ContentDO> findTop10ByActiveTrueOrderByPublishedTimeDesc();
}
