package zev.plagiarismdetectorserver.dto.request;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class UpdateDocumentRequest implements Serializable {

  private String title;

  private String description;
}
