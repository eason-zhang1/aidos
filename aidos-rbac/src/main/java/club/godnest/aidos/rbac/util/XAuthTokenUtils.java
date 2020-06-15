package club.godnest.aidos.rbac.util;

/**
 * @author H.J.Zhang
 * @since 2020-06-15
 */
public class XAuthTokenUtils {

  private final static InheritableThreadLocal<String> xAuthTokenHolder = new InheritableThreadLocal<>();

  public static String getToken() {
	return xAuthTokenHolder.get();
  }

  public static void setToken(String token) {
	xAuthTokenHolder.set(token);
  }
}
