package com.ITOSupportTracker.Service;

import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.DTO.TicketDAO;
import com.ITOSupportTracker.DTO.itItemView;
import com.ITOSupportTracker.Entity.*;
import com.ITOSupportTracker.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class itModuleService {


    @Autowired
    private com.ITOSupportTracker.Repository.categoryRepository categoryRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.subCategoryRepository subCategoryRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.priorityRepository priorityRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.statusRepository statusRepository;
    @Autowired
    private com.ITOSupportTracker.Repository.userRepository userRepository;
    @Autowired
    private  ticketsRepository ticketsRepository;
    @Autowired
    private commentRepository commentRepository;



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

    public List<Ticket> viewTicketList()
    {
        List<Ticket> ticketsList=new ArrayList<>();
        List<Tickets> tickets1;
        tickets1=ticketsRepository.findAll();
        for (Tickets ticket:tickets1)
        {
            Ticket tickets=new Ticket();
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
        return ticketsList;
    }
    public List<itItemView> ViewByTicketId(Integer ticketId)
    {
        List<Tickets> TicketList;
        List<itItemView> TicketList1 = new ArrayList<>();
        TicketList = ticketsRepository.findAll();
        for (Tickets createTicket : TicketList) {
            if(ticketId == createTicket.getTicketId()) {
                itItemView itViewTicket = new itItemView();
                itViewTicket.setTicketId(createTicket.getTicketId());
                itViewTicket.setCategoryId(createTicket.getCategoryId());
                itViewTicket.setSubCategoryId(createTicket.getSubCategoryId());
                itViewTicket.setDescription(createTicket.getDescription());
                itViewTicket.setSubjects(createTicket.getSubjects());
                itViewTicket.setAssigneeId(createTicket.getAssigneeId());
                itViewTicket.setPriorityId(createTicket.getPriorityId());
                itViewTicket.setStatusId(createTicket.getStatusId());
                itViewTicket.setCreateDateTime(createTicket.getCreateDateTime());
                itViewTicket.setReportedId(createTicket.getUserId());
                itViewTicket.setLastModifiedDatetime(createTicket.getLastModifiedDatetime());
                itViewTicket.setUserId(createTicket.getUserId());
                String url="http://localhost:8184/api/userModule/";
                itViewTicket.setUrlLink(url+createTicket.getTicketId());
                List<user> Userlist= userRepository.findAll();
                for (user user : Userlist) {
                    System.out.println(user.getUserId());
                    Integer user_id= Integer.valueOf(user.getUserId());
                    if (createTicket.getUserId()==user_id) {
                        itViewTicket.setUserName(findByUserId(createTicket.getUserId()).getUserName());
                        break;
                    }
                }
                List<comment> comments=commentRepository.findAll();
                String comments1="";
                for (comment comment : comments) {
                    System.out.println(createTicket.getTicketId());
                    System.out.println(comment.getTicketId());
                    if (comment.getTicketId()==createTicket.getTicketId()) {
                         comments1=comments1+","+comment.getMessage();

                        //break;
                    }
                    itViewTicket.setMessage(comments1);
                }

                TicketList1.add(itViewTicket);
            }
        }
        return TicketList1;
    }
    @Autowired
    private TicketDAORepository ticketDAORepository;
    public TicketDAO SetAssigneeById(Integer ticketId, Integer userId, Integer adminId) {
        List<Tickets> TicketList;
        List<TicketDAO> TicketList1 = new ArrayList<>();
        TicketList = ticketsRepository.findAll();
        TicketDAO itViewTicket = new TicketDAO();
        for (Tickets createTicket : TicketList) {
            if (ticketId == createTicket.getTicketId() && userId == createTicket.getUserId()) {
                itViewTicket.setTicketId(createTicket.getTicketId());
                itViewTicket.setCategoryId(createTicket.getCategoryId());
                itViewTicket.setSubCategoryId(createTicket.getSubCategoryId());
                itViewTicket.setDescription(createTicket.getDescription());
                itViewTicket.setSubjects(createTicket.getSubjects());
                itViewTicket.setAssigneeId(adminId);
                itViewTicket.setPriorityId(createTicket.getPriorityId());
                itViewTicket.setStatusId(createTicket.getStatusId());
                itViewTicket.setCreateDateTime(LocalDateTime.now());
                itViewTicket.setReportedId(userId);
                itViewTicket.setLastModifiedDatetime(createTicket.getLastModifiedDatetime());
                itViewTicket.setUserId(createTicket.getUserId());
                String url = "http://localhost:8184/api/userModule/";
                itViewTicket.setUrlLink(url + createTicket.getTicketId());
                ticketDAORepository.save(itViewTicket);
            }
        }
        return itViewTicket;
    }

    public TicketDAO SetStatus(Integer ticketId,Integer userId,Integer statusId) {
        List<Tickets> TicketList;
        List<TicketDAO> TicketList1 = new ArrayList<>();
        TicketList = ticketsRepository.findAll();
        TicketDAO itViewTicket = new TicketDAO();
        for (Tickets createTicket : TicketList) {
            if (ticketId == createTicket.getTicketId() && userId == createTicket.getUserId()) {
                itViewTicket.setTicketId(createTicket.getTicketId());
                itViewTicket.setCategoryId(createTicket.getCategoryId());
                itViewTicket.setSubCategoryId(createTicket.getSubCategoryId());
                itViewTicket.setDescription(createTicket.getDescription());
                itViewTicket.setSubjects(createTicket.getSubjects());
                itViewTicket.setAssigneeId(createTicket.getAssigneeId());
                itViewTicket.setPriorityId(createTicket.getPriorityId());
                itViewTicket.setStatusId(statusId);
                itViewTicket.setCreateDateTime(LocalDateTime.now());
                itViewTicket.setReportedId(userId);
                itViewTicket.setLastModifiedDatetime(LocalDateTime.now());
                itViewTicket.setUserId(createTicket.getUserId());
                String url = "http://localhost:8184/api/userModule/";
                itViewTicket.setUrlLink(url + createTicket.getTicketId());
                ticketDAORepository.save(itViewTicket);
            }
        }
        return itViewTicket;
    }
}
