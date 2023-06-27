package helpdesk.controller;

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

import java.util.Arrays;

@RestController
@EnableAutoConfiguration
public class AuthController {
    private static final User[] users = {
            // myCoolPwd password
        new User("Bob@gmail.com", "a3abf03c0f2ada3b21e0c73b997a9dc0c3ff0b4f61913dfef5d12b0a10f81986", "salt", Role.EMPLOYEE),
            // alicaRules
        new User("Alice@gmail.com", "69f55e9293a89a3e1f9e75b393ffb7a447aecdea854f42a80166a3bb59e65ed8", "biabd^&^&", Role.MANAGER),
            // yasha
        new User("Luna@gmail.com", "9243d6b7d5e1500f355c5dcbd15c86669d082c9416a2f5621c3eac2ab5ef5d31", "config", Role.ENGINEER),
    };

    @RequestMapping("/login")
    ResponseEntity<?> login(
            HttpServletResponse response,
            @RequestParam String email,
            @RequestParam String password
    ) {
        User matchingUser = Arrays.stream(users)
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (matchingUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        String passwordHash = HashHelper.hash(password, matchingUser.getPasswordSalt());
        if (!passwordHash.equals(matchingUser.getPasswordHash())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }

        Cookie jwtTokenCookie = new Cookie("user-id", "c2FtLnNtaXRoQGV4YW1wbGUuY29t");
        jwtTokenCookie.setMaxAge(86400);
        jwtTokenCookie.setSecure(true);
        jwtTokenCookie.setHttpOnly(true);

        response.addCookie(jwtTokenCookie);

        return ResponseEntity.ok().body("Logged in");
    }

}
