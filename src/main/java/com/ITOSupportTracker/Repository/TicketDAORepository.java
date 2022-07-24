package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.DTO.TicketDAO;
import com.ITOSupportTracker.DTO.itItemView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDAORepository extends JpaRepository<TicketDAO, Integer> {


}
