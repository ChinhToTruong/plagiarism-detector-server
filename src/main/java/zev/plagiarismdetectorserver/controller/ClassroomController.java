package zev.plagiarismdetectorserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.Profile;
import zev.plagiarismdetectorserver.service.ClassRoomService;

import java.util.Set;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassRoomService classRoomService;

    @PostMapping
    public ResponseEntity<ClassRoom> createClassRoom(@RequestBody ClassRoomCreateRequest request) {
        ClassRoom classRoom = classRoomService.createClassRoom(request);
        return new ResponseEntity<>(classRoom, HttpStatus.CREATED);
    }

    @PostMapping("/{classRoomId}/users/{userId}")
    public ResponseEntity<ClassRoom> addUserToClassRoom(@PathVariable String classRoomId,
                                                        @PathVariable String userId) {
        ClassRoom classRoom = classRoomService.addUserToClassRoomByClassRoomId(classRoomId, userId);
        return ResponseEntity.ok(classRoom);
    }

    @DeleteMapping("/{classRoomId}/users/{userId}")
    public ResponseEntity<Void> deleteUserFromClassRoom(@PathVariable String classRoomId,
                                                        @PathVariable String userId) {
        classRoomService.deleteUserFromClassRoomByClassRoomId(classRoomId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{classRoomId}/users")
    public ResponseEntity<Set<Profile>> getUsersByClassRoomId(@PathVariable String classRoomId) {
        Set<Profile> users = classRoomService.getUsersByClassRoomId(classRoomId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Set<ClassRoom>> getClassRoomsByUserId(@PathVariable String userId) {
        Set<ClassRoom> classRooms = classRoomService.getClassRoomsByUserId(userId);
        return ResponseEntity.ok(classRooms);
    }

    @DeleteMapping("/{classRoomId}")
    public ResponseEntity<Void> deleteClassRoom(@PathVariable String classRoomId) {
        classRoomService.deleteClassRoomByClassRoomId(classRoomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{classRoomId}")
    public ResponseEntity<ClassRoom> updateClassRoom(@PathVariable String classRoomId,
                                                     @RequestBody ClassRoom classRoom) {
        ClassRoom updatedClassRoom = classRoomService.updateClassRoomByClassRoomId(classRoomId, classRoom);
        return ResponseEntity.ok(updatedClassRoom);
    }
}
