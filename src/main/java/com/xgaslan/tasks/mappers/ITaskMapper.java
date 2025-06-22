package com.xgaslan.tasks.mappers;

import com.xgaslan.tasks.domain.dto.TaskDto;
import com.xgaslan.tasks.domain.entities.Task;

public interface ITaskMapper {
    Task toEntity(TaskDto dto);

    TaskDto toDto(Task entity);
}
