package com.ITOSupportTracker.Controller;

import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.Entity.Tickets;
import com.ITOSupportTracker.Entity.comment;
import com.ITOSupportTracker.Entity.user;
import com.ITOSupportTracker.Exception.resourceNotFoundException;
import com.ITOSupportTracker.Repository.commentRepository;
import com.ITOSupportTracker.Repository.ticketsRepository;
import com.ITOSupportTracker.Service.userModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class userModuleController {
    @Autowired
    private userModuleService userModuleService;
    @Autowired
    private ticketsRepository ticketsRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.userRepository userRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.commentRepository commentRepository;
    @PostMapping("/UserModule")
    public String createTicket(@RequestBody Tickets tickets, @RequestParam Integer userId) {
        if (tickets == null) {
            return "failed to create";
        }
        List<user> userList=userRepository.findAll();
       // System.out.println(userList.toString());
        for(user users:userList) {
            if (userId != users.getUserId()) {
                return userModuleService.createTicket(tickets,userId);
            }

        }
        return ticketsRepository.findById(userId).orElseThrow(()->new resourceNotFoundException("user id is not correct :" + userId, "handling exception") )+"";

    }

    @GetMapping("/ViewTicket")

    public List<Ticket> ViewByUserId(@RequestParam Integer userId) {
        List<Ticket> ticketList=  userModuleService.viewTicket(userId);
        if (ticketList.isEmpty()) {
         return (List<Ticket>) ticketsRepository.findById(userId).
                 orElseThrow(() -> new resourceNotFoundException("user id not found with id :" + userId, "Missing Data Exception"));
        }

      return ticketList;
    }
    @PostMapping("Comment")
    public String 	CommentOnTicket(@RequestBody String message,@RequestParam Integer ticketId,@RequestParam Integer userId) {
        Tickets createTicket1 = new Tickets();
        List< Tickets > createTicketList;
        createTicketList = ticketsRepository.findAll();
        for (Tickets createTicket : createTicketList) {
            while (userId == createTicket.getUserId()&& ticketId == createTicket.getTicketId()) {
                comment comment = new comment();
                comment.setTicketId(ticketId);
                comment.setUserId(userId);
                comment.setMessage(message);
                this.commentRepository.save(comment);
                return "Successfully Added Comment on <" + ticketId + ">";
            }
        }
        /*boolean flag=true;
        for (Tickets createTicket : createTicketList) {
            if(ticketId==createTicket.getTicketId())
            {
                break;
            }
            else
            {
                flag=false;
            }
        }
        if(flag==false)
        {
            return ticketsRepository.findById(ticketId).orElseThrow(()->new resourceNotFoundException("ticket_id and user_id not correct", "Missing Data Exception") )+ "";

        }*/
        return "ticket_id and user_id not correct";
    }

}
