package com.xgaslan.tasks.mappers.impl;

import com.xgaslan.tasks.domain.dto.TaskDto;
import com.xgaslan.tasks.domain.entities.Task;
import com.xgaslan.tasks.mappers.ITaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements ITaskMapper {
    @Override
    public Task toEntity(TaskDto dto) {
        return new Task(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority(),
                dto.status(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task entity) {
        return new TaskDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDueDate(),
                entity.getPriority(),
                entity.getStatus()
        );
    }
}
