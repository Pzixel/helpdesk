package helpdesk.models;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Role {
    EMPLOYEE, MANAGER, ENGINEER;

    public static Role fromString(String role) {
        return switch (role) {
            case "EMPLOYEE" -> EMPLOYEE;
            case "MANAGER" -> MANAGER;
            case "ENGINEER" -> ENGINEER;
            default -> null;
        };
    }
}
