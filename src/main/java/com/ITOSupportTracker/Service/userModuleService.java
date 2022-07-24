package com.ITOSupportTracker.Service;

import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.Entity.*;
import com.ITOSupportTracker.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class userModuleService {

    @Autowired
    private categoryRepository categoryRepository;
    @Autowired
    private subCategoryRepository subCategoryRepository;
    @Autowired
    private priorityRepository priorityRepository;
    @Autowired
    private statusRepository statusRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private  ticketsRepository ticketsRepository;
    @Autowired
    private commentRepository commentRepository;

   /* public static APIResponse getuserIdByQueryDsl(Integer userId) {
        APIResponse apiResponse = new APIResponse();

        List<Tickets> ticketsList= ticketsRepository.getAllTicketsByQueryDsl(userId);
        apiResponse.setData(ticketsList);
        return apiResponse;
    }*/

    public category findByCategoryId(Integer categoryId) {
        return categoryRepository.getReferenceById(categoryId);
    }

    public subCategory findBySubCategoryId(Integer subCategoryId) {
        return subCategoryRepository.getReferenceById(subCategoryId);
    }
    public user findByUserId(Integer userId) {
        return userRepository.getReferenceById(userId);
    }
    public priority findByPriorityId(Integer priorityId) {
        return priorityRepository.getReferenceById(priorityId);
    }

    public status findByStatusId(Integer statusId) {
        return statusRepository.getReferenceById(statusId);
    }

    public comment findByCommentId(Integer commentId)
    {
        return commentRepository.getReferenceById(commentId);
    }
   public String createTicket(Tickets tickets,Integer userId)
    {
     Tickets ticket=new Tickets();
        System.out.println(tickets.getTicketId());
        ticket.setTicketId(tickets.getTicketId());
        System.out.println(tickets.getDescription());
        ticket.setDescription(tickets.getDescription());
        ticket.setSubjects(tickets.getSubjects());
        ticket.setAssigneeId(tickets.getAssigneeId());
        ticket.setCreateDateTime(LocalDateTime.now());
        ticket.setLastModifiedDatetime(tickets.getLastModifiedDatetime());
        ticket.setCategoryId(tickets.getCategoryId());
        ticket.setStatusId(tickets.getStatusId());
        ticket.setSubCategoryId(tickets.getSubCategoryId());
        ticket.setPriorityId(tickets.getPriorityId());
        ticket.setReportedId(userId);
        ticket.setUserId(userId);
        String url="http://localhost:8184/api/userModule/";
        //ticket.setUrlLink(u);
        ticketsRepository.save(ticket);
        List<Tickets> ticket2=ticketsRepository.findAll();
        for (Tickets tickets1 : ticket2) {
            if(userId== tickets1.getUserId())
            {
                tickets1.setUrlLink(url+tickets1.getTicketId());
            }
            this.ticketsRepository.save(tickets1);
        }
        return "Ticket created Successfully " + ticket.getTicketId()+" "+ url+""+ticket.getTicketId();

    }

 public List<Ticket> viewTicket(Integer userId)
    {
        List<Ticket> ticketsList=new ArrayList<>();
        List<Tickets> tickets1;
        tickets1=ticketsRepository.findAll();
        for (Tickets ticket:tickets1)
        {
            Ticket tickets=new Ticket();
            category category=new category();
            subCategory subCategory=new subCategory();
            priority priority=new priority();
            status status=new status();
          if (userId ==ticket.getUserId()) {
                tickets.setTicketId(ticket.getTicketId());
                tickets.setCategoryDesc(findByCategoryId(ticket.getCategoryId()).getCategoryDesc());
                tickets.setSubCategoryDesc(findBySubCategoryId(ticket.getSubCategoryId()).getSubCategoryDesc());
               tickets.setPriorityName(findByPriorityId(ticket.getPriorityId()).getPriorityName());
               tickets.setStatusName(findByStatusId(ticket.getStatusId()).getStatusName());
                tickets.setSubjects(ticket.getSubjects());
                tickets.setUrlLink(ticket.getUrlLink());
                tickets.setAssigneeId(ticket.getAssigneeId());
             ticketsList.add(tickets);
           }
        }
        return ticketsList;
    }


}
