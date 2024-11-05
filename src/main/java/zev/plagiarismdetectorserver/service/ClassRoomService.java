package zev.plagiarismdetectorserver.service;

import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.Profile;

import java.util.Set;

public interface ClassRoomService {
    ClassRoom createClassRoom(ClassRoomCreateRequest request);
    ClassRoom addUserToClassRoomByClassRoomId(String classRoomId, String userId);
    void deleteUserFromClassRoomByClassRoomId(String classRoomId, String userId);
    Set<Profile> getUsersByClassRoomId(String classRoomId);
    Set<ClassRoom> getClassRoomsByUserId(String userId);
    void deleteClassRoomByClassRoomId(String classRoomId);
    ClassRoom updateClassRoomByClassRoomId(String classRoomId, ClassRoom classRoom);
}
