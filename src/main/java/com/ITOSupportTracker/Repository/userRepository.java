package com.ITOSupportTracker.Repository;


import com.ITOSupportTracker.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user,Integer> {
}
