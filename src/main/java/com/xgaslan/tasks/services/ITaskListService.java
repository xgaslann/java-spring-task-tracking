package com.xgaslan.tasks.services;

import com.xgaslan.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskListService {

    List<TaskList> getAll();

    TaskList create(TaskList entity);

    Optional<TaskList> getById(UUID id);

    TaskList update(UUID id, TaskList entity);

    void delete(UUID id);


}
