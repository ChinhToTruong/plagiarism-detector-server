package zev.plagiarismdetectorserver.service;

import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateClassRoomRequest;
import zev.plagiarismdetectorserver.entity.ClassRoom;

import java.util.List;

public interface ClassRoomService {
    void createNewClassRoom(ClassRoomCreateRequest request);
    void updateClassRoom(String classRoomId, UpdateClassRoomRequest request);
    void deleteClassRoom(String classRoomId);
    List<ClassRoom> getClassRoom();
    ClassRoom getClassRoom(String classRoomId);
    void addUserToClassRoom(String classRoomId, String userId);
    void removeUserFromClassRoom(String classRoomId, String userId);

}
