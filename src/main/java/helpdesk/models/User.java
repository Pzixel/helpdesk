package helpdesk.models;

//An Employee
//        A Manager
//        An Engineer

enum Role {
    EMPLOYEE, MANAGER, ENGINEER
}

class User {
    private final String name;
    private final String email;
    private final String password;
    private final Role role;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}