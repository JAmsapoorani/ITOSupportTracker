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
@Table(name = "Status")
public class status {
    @Id
    @Column(name="status_id")
    private Integer statusId;
    @Column(name="status_name")
    private  String statusName;



}
