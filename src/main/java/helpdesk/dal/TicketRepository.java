package helpdesk.dal;

import helpdesk.models.Role;
import helpdesk.models.Ticket;
import helpdesk.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository {
    private final ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public ArrayList<Ticket> getTickedByUser(Integer userId) {
        return Arrays.stream(tickets.toArray(new Ticket[0]))
                        .filter(ticket -> ticket.getUserId() == userId)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
