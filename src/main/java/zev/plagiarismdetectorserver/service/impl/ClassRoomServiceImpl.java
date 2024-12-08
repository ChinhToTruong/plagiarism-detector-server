package zev.plagiarismdetectorserver.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateClassRoomRequest;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.exception.ClassNotFound;
import zev.plagiarismdetectorserver.exception.ClassRoomExited;
import zev.plagiarismdetectorserver.exception.UserInClassRoom;
import zev.plagiarismdetectorserver.exception.UserNotFound;
import zev.plagiarismdetectorserver.exception.UserNotInClassRoom;
import zev.plagiarismdetectorserver.repository.ClassRoomRepository;
import zev.plagiarismdetectorserver.repository.UserRepository;
import zev.plagiarismdetectorserver.service.ClassRoomService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassRoomServiceImpl implements ClassRoomService {

  private final ClassRoomRepository classRoomRepository;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public String createNewClassRoom(ClassRoomCreateRequest request) {
    boolean isClassExited = classRoomRepository.findClassRoomByName(request.getName()).isPresent();

    if (isClassExited) {
      throw new ClassRoomExited();
    }

    try {
      ClassRoom classRoom = ClassRoom.builder()
          .name(request.getName())
          .description(request.getDescription())
          .build();
      var classRoomId = classRoomRepository.save(classRoom).getId();
      log.info("ClassRoom created: {}", classRoomId);
      return classRoomId;
    } catch (Exception e) {
      log.error("Creat class room failed");
      throw e;
    }
  }

  @Transactional
  @Override
  public void updateClassRoom(String classRoomId, UpdateClassRoomRequest request) {
    ClassRoom classRoom = findClassRoomById(classRoomId);

    if (request.getName() != null) {
      classRoom.setName(request.getName());
    }
    if (request.getDescription() != null) {
      classRoom.setDescription(request.getDescription());
    }

    try {
      classRoomRepository.save(classRoom);
      log.info("ClassRoom updated: {}", classRoom);
    } catch (Exception e) {
      log.error("Update class room failed");
      throw e;
    }
  }

  @Transactional
  @Override
  public void deleteClassRoom(String classRoomId) {
    ClassRoom classRoom = findClassRoomById(classRoomId);

    try {
      classRoomRepository.delete(classRoom);
      log.info("ClassRoom deleted: {}", classRoom);
    } catch (Exception e) {
      log.error("Delete class room failed");
      throw e;
    }
  }

  @Override
  public List<ClassRoom> getClassRoom() {
    return classRoomRepository.findAll();
  }

  @Override
  public ClassRoom getClassRoom(String classRoomId) {
    return findClassRoomById(classRoomId);
  }

  @Override
  @Transactional
  public void addUserToClassRoom(String classRoomId, String userId) {
    log.info("Add user to class room {}", classRoomId);

    User user = findUserById(userId);
    ClassRoom classRoom = findClassRoomById(classRoomId);

    boolean isClassExitedUser = classRoom.getUsers().contains(user);
    if (isClassExitedUser) {
      throw new UserInClassRoom();
    }

    try {
      user.getClassRooms().add(classRoom);
      userRepository.save(user);
      log.info("User {} added to classRoom {}", userId, classRoom);
    } catch (Exception e) {
      log.error("Add user to class room failed");
      throw e;
    }
  }

  @Override
  @Transactional
  public void removeUserFromClassRoom(String classRoomId, String userId) {
    log.info("Remove user from class room {}", classRoomId);

    User user = findUserById(userId);
    ClassRoom classRoom = findClassRoomById(classRoomId);

    boolean isClassExitedUser = classRoom.getUsers().contains(user);
    if (!isClassExitedUser) {
      throw new UserNotInClassRoom();
    }

    try {
      classRoom.getUsers().remove(user);
      log.info("User {} removed from classRoom {}", userId, classRoom);
    } catch (Exception e) {
      log.error("Remove user from class room failed");
      throw e;
    }
  }

  private ClassRoom findClassRoomById(String classRoomId) {
    log.info("Find class room by id {}", classRoomId);

    return classRoomRepository.findById(classRoomId).orElseThrow(ClassNotFound::new);
  }

  private User findUserById(String userId) {
    log.info("Find user by id {}", userId);

    return userRepository.findById(userId).orElseThrow(UserNotFound::new);
  }


}
