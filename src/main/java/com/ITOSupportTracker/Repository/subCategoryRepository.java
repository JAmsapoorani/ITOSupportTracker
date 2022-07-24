package com.ITOSupportTracker.Repository;



import com.ITOSupportTracker.Entity.subCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface subCategoryRepository extends JpaRepository<subCategory, Integer> {



}
