package zev.plagiarismdetectorserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.AddUserRequest;
import zev.plagiarismdetectorserver.dto.request.UpdateProfileUserRequest;
import zev.plagiarismdetectorserver.entity.Profile;
import zev.plagiarismdetectorserver.entity.User;
import zev.plagiarismdetectorserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add-user")
    public User addUser(@RequestBody AddUserRequest user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete-user/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping("/{userId}")
    public Profile getUser(@PathVariable String userId) {
        return userService.getProfileByUserId(userId);
    }

    @PostMapping("/update-user-profile/{userId}")
    public User updateProfileUserByUserId(@PathVariable("userId") String userId, @RequestBody UpdateProfileUserRequest request){
        return userService.updateUserById(userId, request);
    }
}
