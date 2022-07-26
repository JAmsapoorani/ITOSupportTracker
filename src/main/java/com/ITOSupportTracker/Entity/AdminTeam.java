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
@Table(name = "Admin_team")
public class AdminTeam {
    @Id
    @Column(name="admin_id")
    private Integer adminId;
    @Column(name = "name")
    private String name;
    @Column(name = "email_id")
    private String emailId;

}
