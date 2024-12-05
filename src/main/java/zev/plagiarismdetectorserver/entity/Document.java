package zev.plagiarismdetectorserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zev.plagiarismdetectorserver.entity.enums.DocumentStatus;

@Getter
@Setter
@Entity(name = "Document")
@Table(name = "java_document_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false)
  private String id;

  @Column(name = "title", length = 100, nullable = false, unique = true)
  private String title;

  @Column(name = "description", length = 100)
  private String description;

  @Column(name = "url", length = 150, unique = true)
  private String url;

  @Column(name = "document_status", nullable = false)
  private DocumentStatus status;

  @Column(name = "report_id", nullable = false, updatable = false)
  private String reportId;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private ClassRoom classRoom;
}
