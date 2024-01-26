package uz.booker.bookstore.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.booker.bookstore.dto.JwtAuthenticationResponse;
import uz.booker.bookstore.dto.JwtAuthorizationResponse;
import uz.booker.bookstore.dto.SignInRequest;
import uz.booker.bookstore.dto.SignUpRequest;

public interface AuthenticationService {
    JwtAuthorizationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
