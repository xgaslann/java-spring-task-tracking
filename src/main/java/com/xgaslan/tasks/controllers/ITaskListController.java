package com.xgaslan.tasks.controllers;

import com.xgaslan.tasks.domain.dto.TaskListDto;

import java.util.List;

public interface ITaskListController {

    List<TaskListDto> getAll();

    TaskListDto create(TaskListDto dto);
}
