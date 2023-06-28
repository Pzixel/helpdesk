package helpdesk.models;

//An Employee
//        A Manager
//        An Engineer

public class User {
    private final Integer id;
    private final String email;
    private final String passwordHash;
    private final String passwordSalt;
    private final Role role;

    public User(Integer id, String email, String passwordHash, String passwordSalt, Role role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.role = role;
    }

    public Integer getId() {
        return id;
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