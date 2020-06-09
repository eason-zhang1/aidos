package club.godnest.aidos.common.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author H.J.Zhang
 * @since 2020-05-08
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity<PK extends Serializable> implements Serializable {

  private static final long serialVersionUID = -4676121424321014927L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private PK id;

  @Column(name = "is_active")
  private Boolean active = Boolean.TRUE;
}
