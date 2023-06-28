package helpdesk.models;

public class JwtUser  {
    private final Integer id;
    private final String email;
    private final Role role;

    public JwtUser(Integer id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
