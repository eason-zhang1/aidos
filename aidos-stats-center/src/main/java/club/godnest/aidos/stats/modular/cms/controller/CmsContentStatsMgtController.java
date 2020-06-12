package club.godnest.aidos.stats.modular.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import club.godnest.aidos.cms.model.ContentVO;
import club.godnest.aidos.stats.config.Constants;
import club.godnest.aidos.stats.modular.cms.service.CmsContentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static club.godnest.aidos.rbac.config.Constants.*;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@Slf4j
@RestController
@RequestMapping(Constants.STATS_MGT_URL)
@AllArgsConstructor
public class CmsContentStatsMgtController {

  private CmsContentService contentService;

  @GetMapping("/contents")
  public List<ContentVO> contentStats(HttpServletRequest request) {
	System.getProperties().setProperty(X_AUTH_TOKEN, request.getHeader(X_AUTH_TOKEN));
	return contentService.topKNewContent();
  }

}
