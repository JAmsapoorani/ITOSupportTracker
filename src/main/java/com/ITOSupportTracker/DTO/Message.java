package com.ITOSupportTracker.DTO;

import com.ITOSupportTracker.Entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "comment")
public class Message {
    @Id
    @Column(name="comment_id")
    private Integer commentId;

    @Column(name="message")
    private String message;
    @Column(name = "ticket_id")
    private Integer ticketId;
    @Column(name = "user_id")
    private Integer userId;



}
