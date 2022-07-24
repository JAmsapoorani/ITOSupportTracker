package com.ITOSupportTracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @Entity
    @Table(name="tickets")
    public class Ticket {
        @Id
        @Column(name="ticket_id")
        private Integer ticketId;
        @Column (name ="category_desc")
        private String categoryDesc;
        @Column (name ="sub_category_desc")
        private  String subCategoryDesc;
        @Column(name="assignee_Id")
        private  Integer assigneeId;
        @Column(name="subjects")
        private String subjects ;
        @Column(name="priority_name")
        private String priorityName;
        @Column(name="status_name")
        private String statusName;
        @Column(name="url_link")
        private  String urlLink;

}
