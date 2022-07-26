package com.ITOSupportTracker.Controller;

import com.ITOSupportTracker.DTO.ItItemView;
import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.Entity.Comment;
import com.ITOSupportTracker.Entity.Tickets;
import com.ITOSupportTracker.Entity.AdminTeam;
import com.ITOSupportTracker.Exception.ResourceNotFoundException;
import com.ITOSupportTracker.Repository.*;
import com.ITOSupportTracker.Service.itModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itTeamModule")
public class ItTeamModuleController {
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private itModuleService itModuleService;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/ViewTicketList")
    public List<?> viewTicket() {
        List<Ticket> tickets = itModuleService.viewTicketList();
        if (tickets.isEmpty()) {
            return (List<?>) new ResourceNotFoundException("No data found", "Missing Data Exception");
        }
        return itModuleService.viewTicketList();
    }

    @GetMapping("/ViewTicketList/{ticketId}")
    public  Object  ViewTicketList(@PathVariable Integer ticketId) {
        ItItemView viewTicketList = itModuleService.ViewByTicketId(ticketId);
        Tickets createTicket1 = new Tickets();
        List<Tickets> createTicketList;
        createTicketList = ticketsRepository.findAll();
        boolean flag = true;
        for (Tickets Ticket : createTicketList) {
            if (ticketId == Ticket.getTicketId()) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (flag == false) {
            return  ticketsRepository.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException(" Invalid Ticket Id" + ticketId, "Missing Data Exception"));
        }
        return viewTicketList;
    }

    @Autowired
    private TicketDAORepository ticketDAORepository;
    @PutMapping("/SetAssignee")
    public Object setAssignee(@RequestParam Integer ticketId, @RequestParam Integer adminId, @RequestParam Integer userId) {
        List<Tickets> createTicketList;
        List<AdminTeam> adminteams;
        adminteams = adminRepository.findAll();
        for (AdminTeam admin : adminteams) {
            if (admin.getAdminId() == adminId) {
                createTicketList = ticketsRepository.findAll();
                for (Tickets createTicket1 : createTicketList) {
                    if (ticketId != createTicket1.getTicketId() && userId != createTicket1.getUserId()) {
                       return  itModuleService.SetAssigneeById(ticketId, userId, adminId);
                        }
                }

            }
        }

        return  ticketsRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Incorrect Ticket Id and user Id: " + userId, "Missing Data Exception"));

    }

    @Autowired
private StatusRepository statusRepository;
    @PutMapping("/ChangeStatus")
    public String ChangeStatus(@RequestParam Integer ticketId,@RequestParam Integer statusId,@RequestParam Integer userId) {
        List<Tickets> createTicketList;
        createTicketList = ticketsRepository.findAll();
        Tickets createTicket = new Tickets();
        String oldstatus="";
        for (Tickets createTicket1 : createTicketList) {
            if (ticketId == createTicket1.getTicketId() && userId == createTicket1.getUserId()) {
                oldstatus= statusRepository.getReferenceById(createTicket1.getStatusId()).getStatusName();
            }
        }
        String returnStatus = "";
        boolean flag = true;
        for (Tickets createTicket1 : createTicketList) {
            if (ticketId == createTicket1.getTicketId() && userId == createTicket1.getReportedId()) {
                flag = false;
                itModuleService.SetStatus(ticketId, userId, statusId);
                returnStatus = "Status Change from <" + oldstatus + "> to <" + statusRepository.getReferenceById(statusId).getStatusName() + ">";
            }
        }
        if (flag == true) {
            return ticketsRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Incorrect Ticket Id and user Id: " + userId, "Missing Data Exception")) + "";
        }
        return returnStatus;
    }

    @PostMapping("Comment")
    public String 	CommentOnTicket(@RequestBody String message, @RequestParam Integer ticketId, @RequestParam Integer userId) {
        Tickets createTicket1 = new Tickets();
        List< Tickets > createTicketList;
        createTicketList = ticketsRepository.findAll();
        for (Tickets createTicket : createTicketList) {
            while (userId == createTicket.getUserId()&& ticketId == createTicket.getTicketId()) {
                Comment comment = new Comment();
                comment.setTicketId(ticketId);
                comment.setUserId(userId);
                comment.setMessage(message);
                this.commentRepository.save(comment);
                return "Successfully Added Comment on <" + ticketId + ">";
            }
        }
        return "ticket_id and user_id not correct" ;
    }
}
