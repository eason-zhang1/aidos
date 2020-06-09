package club.godnest.aidos.uc.modular.user.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import club.godnest.aidos.common.jpa.AbstractAuditEntity;
import lombok.Data;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
@Entity
@Table(name = "user_account", uniqueConstraints = {
	@UniqueConstraint(name = "unique_username", columnNames = "username")
})
@Data
public class UserAccountDO extends AbstractAuditEntity<Long> {

  private static final long serialVersionUID = -3651406368209012319L;

  @Column(name = "name", length = 64)
  private String name;

  @Column(name = "username", length = 64)
  private String username;

  @Column(name = "password", length = 64)
  private String password;

  @Column(name = "mobile", length = 16)
  private String mobile;

  @Column(name = "email", length = 64)
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
	  name = "user_account_role",
	  joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	  inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<RoleDO> roles = new HashSet<>();

  @Transient
  private Collection<? extends GrantedAuthority> authorities;
}
