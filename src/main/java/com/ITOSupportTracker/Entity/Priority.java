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
@Table(name = "Priority")
public class Priority {
    @Id
    @Column(name="priority_id")
    private Integer priorityId;
    @Column(name="priority_name")
    private  String priorityName;

}
