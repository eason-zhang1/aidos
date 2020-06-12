package club.godnest.aidos.cms.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import club.godnest.aidos.cms.AidosCmsCenterApplication;

/**
 * @author H.J.Zhang
 *  https://docs.spring.io/spring-data/jpa/docs/2.2.7.RELEASE/reference/html/#jpa.java-config
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = AidosCmsCenterApplication.class)
@EntityScan(basePackageClasses = AidosCmsCenterApplication.class)
@EnableTransactionManagement
public class JpaConfig {

}

