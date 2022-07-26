package com.ITOSupportTracker.Entity;

import com.ITOSupportTracker.DTO.ItItemView;
import com.ITOSupportTracker.DTO.TicketDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tickets")
public class Tickets {
    @Id
    @Column(name="ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketId;
    @Column(name="assignee_Id")
    private  Integer assigneeId;
    @Column(name="subjects ")
    private String subjects ;
    @Column(name="description")
    private String description ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id" ,insertable =false,updatable =false,referencedColumnName = "status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private com.ITOSupportTracker.Entity.Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id",insertable =false,updatable =false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private com.ITOSupportTracker.Entity.Priority priority;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",insertable =false,updatable =false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private com.ITOSupportTracker.Entity.Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_category_id",insertable =false,updatable =false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private com.ITOSupportTracker.Entity.SubCategory subCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",insertable =false,updatable =false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    @Column(name ="create_datetime")
    private LocalDateTime createDateTime=LocalDateTime.now();
    @Column(name = "reported_id")
    private Integer reportedId;
    @Column(name = "last_modified_datetime")
    private  LocalDateTime lastModifiedDatetime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="message",insertable =false,updatable =false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Comment comment;
    @Column(name = "category_id")
    private  Integer categoryId;
    @Column(name = "sub_category_id")
    private Integer subCategoryId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "status_id" )
    private Integer statusId=201;
    @Column(name = "priority_id")
    private Integer priorityId;
    @Column(name="url_link")
    private String urlLink;



}