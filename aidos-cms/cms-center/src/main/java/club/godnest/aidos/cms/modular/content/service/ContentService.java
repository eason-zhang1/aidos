package club.godnest.aidos.cms.modular.content.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import club.godnest.aidos.cms.model.ContentVO;
import club.godnest.aidos.cms.modular.content.repo.ContentRepository;
import club.godnest.aidos.cms.service.IContentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ContentService implements IContentService {

  private ContentRepository contentRepository;

  @Override
  public List<ContentVO> topKNewContent() {
	return contentRepository.findTop10ByActiveTrueOrderByPublishedTimeDesc()
							.stream()
							.map(c -> new ContentVO(c.getContent(), c.getPublishedTime(), c.getAuthor(), c.getOrigin()))
							.collect(Collectors.toList());
  }
}
