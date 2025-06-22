package com.xgaslan.tasks.mappers;

import com.xgaslan.tasks.domain.dto.TaskListDto;
import com.xgaslan.tasks.domain.entities.TaskList;

public interface ITaskListMapper {

    TaskList toEntity(TaskListDto dto);

    TaskListDto toDto(TaskList entity);
}
