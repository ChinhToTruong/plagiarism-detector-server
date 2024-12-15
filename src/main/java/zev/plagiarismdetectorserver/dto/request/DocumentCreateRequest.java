package zev.plagiarismdetectorserver.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class DocumentCreateRequest implements Serializable {

  private String title;

  private String description;

  @NotBlank(message = "file not be empty")
  private MultipartFile file;
}
