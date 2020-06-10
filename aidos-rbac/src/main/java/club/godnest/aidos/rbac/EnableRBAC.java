package club.godnest.aidos.rbac;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import club.godnest.aidos.rbac.config.DefaultSecurityConfig;
import club.godnest.aidos.rbac.config.RedisHttpSessionConfig;

/**
 * @author H.J.Zhang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({RedisHttpSessionConfig.class, DefaultSecurityConfig.class})
@Documented
public @interface EnableRBAC {

}
