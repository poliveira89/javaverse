package com.poliveira.javaverse.repositories;

import com.poliveira.javaverse.models.TaskVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskVO, Long> {

  // Custom query methods can be defined here if needed
  // For example, to find tasks by status:
  // List<TaskVO> findByStatus(Status status);

}
