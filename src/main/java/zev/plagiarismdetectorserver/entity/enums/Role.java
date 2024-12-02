package zev.plagiarismdetectorserver.entity.enums;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    @JsonProperty("student")
    STUDENT,
    @JsonProperty("teacher")
    TEACHER;
}
