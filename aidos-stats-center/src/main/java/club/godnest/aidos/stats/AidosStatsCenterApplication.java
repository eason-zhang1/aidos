package club.godnest.aidos.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import club.godnest.aidos.rbac.EnableRBAC;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@EnableRBAC
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AidosStatsCenterApplication {

  public static void main(String[] args) {
	SpringApplication.run(AidosStatsCenterApplication.class, args);
  }
}
