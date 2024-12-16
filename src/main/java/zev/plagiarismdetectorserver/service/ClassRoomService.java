package zev.plagiarismdetectorserver.service;

import java.util.List;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateClassRoomRequest;
import zev.plagiarismdetectorserver.dto.response.ClassDetailsResponse;
import zev.plagiarismdetectorserver.entity.ClassRoom;

public interface ClassRoomService {

  String createNewClassRoom(ClassRoomCreateRequest request);

  void updateClassRoom(String classRoomId, UpdateClassRoomRequest request);

  void deleteClassRoom(String classRoomId);

  List<ClassRoom> getClassRoom();

  ClassDetailsResponse getClassRoom(String classRoomId);

  void addUserToClassRoom(String classRoomId, String userId);

  void removeUserFromClassRoom(String classRoomId, String userId);

}
