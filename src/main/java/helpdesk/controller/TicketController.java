package helpdesk.controller;

import helpdesk.models.JwtUser;
import helpdesk.models.Ticket;
import helpdesk.services.TicketService;
import helpdesk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RestController
@EnableAutoConfiguration
public class TicketController {
    private final UserService userService;
    private final TicketService ticketService;

    @Autowired
    public TicketController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @RequestMapping("api/tickets/myTickets")
    ArrayList<Ticket> myTickets(@RequestHeader(name="Authorization") String token) {
        JwtUser jwtUser = userService.getJwtUser(token);
        System.out.println(jwtUser.getId());
        return ticketService.getTickedByUser(jwtUser.getId());
    }
}
