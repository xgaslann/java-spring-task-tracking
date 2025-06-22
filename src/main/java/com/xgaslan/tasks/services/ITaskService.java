package com.xgaslan.tasks.services;

import com.xgaslan.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskService {
    List<Task> getAll(UUID taskListId);

    Task create(UUID taskListId, Task entity);

    Optional<Task> getById(UUID taskListId, UUID id);

    Task update(UUID taskListId, UUID id, Task entity);

    void delete(UUID taskListId, UUID id);
}
