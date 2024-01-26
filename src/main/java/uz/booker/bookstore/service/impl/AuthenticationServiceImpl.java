package uz.booker.bookstore.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import uz.booker.bookstore.dto.JwtAuthenticationResponse;
import uz.booker.bookstore.dto.JwtAuthorizationResponse;
import uz.booker.bookstore.dto.SignInRequest;
import uz.booker.bookstore.dto.SignUpRequest;
import uz.booker.bookstore.entity.user.User;
import uz.booker.bookstore.repository.UserRepository;
import uz.booker.bookstore.service.AuthenticationService;
import uz.booker.bookstore.service.JwtService;




@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    @Override
    public JwtAuthorizationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and confirmation password do not match");
        }
        userRepository.save(user);


        return JwtAuthorizationResponse.builder().status("CREATED").build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public void  logout(HttpServletRequest request, HttpServletResponse response)  {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request,response,authentication);
            }
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();

            logger.info("User logged out successfully.");

        } catch (Exception e){
            logger.error("Error during logout:", e);
        }
    }
}
