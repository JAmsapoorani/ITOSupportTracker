package com.ITOSupportTracker.Service;

import com.ITOSupportTracker.DTO.Message;
import com.ITOSupportTracker.DTO.Ticket;
import com.ITOSupportTracker.DTO.TicketDAO;
import com.ITOSupportTracker.DTO.ItItemView;
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
    public ItItemView ViewByTicketId(Integer ticketId)
    {
        List<Tickets> TicketList;
        List<ItItemView> TicketList1 = new ArrayList<>();
        ItItemView itViewTicket = new ItItemView();
        TicketList = ticketsRepository.findAll();
        for (Tickets createTicket : TicketList) {
            if (ticketId == createTicket.getTicketId()) {

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
                String url = "http://localhost:8184/api/itTeamModule/ViewTicketList/";
                itViewTicket.setUrlLink(url + createTicket.getTicketId());
                List<User> userlist = userRepository.findAll();
                String user1 = "";
                List<Comment> comments=commentRepository.findAll();
                String comments1="";
                List<User> Userlist= userRepository.findAll();
                for (User user : Userlist) {
                    System.out.println(user.getUserId());
                    Integer user_id= Integer.valueOf(user.getUserId());
                    if (createTicket.getUserId()==user_id) {
                        itViewTicket.setUserName(findByUserId(createTicket.getUserId()).getUserName());
                        break;
                    }
                }

               List<Message> messages=new ArrayList<>();
                List<Comment> comments2=new ArrayList<>();
                for (Comment comment : comments) {
                    if (comment.getTicketId() == createTicket.getTicketId()) {
                        Message message=new Message();
                        message.setCommentId(comment.getCommentId());
                        message.setTicketId(comment.getTicketId());
                        message.setMessage(comment.getMessage());
                        message.setUserId(comment.getUserId());
                       messages.add(message);
                    }
                }
                itViewTicket.setMessage(messages);
                  //TicketList1.add(itViewTicket);
                }

        }
        return itViewTicket;
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
                String url = "http://localhost:8184/api/itTeamModule/ViewTicketList/";
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
                String url = "http://localhost:8184/api/itTeamModule/ViewTicketList/";
                itViewTicket.setUrlLink(url + createTicket.getTicketId());
                ticketDAORepository.save(itViewTicket);
            }
        }
        return itViewTicket;
    }
}
