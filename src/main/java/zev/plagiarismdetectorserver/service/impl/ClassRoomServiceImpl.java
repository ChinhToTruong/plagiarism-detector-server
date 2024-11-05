package zev.plagiarismdetectorserver.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.Profile;
import zev.plagiarismdetectorserver.repository.ClassRoomRepository;
import zev.plagiarismdetectorserver.repository.ProfileRepository;
import zev.plagiarismdetectorserver.service.ClassRoomService;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public ClassRoom createClassRoom(ClassRoomCreateRequest request) {
        try {
            Optional<ClassRoom> existClassRoom = classRoomRepository.findClassRoomByName(request.getName());
            if (existClassRoom.isPresent()) {
                throw new RuntimeException("class room already exists");
            }
            ClassRoom newClassRoom = ClassRoom.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .build();
            return classRoomRepository.save(newClassRoom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * userid la profileId
     * */
    @Transactional
    @Override
    public ClassRoom addUserToClassRoomByClassRoomId(String classRoomId, String userId) {
        try {
            Optional<Profile> existedProfile = profileRepository.findById(userId);
            Optional<ClassRoom> existClassRoom = classRoomRepository.findById(classRoomId);
            if (existedProfile.isEmpty()) {
                throw new RuntimeException("profile not found");
            }
            if (existClassRoom.isEmpty()) {
                throw new RuntimeException("classroom not found");
            }
            Profile profile = existedProfile.get();
            ClassRoom classRoom = existClassRoom.get();
            classRoom.getUsers().add(profile);
            return classRoomRepository.save(classRoom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteUserFromClassRoomByClassRoomId(String classRoomId, String userId) {
        try {
            Optional<Profile> existedProfile = profileRepository.findById(userId);
            Optional<ClassRoom> existClassRoom = classRoomRepository.findById(classRoomId);
            if (existedProfile.isEmpty()) {
                throw new RuntimeException("profile not found");
            }
            if(existClassRoom.isEmpty()) {
                throw new RuntimeException("user not found");
            }
            Profile profile = existedProfile.get();
            ClassRoom classRoom = existClassRoom.get();
            classRoom.getUsers().remove(profile);
            classRoomRepository.save(classRoom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Profile> getUsersByClassRoomId(String classRoomId) {
        Optional<ClassRoom> existClassRoom = classRoomRepository.findById(classRoomId);
        if (existClassRoom.isEmpty()){
            throw new RuntimeException("class room not found");
        }
        return existClassRoom.get().getUsers();
    }

    @Override
    public Set<ClassRoom> getClassRoomsByUserId(String userId) {
        Optional<Profile> existedProfile = profileRepository.findById(userId);
        if(existedProfile.isEmpty()) {
            throw new RuntimeException("profile not found");
        }
        return existedProfile.get().getClassRooms();

    }

    @Override
    public void deleteClassRoomByClassRoomId(String classRoomId) {
        try{
            Optional<ClassRoom> existClassRoom = classRoomRepository.findById(classRoomId);
            if(existClassRoom.isEmpty()) {
                throw new RuntimeException("class room not found");
            }
            classRoomRepository.delete(existClassRoom.get());

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClassRoom updateClassRoomByClassRoomId(String classRoomId, ClassRoom classRoom) {
        Optional<ClassRoom> existClassRoom = classRoomRepository.findById(classRoomId);
        if(existClassRoom.isEmpty()) {
            throw new RuntimeException("class room not found");
        }
        existClassRoom.get().setName(classRoom.getName());
        existClassRoom.get().setDescription(classRoom.getDescription());
        return classRoomRepository.save(existClassRoom.get());
    }
}
