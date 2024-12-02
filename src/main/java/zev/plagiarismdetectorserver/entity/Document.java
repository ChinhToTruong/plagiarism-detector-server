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
@Entity(name = "document")
@Table(name = "java_document_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private String url;
    private DocumentStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String reportId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClassRoom classRoom;
}
