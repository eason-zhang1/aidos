package club.godnest.aidos.stats.modular.cms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import club.godnest.aidos.cms.model.ContentVO;
import club.godnest.aidos.cms.service.IContentService;
import club.godnest.aidos.rbac.config.FeignConfig;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@FeignClient(value = "cms-center", configuration = FeignConfig.class,
	fallbackFactory = CmsContentService.HystrixActivityFallbackFactory.class)
public interface CmsContentService extends IContentService {

  @Slf4j
  @Service
  class HystrixActivityFallbackFactory implements FallbackFactory<IContentService> {

	private HystrixFallback fallback = new HystrixFallback();

	@Override
	public IContentService create(Throwable cause) {
	  log.warn("调用IActivityService出现了错误", cause);

	  return fallback;
	}
  }

  @Slf4j
  class HystrixFallback implements CmsContentService {

	@Override
	public List<ContentVO> topKNewContent() {
	  log.warn("调用了回调函数: topKNewContent");
	  return new ArrayList<>();
	}
  }
}
