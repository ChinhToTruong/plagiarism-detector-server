package zev.plagiarismdetectorserver.service.impl;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.entity.enums.Gender;
import zev.plagiarismdetectorserver.entity.enums.Role;
import zev.plagiarismdetectorserver.exception.UserAlreadyExisted;
import zev.plagiarismdetectorserver.exception.UserNotFound;
import zev.plagiarismdetectorserver.repository.SearchRepository;
import zev.plagiarismdetectorserver.repository.UserRepository;
import zev.plagiarismdetectorserver.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final SearchRepository searchRepository;

  @Override
  @Transactional
  public void createUser(AddUserRequest request) {
    log.info("Creating user: {}", request);

    boolean isExistedUser = userRepository.findByEmail(request.getEmail()).isPresent();

    if (isExistedUser) {
      throw new UserAlreadyExisted();
    }

    try {
      User user = User.builder()
          .email(request.getEmail())
          .password(request.getPassword())
          .role(Role.valueOf(request.getRole()))
          .isActive(true)
          .build();

      userRepository.save(user);

      log.info("Created user: {}", user);
      // send email verify user with kafka
    } catch (Exception e) {
      log.error("Create failed user: {}", request);
      throw e;
    }
  }

  @Transactional
  @Override
  public void updateUserByUserId(UpdateProfileUserRequest request, String userId) {

    log.info("Updating user: {}", request);

    User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

    try {
      user.setFirstName(request.getFirstName());
      user.setLastName(request.getLastName());
      user.setDateOfBirth(request.getDateOfBirth());
      user.setGender(Gender.valueOf(request.getGender()));
      user.setAvatarUrl(request.getAvatarUrl());
      user.setAddress(request.getAddress());
      user.setPhone(request.getPhoneNumber());

      userRepository.save(user);
      log.info("Updated user: {}", user);
    } catch (Exception e) {
      log.error("Update failed user: {}", request);
      throw e;
    }
  }

  @Transactional
  @Override
  public void deleteUserByUserId(String userId) {
    log.info("Deleting user: {}", userId);

    User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

    try {
      userRepository.delete(user);
      log.info("Deleted user: {}", user);
    } catch (Exception e) {
      log.error("Delete failed user: {}", userId);
      throw e;
    }
  }

  @Override
  public List<User> getUsers() {
    log.info("Retrieving all users");

    List<User> list = new ArrayList<>();
    List<User> users = userRepository.findAll();

    users.forEach(user -> {
      if (user.isActive()) {
        list.add(user);
      }
    });

    return list;
  }

  @Override
  public User getUserById(String userId) {
    log.info("Retrieving user: {}", userId);

    User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

    return user.isActive() ? user : null;
  }

  @Override
  public ResponseData<?> searchUser(int pageNo, int pageSize, String sortBy, String classroom,
      String[] search) {
    log.info("Retrieving users: {}", (Object) search);

    return new ResponseData<>("thanh cong",
        searchRepository.searchAdvance(pageNo, pageSize, sortBy, classroom, search));
  }

  @Override
  public ResponseData<?> searchUserAdvance(Pageable pageable, String[] classroom, String[] user) {
    log.info("Retrieving users: {}", (Object) user);

    return new ResponseData<>("thanh cong",
        searchRepository.searchSpecification(pageable, classroom, user));
  }

  @Override
  public User getUserByEmail(String email) {
    log.info("Retrieving user: {}", email);

    return userRepository.findByEmail(email).orElseThrow(UserNotFound::new);
  }
}
