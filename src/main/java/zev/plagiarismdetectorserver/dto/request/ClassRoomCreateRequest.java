package zev.plagiarismdetectorserver.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
public class ClassRoomCreateRequest {

  @NotBlank(message = "ten lop hoc khong duoc de trong")
  private String name;

  @NotBlank(message = "mo ta lop hoc khong duoc de trong")
  private String description;
}
