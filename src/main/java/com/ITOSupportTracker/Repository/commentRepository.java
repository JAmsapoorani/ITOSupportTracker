package com.ITOSupportTracker.Repository;


import com.ITOSupportTracker.Entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface commentRepository extends JpaRepository<comment,Integer> {
}
