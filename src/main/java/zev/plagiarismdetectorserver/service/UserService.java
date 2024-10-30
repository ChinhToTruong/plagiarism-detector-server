package zev.plagiarismdetectorserver.service;

import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.entity.Profile;
import zev.plagiarismdetectorserver.entity.User;

public interface UserService {
    User addUser(AddUserRequest user);
    User updateUserById(String userId, UpdateProfileUserRequest request);
    void deleteUserById(String userId);
    Profile getProfileByUserId(String userId);
}
