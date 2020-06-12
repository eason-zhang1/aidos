package club.godnest.aidos.cms.service;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import club.godnest.aidos.cms.config.Constants;
import club.godnest.aidos.cms.model.ContentVO;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
public interface IContentService {

  @GetMapping(Constants.CMS_MGT_URL + "/contents")
  List<ContentVO> topKNewContent();
}
