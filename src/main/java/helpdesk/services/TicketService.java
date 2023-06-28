package helpdesk.services;

import helpdesk.dal.TicketRepository;
import helpdesk.models.Ticket;
import helpdesk.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository TicketRepository) {
        this.ticketRepository = TicketRepository;
    }

    public ArrayList<Ticket> getTickedByUser(Integer userId) {
        return ticketRepository.getTickedByUser(userId);
    }
}
