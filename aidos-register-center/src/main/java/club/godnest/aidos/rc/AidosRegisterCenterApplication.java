package club.godnest.aidos.rc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author H.J.Zhang
 * @since 2020-06-07
 */
@EnableEurekaServer
@SpringBootApplication
public class AidosRegisterCenterApplication {

  public static void main(String[] args) {
	SpringApplication.run(AidosRegisterCenterApplication.class, args);
  }
}
