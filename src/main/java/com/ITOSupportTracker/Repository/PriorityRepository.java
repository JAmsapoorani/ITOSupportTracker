package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.Entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority,Integer> {

}
