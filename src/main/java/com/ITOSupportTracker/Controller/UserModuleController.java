package com.ITOSupportTracker.Controller;

import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.Entity.Comment;
import com.ITOSupportTracker.Entity.Tickets;
import com.ITOSupportTracker.Entity.User;
import com.ITOSupportTracker.Exception.ResourceNotFoundException;
import com.ITOSupportTracker.Repository.CommentRepository;
import com.ITOSupportTracker.Repository.TicketsRepository;
import com.ITOSupportTracker.Repository.UserRepository;
import com.ITOSupportTracker.Service.userModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserModuleController {
    @Autowired
    private userModuleService userModuleService;
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/UserModule")
    public String createTicket(@RequestBody Tickets tickets, @RequestParam Integer userId) {
        if (tickets == null) {
            return "failed to create";
        }
        List<User> userList=userRepository.findAll();
        for(User users:userList) {
            if (userId == users.getUserId()) {
                return userModuleService.createTicket(tickets,userId);
            }
        }
        return ticketsRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user id is not correct :" + userId, "handling exception") )+"";

    }

    @GetMapping("/ViewTicket")

    public List<Ticket> ViewByUserId(@RequestParam Integer userId) {
        List<Ticket> ticketList = userModuleService.viewTicket(userId);
        if (ticketList.isEmpty()) {
            return (List<Ticket>) ticketsRepository.findById(userId).
                    orElseThrow(() -> new ResourceNotFoundException("user id not found with id :" + userId, "Missing Data Exception"));
        }

        return ticketList;
    }

    @PostMapping("Comment")
    public String CommentOnTicket(@RequestBody String message, @RequestParam Integer ticketId, @RequestParam Integer userId) {
        List<User> user= userRepository.findAll();
        List<Tickets> ticketsList = ticketsRepository.findAll();
        List< Tickets > createTicketList;
        createTicketList = ticketsRepository.findAll();
        boolean flag=true;
        for (User user1 : user) {
            System.out.println(user1.getUserId());
            if (user1.getUserId() == (userId)) {
                Comment comment = new Comment();
                comment.setTicketId(ticketId);
                comment.setMessage(message);
                comment.setUserId(userId);
                this.commentRepository.save(comment);
                return "Successfully Added Comment on <" + ticketId + ">";
            }

        }
        for (Tickets tickets:createTicketList)
        {
            if(ticketId==tickets.getTicketId())
            {
                flag=false;
            }
        }
        if(flag==true)
        {
            return "ticket_id and user_id not correct";
        }

        return  "ticket_id and user_id not correct";

    }
}
