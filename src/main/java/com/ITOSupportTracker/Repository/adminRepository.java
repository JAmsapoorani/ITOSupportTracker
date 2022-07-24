package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.Entity.adminTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepository extends JpaRepository<adminTeam,Long> {
}
