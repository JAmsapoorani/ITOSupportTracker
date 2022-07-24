package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.Entity.priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface priorityRepository extends JpaRepository<priority,Integer> {

}
