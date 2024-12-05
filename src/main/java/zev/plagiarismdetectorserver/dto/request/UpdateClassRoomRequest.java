package zev.plagiarismdetectorserver.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UpdateClassRoomRequest {

  private String name;

  private String description;
}
