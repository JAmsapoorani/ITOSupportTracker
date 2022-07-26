package com.ITOSupportTracker.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="Comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="comment_id")
    private Integer commentId;
    @OneToOne
    @JoinColumn(name = "ticket_id",insertable =false,updatable =false)
    private Tickets tickets;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable =false,updatable =false)
    private User user;
    @Column(name="message")
    private String message;
    @Column(name = "ticket_id")
    private Integer ticketId;
    @Column(name = "user_id")
    private Integer userId;

}
