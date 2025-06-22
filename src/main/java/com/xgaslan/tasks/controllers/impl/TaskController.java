package com.xgaslan.tasks.controllers.impl;

import com.xgaslan.tasks.controllers.ITaskController;
import com.xgaslan.tasks.domain.dto.TaskDto;
import com.xgaslan.tasks.mappers.impl.TaskMapper;
import com.xgaslan.tasks.services.ITaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{taskListId}/tasks")
public class TaskController implements ITaskController {
    private final ITaskService service;

    private final TaskMapper mapper;

    public TaskController(ITaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Override
    public List<TaskDto> getAll(@PathVariable UUID taskListId) {
        return service.getAll(taskListId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @PostMapping
    @Override
    public TaskDto create(@PathVariable UUID taskListId, @RequestBody TaskDto dto) {
        var result = service.create(taskListId, mapper.toEntity(dto));
        return mapper.toDto(result);
    }

    @GetMapping("/{id}")
    @Override
    public Optional<TaskDto> getById(@PathVariable UUID taskListId, @PathVariable UUID id) {
        return service.getById(taskListId, id)
                .map(mapper::toDto);
    }

    @PutMapping("/{id}")
    @Override
    public TaskDto update(@PathVariable UUID taskListId, @PathVariable UUID id, @RequestBody TaskDto dto) {

        var updatedEntity = service.update(taskListId, id, mapper.toEntity(dto));
        return mapper.toDto(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable UUID taskListId, @PathVariable UUID id) {
        service.delete(taskListId, id);
    }
}
