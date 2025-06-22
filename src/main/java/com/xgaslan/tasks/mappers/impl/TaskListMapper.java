package com.xgaslan.tasks.mappers.impl;

import com.xgaslan.tasks.domain.dto.TaskListDto;
import com.xgaslan.tasks.domain.entities.Task;
import com.xgaslan.tasks.domain.entities.TaskList;
import com.xgaslan.tasks.domain.entities.TaskStatus;
import com.xgaslan.tasks.mappers.ITaskListMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapper implements ITaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList toEntity(TaskListDto dto) {
        return new TaskList(
                dto.id(),
                dto.title(),
                dto.description(),
                Optional.ofNullable(dto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toEntity).toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList entity) {
        return new TaskListDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                Optional.ofNullable(entity.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateProgress(entity.getTasks()),
                Optional.ofNullable(entity.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)

        );
    }

    private Double calculateProgress(List<Task> tasks) {
        if (null == tasks) {
            return null;
        }

        long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED.equals(task.getStatus()))
                .count();

        return (double) closedTaskCount / tasks.size();
    }
}
