package com.ITOSupportTracker.Repository;


import com.ITOSupportTracker.Entity.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface categoryRepository extends JpaRepository<category,Integer> {

}