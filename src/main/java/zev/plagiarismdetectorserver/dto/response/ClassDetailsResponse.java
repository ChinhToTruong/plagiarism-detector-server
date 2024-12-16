package zev.plagiarismdetectorserver.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Setter;
import zev.plagiarismdetectorserver.entity.Document;
import zev.plagiarismdetectorserver.entity.User;

@Setter
@Builder
public class ClassDetailsResponse {

  private String className;

  private String description;

  private User teacherName;

  List<Document> documents;
}
