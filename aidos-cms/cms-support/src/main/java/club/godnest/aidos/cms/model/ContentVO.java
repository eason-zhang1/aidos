package club.godnest.aidos.cms.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author H.J.Zhang
 * @since 2020-06-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentVO implements Serializable {

  private String content;

  private LocalDateTime publishedTime;

  private String author;

  private String origin;
}
