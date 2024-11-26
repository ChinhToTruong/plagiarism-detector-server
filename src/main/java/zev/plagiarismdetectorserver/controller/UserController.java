package zev.plagiarismdetectorserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.repository.SearchRepository;
import zev.plagiarismdetectorserver.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User Controller")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseData<?> createNewUser(@Valid @RequestBody AddUserRequest request){

        log.info("Creating new user: {}", request);
        userService.createUser(request);
        return new ResponseData<>("Created new user.");
    }

    @PutMapping("/update-user/{userId}")
    public ResponseData<?> updateUserByUserId(
            @Valid @RequestBody UpdateProfileUserRequest request,
            @PathVariable("userId") String userId
    ){

        log.info("Updating user: {}", request);
        userService.updateUserByUserId(request, userId);
        return new ResponseData<>("Updated user, id: " + userId);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseData<?> deleteUserByUserId(@PathVariable("userId") String userId){
        log.info("Deleting user: {}", userId);
        userService.deleteUserByUserId(userId);
        return new ResponseData<>("Deleted user, id: " + userId);
    }

    @GetMapping("/{userId}")
    public ResponseData<?> getUserById(@PathVariable(value = "userId", required = false) String userId){
        log.info("Getting user by id: {}", userId);
        User user = userService.getUserById(userId);
        return new ResponseData<>("Getting user by id: " + userId, user);
    }

    @GetMapping
    public ResponseData<?> getUsers(){
        log.info("Getting users");
        List<User> users = userService.getUsers();
        return new ResponseData<>("Get list of users", users);
    }

    @GetMapping("/search-user")
    public ResponseData<?> searchUsers(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(defaultValue = "desc", required = false) String sortBy,
            @RequestParam(defaultValue = "", required = false) String classroom,
            @RequestParam(required = false) String... search
    ){
        log.info("Searching users: {}, {}", search, classroom);
        return userService.searchUser(pageNo, pageSize,sortBy, classroom, search);
    }

    @GetMapping("/search-user/advance")
    public ResponseData<?> searchUsersAndAdvance(
            Pageable pageable,
            @RequestParam(required = false) String[] classroom,
            @RequestParam(required = false) String[] user
    ){
        log.info("Searching users: {}, {}", user, classroom);
        return userService.searchUserAdvance(pageable, classroom, user);
    }

}
