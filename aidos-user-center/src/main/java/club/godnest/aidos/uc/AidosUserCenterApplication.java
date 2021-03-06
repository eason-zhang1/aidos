package club.godnest.aidos.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import club.godnest.aidos.rbac.EnableRBAC;

/**
 * @author H.J.Zhang
 */
// @SpringCloudApplication
@EnableRBAC
@SpringBootApplication
@EnableDiscoveryClient
public class AidosUserCenterApplication {

  public static void main(String[] args) {
	SpringApplication.run(AidosUserCenterApplication.class, args);
  }
}
