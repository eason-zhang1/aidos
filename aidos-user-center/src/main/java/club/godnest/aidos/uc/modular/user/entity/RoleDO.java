package club.godnest.aidos.uc.modular.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import club.godnest.aidos.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author H.J.Zhang
 */
@Entity
@Table(name = "user_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDO extends AbstractEntity<Long> {

  private static final long serialVersionUID = 5088750607679795883L;

  @Column(name = "name", length = 64)
  private String name;

  @Column(name = "description", length = 128)
  private String description;
}

