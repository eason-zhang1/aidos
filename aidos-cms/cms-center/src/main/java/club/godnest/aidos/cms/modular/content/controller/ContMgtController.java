package club.godnest.aidos.cms.modular.content.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import club.godnest.aidos.cms.config.Constants;
import club.godnest.aidos.cms.model.ContentVO;
import club.godnest.aidos.cms.modular.content.service.ContentService;
import lombok.AllArgsConstructor;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@RestController
@RequestMapping(Constants.CMS_MGT_URL)
@AllArgsConstructor
public class ContMgtController {

  private ContentService contentService;

  @GetMapping("/contents")
  public List<ContentVO> contents() {
	return contentService.topKNewContent();
  }
}
