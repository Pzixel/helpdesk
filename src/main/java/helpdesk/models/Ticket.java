package helpdesk.models;

import java.util.Date;

public class Ticket {
    private final Long id;
    private final String name;
    private final Date desiredDate;
    private final Urgency urgency;
    private final Status status;

    private final long userId;

    public Ticket(Long id, String name, Date desiredDate, Urgency urgency, Status status, long userId) {
        this.id = id;
        this.name = name;
        this.desiredDate = desiredDate;
        this.urgency = urgency;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
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

    public long getUserId() {
        return userId;
    }
}
