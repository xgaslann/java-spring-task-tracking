package com.xgaslan.tasks.repositories;

import com.xgaslan.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITaskListRepository extends JpaRepository<TaskList, UUID> {
}
