package zev.plagiarismdetectorserver.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.entity.Profile;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.exception.UserNotFound;
import zev.plagiarismdetectorserver.repository.ProfileRepository;
import zev.plagiarismdetectorserver.repository.UserRepository;
import zev.plagiarismdetectorserver.service.UserService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public User addUser(AddUserRequest user) {
        try {
            Optional<User> existedUser = userRepository.findByEmail(user.getEmail());
            if (existedUser.isEmpty()) {
                throw new RuntimeException("User already exists");
            }
            var saveUser = User.builder()
                    .role(user.getRole())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            return userRepository.save(saveUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public User updateUserById(String userId, UpdateProfileUserRequest request) {
        try {
            User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
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
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteUserById(String userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) throw new UserNotFound();
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile getProfileByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        return user.getProfile();
    }
}
