package zev.plagiarismdetectorserver.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Setter
public class AIDetectReportResponse {

  private boolean status;

  private int code;

  private JsonNode data;
}
