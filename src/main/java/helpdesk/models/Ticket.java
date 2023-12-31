package helpdesk.models;

import java.util.Date;

public class Ticket {
    private final Integer id;
    private final String name;
    private final Date desiredDate;
    private final Urgency urgency;
    private final Status status;

    private final Integer userId;

    public Ticket(Integer id, String name, Date desiredDate, Urgency urgency, Status status, Integer userId) {
        this.id = id;
        this.name = name;
        this.desiredDate = desiredDate;
        this.urgency = urgency;
        this.status = status;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDesiredDate() {
        return desiredDate;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }
}
