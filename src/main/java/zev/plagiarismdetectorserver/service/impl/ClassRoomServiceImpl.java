package zev.plagiarismdetectorserver.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.exception.ClassNotFound;
import zev.plagiarismdetectorserver.exception.ClassRoomExited;
import zev.plagiarismdetectorserver.exception.UserNotFound;
import zev.plagiarismdetectorserver.repository.ClassRoomRepository;
import zev.plagiarismdetectorserver.repository.UserRepository;
import zev.plagiarismdetectorserver.service.ClassRoomService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassRoomServiceImpl implements ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createNewClassRoom(ClassRoomCreateRequest request) {
        boolean isClassExited = classRoomRepository.findClassRoomByName(request.getName()).isPresent();

        if (isClassExited) {
            throw new ClassRoomExited();
        }
        try {
            ClassRoom classRoom = ClassRoom.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .build();

            classRoomRepository.save(classRoom);
            log.info("ClassRoom created: {}", classRoom);
        }
        catch (Exception e) {
            log.error("Creat class room failed");
            throw e;
        }
    }

    @Override
    public void updateClassRoom(ClassRoom classRoom) {

    }

    @Override
    public void deleteClassRoom(String classRoomId) {

    }

    @Override
    public List<ClassRoom> getClassRoom() {
        return classRoomRepository.findAll();
    }

    @Override
    public ClassRoom getClassRoom(String classRoomId) {
        return null;
    }

    @Transactional
    @Override
    public void addUserToClassRoom(String classRoomId, String userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        ClassRoom classRoom = classRoomRepository.findById(classRoomId).orElseThrow(ClassNotFound::new);
        try{
            user.getClassRooms().add(classRoom);
            userRepository.save(user);
            log.info("User {} added to classRoom {}", userId, classRoom);
        }
        catch (Exception e) {
            log.error("Add user to class room failed");
            throw e;
        }
    }

    @Override
    public void removeUserFromClassRoom(String classRoomId, String userId) {

    }
}
