package com.ITOSupportTracker.Repository;

import com.ITOSupportTracker.DTO.itItemView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ittemRepository extends JpaRepository<itItemView, Integer> {

}
