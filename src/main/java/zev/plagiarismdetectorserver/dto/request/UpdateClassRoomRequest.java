package zev.plagiarismdetectorserver.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UpdateClassRoomRequest implements Serializable {

  private String name;

  private String description;
}
