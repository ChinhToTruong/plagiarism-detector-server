package zev.plagiarismdetectorserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Assigment")
@Table(name = "java_assignment_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Assigment extends AbstractEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String title;
  private LocalDate startDate;
  private LocalDate endDate;
  private String documentId;
}
