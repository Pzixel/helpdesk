package helpdesk.controller;

import helpdesk.dal.UserRepository;
import helpdesk.models.Role;
import helpdesk.services.UserService;
import helpdesk.utils.HashHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import helpdesk.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RestController
@EnableAutoConfiguration
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/auth/login")
    ResponseEntity<?> login(
            HttpServletResponse response,
            @RequestParam String email,
            @RequestParam String password
    ) {
        User matchingUser = userService.findByEmail(email).orElse(null);

        if (matchingUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        String passwordHash = HashHelper.hash(password, matchingUser.getPasswordSalt());
        if (!passwordHash.equals(matchingUser.getPasswordHash())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }

        String token = userService.getJwtToken(matchingUser);
        Cookie jwtTokenCookie = new Cookie("jwtToken", token);
        jwtTokenCookie.setMaxAge(86400);
        jwtTokenCookie.setHttpOnly(true);
        jwtTokenCookie.setPath("/");

        response.addCookie(jwtTokenCookie);

        return ResponseEntity.ok().body("Logged in");
    }

}
