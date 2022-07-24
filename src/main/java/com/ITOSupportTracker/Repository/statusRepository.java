package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.Entity.status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface statusRepository extends JpaRepository<status,Integer> {

}
