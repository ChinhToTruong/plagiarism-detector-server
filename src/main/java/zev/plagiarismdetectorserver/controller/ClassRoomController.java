package zev.plagiarismdetectorserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.ClassRoomCreateRequest;
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
}
