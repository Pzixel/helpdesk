package helpdesk.services;

import helpdesk.dal.UserRepository;
import helpdesk.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private static final String myPrivateKey = "abcdefff3c0f2ada3b21e0c73b997a9dc0c3ff0b4f61913dfef5d12b0a10f81986abcdefff3c0f2ada3b21e0c73b997a9dc0c3ff0b4f61913dfef5d12b0a10f81986";
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String getJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, myPrivateKey)
                .compact();
    }
}
