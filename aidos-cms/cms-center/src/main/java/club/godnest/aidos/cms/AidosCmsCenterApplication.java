package club.godnest.aidos.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import club.godnest.aidos.rbac.EnableRBAC;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@EnableRBAC
@EnableDiscoveryClient
@SpringBootApplication
public class AidosCmsCenterApplication {

  public static void main(String[] args) {
	SpringApplication.run(AidosCmsCenterApplication.class, args);
  }

}
