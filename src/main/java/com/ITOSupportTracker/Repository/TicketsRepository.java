package com.ITOSupportTracker.Repository;


import com.ITOSupportTracker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer>  {



}