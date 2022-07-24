package com.ITOSupportTracker.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user")
public class user {

    @Id
    @Column(name="user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "emailId")
    private String emailId;


}
