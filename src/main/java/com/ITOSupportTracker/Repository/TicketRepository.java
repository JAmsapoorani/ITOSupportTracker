package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.DTO.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

/*
    @Query(value = "select t from Ticket t ")
    public List<Object> viewTicketList();
*/
}
