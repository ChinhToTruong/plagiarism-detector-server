package zev.plagiarismdetectorserver.dto.request;


import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.*;

@Getter
public class ClassRoomCreateRequest implements Serializable {

  @NotBlank(message = "ten lop hoc khong duoc de trong")
  private String name;

  @NotBlank(message = "mo ta lop hoc khong duoc de trong")
  private String description;
}
