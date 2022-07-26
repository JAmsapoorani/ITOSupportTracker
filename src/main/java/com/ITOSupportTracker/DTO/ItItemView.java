package com.ITOSupportTracker.DTO;

import com.ITOSupportTracker.Entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class ItItemView {
    @Id
    @Column(name="ticket_id")
    private Integer ticketId;
    @Column(name="assignee_Id")
    private  Integer assigneeId;
    @Column(name="subjects ")
    private String subjects ;
    @Column(name="description")
    private String description ;
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
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "message")
    private List<Message> message;
    @Column(name="user_name")
    private String userName;
    @Column(name="url_link")
    private String urlLink;
    @Column(name ="create_datetime")
    private LocalDateTime createDateTime;
    @Column(name = "reported_id")
    private Integer reportedId;
    @Column(name = "last_modified_datetime")
    private  LocalDateTime lastModifiedDatetime;

}
