package com.xgaslan.tasks.controllers;

import com.xgaslan.tasks.domain.dto.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskController {
    List<TaskDto> getAll(UUID taskListId);

    TaskDto create(UUID taskListId, TaskDto dto);

    Optional<TaskDto> getById(UUID taskListId, UUID id);

    TaskDto update(UUID taskListId, UUID id, TaskDto dto);

    void delete(UUID taskListId, UUID id);
}

