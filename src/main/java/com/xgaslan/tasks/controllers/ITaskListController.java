package com.xgaslan.tasks.controllers;

import com.xgaslan.tasks.domain.dto.TaskListDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskListController {

    List<TaskListDto> getAll();

    TaskListDto create(TaskListDto dto);

    Optional<TaskListDto> getById(UUID id);

    TaskListDto update(UUID id, TaskListDto dto);

    void delete(UUID id);
}
