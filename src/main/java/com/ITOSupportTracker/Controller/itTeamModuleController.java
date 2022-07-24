package com.ITOSupportTracker.Controller;

import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.DTO.TicketDAO;
import com.ITOSupportTracker.DTO.itItemView;
import com.ITOSupportTracker.Entity.Tickets;
import com.ITOSupportTracker.Entity.adminTeam;
import com.ITOSupportTracker.Entity.comment;
import com.ITOSupportTracker.Exception.resourceNotFoundException;
import com.ITOSupportTracker.Repository.*;
import com.ITOSupportTracker.Service.itModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itTeamModule")
public class itTeamModuleController {
    @Autowired
    private com.ITOSupportTracker.Repository.ticketsRepository ticketsRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.userRepository userRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.commentRepository commentRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private itModuleService itModuleService;
    @Autowired
    private adminRepository adminRepository;

    @GetMapping("/ViewTicketList")
    public List<?> viewTicket() {
        List<Ticket> tickets = itModuleService.viewTicketList();
        if (tickets.isEmpty()) {
            return (List<?>) new resourceNotFoundException("No data found", "Missing Data Exception");
        }
        return itModuleService.viewTicketList();
    }

    @GetMapping("/ViewTicketList/{ticketId}")
    public List<?> ViewTicketList(@PathVariable Integer ticketId) {
        List<?> viewTicketList = itModuleService.ViewByTicketId(ticketId);
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
            return (List<?>) ticketsRepository.findById(ticketId).orElseThrow(() -> new resourceNotFoundException(" Invalid Ticket Id" + ticketId, "Missing Data Exception"));
        }
        return viewTicketList;
    }

    @Autowired
    private TicketDAORepository ticketDAORepository;

    @PutMapping("/SetAssignee")
    public TicketDAO setAssignee(@RequestParam Integer ticketId, @RequestParam Integer adminId, @RequestParam Integer userId) {
        List<Tickets> createTicketList;
        List<adminTeam> adminteams;
        adminteams = adminRepository.findAll();
        // CreateTicket createTicket=new CreateTicket();
        for (adminTeam admin : adminteams) {
            if (admin.getAdminId() == adminId) {
                createTicketList = ticketsRepository.findAll();
                for (Tickets createTicket1 : createTicketList) {
                    System.out.println("t" + createTicket1.getTicketId());
                    System.out.println("tc" + ticketId);
                    if (ticketId == createTicket1.getTicketId() && userId == createTicket1.getUserId()) {
                        itModuleService.SetAssigneeById(ticketId, userId, adminId);
                    }
                }
            }
        }
        return ticketDAORepository.findById(userId).orElseThrow(() -> new resourceNotFoundException("Incorrect Ticket Id and user Id: " + userId, "Missing Data Exception"));

}


@Autowired
private statusRepository statusRepository;
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
            return ticketsRepository.findById(userId).orElseThrow(() -> new resourceNotFoundException("Incorrect Ticket Id and user Id: " + userId, "Missing Data Exception")) + "";
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
                comment comment = new comment();
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
