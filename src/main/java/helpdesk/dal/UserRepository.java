package helpdesk.dal;

import helpdesk.models.Role;
import helpdesk.models.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final User[] users = {
            // myCoolPwd password
            new User("Bob@gmail.com", "a3abf03c0f2ada3b21e0c73b997a9dc0c3ff0b4f61913dfef5d12b0a10f81986", "salt", Role.EMPLOYEE),
            // alicaRules
            new User("Alice@gmail.com", "69f55e9293a89a3e1f9e75b393ffb7a447aecdea854f42a80166a3bb59e65ed8", "biabd^&^&", Role.MANAGER),
            // yasha
            new User("Luna@gmail.com", "9243d6b7d5e1500f355c5dcbd15c86669d082c9416a2f5621c3eac2ab5ef5d31", "config", Role.ENGINEER),
    };

    public Optional<User> findByEmail(String email) {
        return Arrays.stream(users)
                        .filter(user -> user.getEmail().equals(email))
                        .findFirst();
    }
}
