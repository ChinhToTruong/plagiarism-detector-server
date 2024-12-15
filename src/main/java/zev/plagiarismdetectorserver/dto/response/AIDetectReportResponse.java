package zev.plagiarismdetectorserver.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import lombok.*;

@Setter
@Getter
public class AIDetectReportResponse implements Serializable {

  private boolean status;

  private int code;

  private JsonNode data;
}
