package zev.plagiarismdetectorserver.dto.request;

import lombok.Data;
import zev.plagiarismdetectorserver.entity.enums.Role;

@Data
public class AddUserRequest {
    private String email;
    private String password;
    private Role role;
}
