package uz.booker.bookstore.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.booker.bookstore.dto.ChangePasswordRequest;
import uz.booker.bookstore.dto.UserDto;
import uz.booker.bookstore.entity.user.User;
import uz.booker.bookstore.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtServiceImpl jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;



    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Transactional
    public void updateUser(Long id, UserDto updatedUserDto) {
        userRepository.findById(id)
                .map(existingUser -> {
                    log.info("Updating user with id {}: {}", id, updatedUserDto);

                    String oldEmail = existingUser.getEmail();
                    existingUser.setFullName(updatedUserDto.getFullName());
                    existingUser.setEmail(updatedUserDto.getEmail());
                    existingUser.setRole(updatedUserDto.getRole());


                    User updatedUser = userRepository.save(existingUser);
                    log.info("User updated successfully: {}", updatedUser);

                    if (!oldEmail.equals(updatedUser.getEmail())) {
                        log.info("User email has been updated. Invalidating current token.");
                        jwtService.invalidateToken(oldEmail);

                        return "redirect:/login";
                    }
                    return null;
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @Transactional
    public void changePasswordById(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // check if current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // check if new password and confirmation password match
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Trying to load user by username: {}", username);
        return userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("User with email {} not found", username);
                    return new UsernameNotFoundException("User not found " + username);});

    }
}
