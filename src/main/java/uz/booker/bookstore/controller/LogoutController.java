package uz.booker.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.booker.bookstore.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LogoutController {

    private final AuthenticationService authenticationService;


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        authenticationService.logout(request,response);
        return ResponseEntity.ok().build();
    }
}
