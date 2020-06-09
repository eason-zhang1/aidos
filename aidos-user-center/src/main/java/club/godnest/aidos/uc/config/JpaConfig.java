package club.godnest.aidos.uc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import club.godnest.aidos.uc.AidosUserCenterApplication;

/**
 * @author H.J.Zhang
 * @since 2020-05-08 https://docs.spring.io/spring-data/jpa/docs/2.2.7.RELEASE/reference/html/#jpa.java-config
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = AidosUserCenterApplication.class)
@EntityScan(basePackageClasses = AidosUserCenterApplication.class)
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class JpaConfig {

}

