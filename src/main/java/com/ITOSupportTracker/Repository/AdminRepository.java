package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.Entity.AdminTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminTeam,Long> {
}
