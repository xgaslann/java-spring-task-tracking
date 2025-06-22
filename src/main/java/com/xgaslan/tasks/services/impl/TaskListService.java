package com.xgaslan.tasks.services.impl;

import com.xgaslan.tasks.domain.entities.TaskList;
import com.xgaslan.tasks.repositories.ITaskListRepository;
import com.xgaslan.tasks.services.ITaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListService implements ITaskListService {

    private final ITaskListRepository repository;


    public TaskListService(ITaskListRepository taskListRepository) {
        this.repository = taskListRepository;
    }

    @Override
    public List<TaskList> getAll() {
        return repository.findAll();
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
        return repository.save(new TaskList(
                null,
                entity.getTitle(),
                entity.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public TaskList update(UUID id, TaskList entity) {
        if (null != entity.getId() && !entity.getId().equals(id)) {
            throw new IllegalArgumentException("Task list ID in entity must match the provided ID");
        }

        var existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskList with ID " + id + " not found"));

        existing.setTitle(entity.getTitle());
        existing.setDescription(entity.getDescription());
        existing.setUpdated(LocalDateTime.now());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
