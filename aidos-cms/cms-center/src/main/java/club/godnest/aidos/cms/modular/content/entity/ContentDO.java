package club.godnest.aidos.cms.modular.content.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import club.godnest.aidos.common.jpa.AbstractEntity;
import lombok.Data;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@Entity
@Table(name = "cms_content")
@Data
public class ContentDO extends AbstractEntity<Long> {

  private static final long serialVersionUID = -1L;

  @Column(name = "title", length = 100)
  private String title;

  private String content;

  private LocalDateTime publishedTime;

  @Column(name = "author", length = 20)
  private String author;

  @Column(name = "origin", length = 100)
  private String origin;
}
