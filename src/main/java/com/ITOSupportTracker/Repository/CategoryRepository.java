package com.ITOSupportTracker.Repository;


import com.ITOSupportTracker.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}