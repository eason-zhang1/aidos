package club.godnest.aidos.uc.modular.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AuthenticationToken implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private String username;
  private String token;

  private Set<String> authorities = new HashSet<>();

  public AuthenticationToken(String username, Set<String> authorities, String token) {
	this.authorities = authorities;
	this.token = token;
	this.username = username;
  }

  public AuthenticationToken(String name, String username, Set<String> authorities, String token) {
	this(username, authorities, token);
	this.name = name;
  }


}
