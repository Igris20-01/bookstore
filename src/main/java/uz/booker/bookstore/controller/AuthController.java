package uz.booker.bookstore.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.booker.bookstore.dto.JwtAuthenticationResponse;
import uz.booker.bookstore.dto.JwtAuthorizationResponse;
import uz.booker.bookstore.dto.SignInRequest;
import uz.booker.bookstore.dto.SignUpRequest;
import uz.booker.bookstore.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<JwtAuthorizationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }



}