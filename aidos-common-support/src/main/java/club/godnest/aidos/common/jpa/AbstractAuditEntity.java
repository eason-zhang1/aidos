package club.godnest.aidos.common.jpa;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author H.J.Zhang
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAuditEntity<PK extends Serializable> extends AbstractEntity<PK> {

  private static final long serialVersionUID = 1916481336869600077L;

  @CreatedBy
  private Long createdBy;

  @LastModifiedBy
  private Long modifiedBy;

  @CreatedDate
  private LocalDateTime createdTime;

  @LastModifiedDate
  private LocalDateTime modifiedTime;
}
