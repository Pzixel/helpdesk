package helpdesk.controller;

import helpdesk.dal.UserRepository;
import helpdesk.models.Role;
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
    private static final String myPrivateKey = "abcdefff3c0f2ada3b21e0c73b997a9dc0c3ff0b4f61913dfef5d12b0a10f81986abcdefff3c0f2ada3b21e0c73b997a9dc0c3ff0b4f61913dfef5d12b0a10f81986";
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/auth/login")
    ResponseEntity<?> login(
            HttpServletResponse response,
            @RequestParam String email,
            @RequestParam String password
    ) {
        User matchingUser = userRepository.findByEmail(email).orElse(null);

        if (matchingUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        String passwordHash = HashHelper.hash(password, matchingUser.getPasswordSalt());
        if (!passwordHash.equals(matchingUser.getPasswordHash())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }

        String token = Jwts.builder()
                .setSubject(matchingUser.getEmail())
                .claim("roles", matchingUser.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, myPrivateKey)
                .compact();

        Cookie jwtTokenCookie = new Cookie("jwtToken", token);
        jwtTokenCookie.setMaxAge(86400);
        jwtTokenCookie.setSecure(true);
        jwtTokenCookie.setHttpOnly(true);

        response.addCookie(jwtTokenCookie);

        return ResponseEntity.ok().body("Logged in");
    }

}
