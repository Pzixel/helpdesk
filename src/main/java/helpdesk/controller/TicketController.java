package helpdesk.controller;

import helpdesk.models.Ticket;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@EnableAutoConfiguration
public class TicketController {
    @RequestMapping("/tickets/myTickets")
    ArrayList<Ticket> myTickets() {
    }
}
