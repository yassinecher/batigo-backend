package com.batigobackend.batigo.Service;
import com.batigobackend.batigo.Model.ChangePasswordRequest;
import com.batigobackend.batigo.Entity.User;
import com.batigobackend.batigo.Repository.UserRepository;
import com.batigobackend.batigo.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {



    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }
    public void changePasswordForgotten(String password, User connectedUser) {


        // check if the current password is correct
        if (passwordEncoder.matches(password, connectedUser.getPassword())) {
            throw new IllegalStateException("Vous avez choisi le même mot de passe.");
        }


        // update the password
        connectedUser.setPassword(passwordEncoder.encode(password));

        // save the new password
        userRepository.save(connectedUser);
    }

    private final JwtService jwtService;
    public User getUser(HttpServletRequest request) {
        var client = User.builder().build();
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return client;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                return user;
            }
        }

        return client;
    }
    public List<User> getUsers(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (jwtService.isTokenValid(refreshToken, user)) {
                return userRepository.findAll(); // Return the list of all users
            }
        }

        throw new RuntimeException("Unauthorized access");
    }

    public Boolean findUserByEmail(String email) {
       return userRepository.findByEmail(email).isPresent();
    }

    public User updateUser(Integer id, User updatedUserRequest) {
        // Fetch user from the database
        User existingUser = userRepository.findById(id).orElse(null);
        // Update fields as necessary
        existingUser.setEmail(updatedUserRequest.getEmail());
        existingUser.setFirstname(updatedUserRequest.getFirstname());
        existingUser.setLastname(updatedUserRequest.getLastname());
        existingUser.setGender(updatedUserRequest.getGender());
        existingUser.setPhoneNumber(updatedUserRequest.getPhoneNumber());
        // Update password if provided (you might want additional validation here)
        if (updatedUserRequest.getPassword() != null && !updatedUserRequest.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUserRequest.getPassword())); // Assuming you're using a password encoder
        }

        // Save the updated user back to the database
        return userRepository.save(existingUser);
    }

}
