package zev.plagiarismdetectorserver.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIDetectRequest {
    @JsonProperty("text")
    @NotBlank(message = "van ban khong duoc de trong")
    private String text;
}
