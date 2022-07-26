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
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private CommentRepository commentRepository;


    public Category findByCategoryId(Integer categoryId) {
        return categoryRepository.getReferenceById(categoryId);
    }

    public SubCategory findBySubCategoryId(Integer subCategoryId) {
        return subCategoryRepository.getReferenceById(subCategoryId);
    }
    public User findByUserId(Integer userId) {
        return userRepository.getReferenceById(userId);
    }
    public Priority findByPriorityId(Integer priorityId) {
        return priorityRepository.getReferenceById(priorityId);
    }

    public Status findByStatusId(Integer statusId) {
        return statusRepository.getReferenceById(statusId);
    }

    public Comment findByCommentId(Integer commentId)
    {
        return commentRepository.getReferenceById(commentId);
    }
    public String createTicket(Tickets tickets,Integer userId)
    {
        Tickets ticket=new Tickets();
        ticket.setTicketId(tickets.getTicketId());
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
        String url="http://localhost:8184/api/itTeamModule/ViewTicketList/";
        ticket.setUrlLink(url);
       ticketsRepository.save(ticket);
      List<Tickets> ticket2=ticketsRepository.findAll();
        for (Tickets tickets1 : ticket2) {
            if(userId== tickets1.getUserId())
            {
                tickets1.setUrlLink(url+tickets1.getTicketId());
            }
           ticketsRepository.save(ticket);
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
            Category category=new Category();
            SubCategory subCategory=new SubCategory();
            Priority priority=new Priority();
            Status status=new Status();
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
