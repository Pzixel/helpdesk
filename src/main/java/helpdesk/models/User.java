package helpdesk.models;

//An Employee
//        A Manager
//        An Engineer

public class User {
    private final String email;
    private final String passwordHash;
    private final String passwordSalt;
    private final Role role;

    public User(String email, String passwordHash, String passwordSalt, Role role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public Role getRole() {
        return role;
    }
}