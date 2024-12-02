package zev.plagiarismdetectorserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import zev.plagiarismdetectorserver.entity.enums.DocumentStatus;
import zev.plagiarismdetectorserver.entity.enums.Gender;
import zev.plagiarismdetectorserver.entity.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "document_status",nullable = false)
    private DocumentStatus status;

    @Column(name = "report_id", nullable = false, updatable = false)
    private String reportId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClassRoom classRoom;
}
