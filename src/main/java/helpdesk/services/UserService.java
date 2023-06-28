package helpdesk.services;

import helpdesk.dal.UserRepository;
import helpdesk.models.JwtUser;
import helpdesk.models.Role;
import helpdesk.models.User;
import helpdesk.models.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
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
                .setSubject(user.getId().toString())
                .claim("data", new JwtUser(user.getId(), user.getEmail(), user.getRole()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, myPrivateKey)
                .compact();
    }

    public JwtUser getJwtUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(myPrivateKey)
                .parseClaimsJws(token)
                .getBody();
        java.util.LinkedHashMap o = (java.util.LinkedHashMap) claims.get("data");
        return new JwtUser(
                (Integer) o.get("id"),
                (String) o.get("email"),
                Role.fromString((String) o.get("role"))
        );
    }

    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
