package club.godnest.aidos.cms.service;

import java.util.List;

import club.godnest.aidos.cms.model.ContentVO;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
public interface IContentService {

  List<ContentVO> topKNewContent();
}
