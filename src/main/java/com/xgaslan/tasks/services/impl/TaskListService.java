package com.xgaslan.tasks.services.impl;

import com.xgaslan.tasks.domain.entities.TaskList;
import com.xgaslan.tasks.repositories.ITaskListRepository;
import com.xgaslan.tasks.services.ITaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListService implements ITaskListService {

    private final ITaskListRepository taskListRepository;

    public TaskListService(ITaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> getAll() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList create(TaskList entity) {
        if (null != entity.getId()) {
            throw new IllegalArgumentException("TaskList ID must be null for creation");
        }

        if (null == entity.getTitle() || entity.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title cannot be null or blank");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                entity.getTitle(),
                entity.getDescription(),
                null,
                now,
                now
        ));
    }
}
