package uz.booker.bookstore.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.booker.bookstore.dto.ChangePasswordRequest;
import uz.booker.bookstore.dto.UserDto;
import uz.booker.bookstore.service.impl.UserServiceImpl;




@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl service;


    @PutMapping("/edit-profile/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        service.updateUser(id, updatedUserDto);
        return ResponseEntity.ok("User updated successfully");
    }


    @PutMapping("/{id}/change-password")
    public ResponseEntity<?> changePasswordById(@PathVariable Long id, @RequestBody ChangePasswordRequest passwordRequest) {
        service.changePasswordById(id, passwordRequest);
        return ResponseEntity.ok("Password changed successfully");
    }




}
