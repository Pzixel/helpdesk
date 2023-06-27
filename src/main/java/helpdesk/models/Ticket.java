package helpdesk.models;

import java.util.Date;

public class Ticket {
    private final Long id;
    private final String name;
    private final Date desiredDate;
    private final Urgency urgency;
    private final Status status;

    public Ticket(Long id, String name, Date desiredDate, Urgency urgency, Status status) {
        this.id = id;
        this.name = name;
        this.desiredDate = desiredDate;
        this.urgency = urgency;
        this.status = status;
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
}
