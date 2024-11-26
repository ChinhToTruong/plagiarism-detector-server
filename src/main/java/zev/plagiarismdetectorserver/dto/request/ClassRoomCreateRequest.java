package zev.plagiarismdetectorserver.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomCreateRequest {
    @NotBlank(message = "ten lop hoc khong duoc de trong")
    private String name;
    @NotBlank(message = "mo ta lop hoc khong duoc de trong")
    private String description;
}
