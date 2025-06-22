package com.xgaslan.tasks.services;

import com.xgaslan.tasks.domain.entities.TaskList;

import java.util.List;

public interface ITaskListService {

    List<TaskList> getAll();

    TaskList create(TaskList entity);
}
