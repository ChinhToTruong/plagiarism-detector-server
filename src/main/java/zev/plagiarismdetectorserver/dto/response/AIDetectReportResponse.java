package zev.plagiarismdetectorserver.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIDetectReportResponse {

    private boolean status;
    private int code;
    private JsonNode data;
}
