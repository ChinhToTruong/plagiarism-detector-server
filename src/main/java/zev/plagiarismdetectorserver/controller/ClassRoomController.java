package zev.plagiarismdetectorserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateClassRoomRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.service.ClassRoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classroom")
@Slf4j
public class ClassRoomController {
    private final ClassRoomService classRoomService;

    @PostMapping("/create-class")
    public ResponseData<?> createNewClassRoom(@RequestBody @Valid ClassRoomCreateRequest request){
        log.info("Creating new classroom: {}", request);
        classRoomService.createNewClassRoom(request);
        return new ResponseData<>("created new classroom successfully.");
    }
    @GetMapping("/add-user-to-class/{classRoomId}/{userId}")
    public ResponseData<?> addUserToClassRoom(@PathVariable("classRoomId") String classRoomId, @PathVariable("userId") String userId){
        log.info("add userId: {} to classroomId: {}", userId, classRoomId);
        classRoomService.addUserToClassRoom(classRoomId, userId);
        return new ResponseData<>("created new classroom successfully.");
    }

    @GetMapping
    public ResponseData<?> getCLassRooms(){
        List<ClassRoom> classList = classRoomService.getClassRoom();
        return new ResponseData<>("thanh cong", classList);
    }

    @DeleteMapping("/delete-class/{classRoomId}")
    public ResponseData<?> deleteClassRoomByClassRoomId(@PathVariable("classRoomId") String classRoomId){
        log.info("delete userId: {}", classRoomId);
        classRoomService.deleteClassRoom(classRoomId);
        return new ResponseData<>("deleted classroom successfully.");
    }
    @PatchMapping("/update-class/{classRoomId}")
    public ResponseData<?> updateClassRoomByClassRoomId(@PathVariable("classRoomId") String classRoomId, @RequestBody @Valid UpdateClassRoomRequest request){
        log.info("Updating classroom: {}", classRoomId);
        classRoomService.updateClassRoom(classRoomId, request);
        return new ResponseData<>("updated classroom successfully.");
    }

    @GetMapping("/remove-user-from-class/{classRoomId}/{userId}")
    public ResponseData<?> removeUserFromClassRoom(@PathVariable("classRoomId") String classRoomId, @PathVariable("userId") String userId){
        log.info("remove userId: {} from classroomId: {}", userId, classRoomId);
        classRoomService.removeUserFromClassRoom(classRoomId, userId);
        return new ResponseData<>("removed user from classroom successfully.");
    }

    @GetMapping("/{classRoomId}")
    public ResponseData<?> getClassRoomById(@PathVariable("classRoomId") String classRoomId){
        log.info("get class room by id: {}", classRoomId);
        ClassRoom  classRoom= classRoomService.getClassRoom(classRoomId);
        return new ResponseData<>("get class room successfully.", classRoom);
    }

}
