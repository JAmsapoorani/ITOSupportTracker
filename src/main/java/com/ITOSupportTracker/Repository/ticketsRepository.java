package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.DTO.itItemView;
import com.ITOSupportTracker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ticketsRepository extends JpaRepository<Tickets, Integer>  {
   /* @Query(value = "select t.ticketId,category.categoryDesc,subCategory.subCategoryDesc,t.subjects,priority.priorityName," +
            "status.statusName,t.assigneeId,t.urlLink from Tickets t  where userId=?1 ")
    public List<?> viewTicket(Integer userId);

    @Query(value ="select t from Tickets t ")
   public   List<Tickets> ViewByTicketId(Integer ticketId);
*/


}
