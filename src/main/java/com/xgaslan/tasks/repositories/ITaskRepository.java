package com.xgaslan.tasks.repositories;

import com.xgaslan.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTaskListId(UUID taskListId);

    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);
}
