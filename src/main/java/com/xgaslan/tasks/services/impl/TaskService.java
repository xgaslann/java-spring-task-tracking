package com.xgaslan.tasks.services.impl;

import com.xgaslan.tasks.domain.entities.Task;
import com.xgaslan.tasks.domain.entities.TaskPriority;
import com.xgaslan.tasks.domain.entities.TaskStatus;
import com.xgaslan.tasks.repositories.ITaskListRepository;
import com.xgaslan.tasks.repositories.ITaskRepository;
import com.xgaslan.tasks.services.ITaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository repository;

    private final ITaskListRepository taskListRepository;

    public TaskService(ITaskRepository repository, ITaskListRepository taskListRepository) {
        this.repository = repository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> getAll(UUID taskListId) {
        return repository.findByTaskListId(taskListId);
    }

    @Override
    public Task create(UUID taskListId, Task entity) {
        if (null != entity.getId()) {
            throw new IllegalArgumentException("Task ID must be null when creating a new task.");
        }

        if (null == entity.getTitle() || entity.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or blank.");
        }

        var taskPriority = Optional.ofNullable(entity.getPriority()).orElse(TaskPriority.MEDIUM);

        var taskStatus = TaskStatus.OPEN;

        var taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Task list with ID " + taskListId + " does not exist."));

        var now = LocalDateTime.now();
        var taskToSave = new Task(null, entity.getTitle(), entity.getDescription(), entity.getDueDate(), taskPriority, taskStatus, taskList, now, now);

        repository.save(taskToSave);

        return taskToSave;
    }

    @Override
    public Optional<Task> getById(UUID taskListId, UUID id) {
        return repository.findByTaskListIdAndId(taskListId, id);
    }

    @Transactional
    @Override
    public Task update(UUID taskListId, UUID id, Task entity) {
        if (null != entity.getId() && !entity.getId().equals(id)) {
            throw new IllegalArgumentException("Task ID in entity must match the provided ID");
        }

        if (null == entity.getPriority()) {
            throw new IllegalArgumentException("Task priority cannot be null");
        }

        if (null == entity.getStatus()) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        var existingTask = repository.findByTaskListIdAndId(taskListId, id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " does not exist in task list " + taskListId));

        existingTask.setTitle(entity.getTitle());
        existingTask.setDescription(entity.getDescription());
        existingTask.setDueDate(entity.getDueDate());
        existingTask.setPriority(Optional.ofNullable(entity.getPriority()).orElse(existingTask.getPriority()));
        existingTask.setStatus(Optional.ofNullable(entity.getStatus()).orElse(existingTask.getStatus()));
        existingTask.setUpdated(LocalDateTime.now());

        return repository.save(existingTask);
    }

    @Transactional
    @Override
    public void delete(UUID taskListId, UUID id) {
        repository.deleteByTaskListIdAndId(taskListId, id);
    }

//    @Override
//    public void delete(UUID taskListId, UUID id) {
//        var task = repository.findByTaskListIdAndId(taskListId, id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " does not exist in task list " + taskListId));
//
//        repository.delete(task);
//    }
}
