package zev.plagiarismdetectorserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.entity.Profile;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.repository.ProfileRepository;
import zev.plagiarismdetectorserver.repository.UserRepository;
import zev.plagiarismdetectorserver.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public User addUser(AddUserRequest user) {
        var saveUser = User.builder()
                .role(user.getRole())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        return userRepository.save(saveUser);
    }

    @Override
    public User updateUserById(String userId, UpdateProfileUserRequest request) {

        User user = userRepository.findById(userId).orElseThrow();
        Profile profile = Profile.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .phoneNumber(request.getPhoneNumber())
                .Address(request.getAddress())
                .build();
        profileRepository.save(profile);
        user.setProfile(profile);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Profile getProfileByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getProfile();
    }
}
