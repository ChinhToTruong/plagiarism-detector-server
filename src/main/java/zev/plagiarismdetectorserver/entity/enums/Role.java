package zev.plagiarismdetectorserver.entity.enums;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Role {
    @JsonProperty("student")
    STUDENT("st_01"),
    @JsonProperty("teacher")
    TEACHER("t_01");

    private final String roleCode;
}
