package zev.plagiarismdetectorserver.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.entity.User;

public interface UserService {

  void createUser(AddUserRequest request);

  void updateUserByUserId(UpdateProfileUserRequest request, String userId);

  void deleteUserByUserId(String userId);

  List<User> getUsers();

  User getUserById(String userId);

  ResponseData<?> searchUser(int pageNo, int pageSize, String sortBy, String classroom,
      String[] search);

  ResponseData<?> searchUserAdvance(Pageable pageable, String[] classroom, String[] user);

  User getUserByEmail(String email);
}
