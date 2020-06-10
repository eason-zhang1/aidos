package club.godnest.adios.gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author H.J.Zhang
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AidosGatewayCenterApplication {

  public static void main(String[] args) {
	SpringApplication.run(AidosGatewayCenterApplication.class, args);
  }
}
